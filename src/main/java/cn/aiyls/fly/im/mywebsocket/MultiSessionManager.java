package cn.aiyls.fly.im.mywebsocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.aiyls.fly.im.vo.BaseMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 支持多端登陆的websocket session管理器
 */
@Slf4j
public class MultiSessionManager implements SessionManager {

    private final ConcurrentHashMap<Long, CopyOnWriteArrayList<String>> masterPool = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, ConcurrentWebSocketSessionDecorator> slavePool = new ConcurrentHashMap<>();

    /**
     * 根据策略LimitStrategy选择注入该SessionManager，如果注入成功则ObjectMapper的注入是生效的，可正常使用
     */
    @Resource
    private ObjectMapper objectMapper;

    private CopyOnWriteArrayList<String> getIdsByKey(Long key) {
        CopyOnWriteArrayList<String> temp;
        if (masterPool.containsKey(key)) {
            temp = masterPool.get(key);
        } else {
            temp = new CopyOnWriteArrayList<>();
            masterPool.put(key, temp);
        }
        return temp;
    }

    @Override
    public void add(Long key, WebSocketSession socketSession) {
        int sendTimeLimit = 10 * 1000;
        int sendBufferSizeLimit = 512 * 1024;
        //这两个限制条件默认值参见 - org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;
        ConcurrentWebSocketSessionDecorator decorator = new ConcurrentWebSocketSessionDecorator(socketSession, sendTimeLimit, sendBufferSizeLimit);
        String sessionId = decorator.getId();

        CopyOnWriteArrayList<String> temp = getIdsByKey(key);
        temp.add(sessionId);
        slavePool.put(sessionId, decorator);
        log.info("用户={}建立ws连接", key);
    }

    @Override
    public WebSocketSession remove(Long key, String... args) {
        if (args.length == 1) {
            String sessionId = args[0];
            CopyOnWriteArrayList<String> temp = null;
            if (masterPool.containsKey(key)) {
                temp = masterPool.get(key);
            }
            if (null != temp && temp.size() > 0) {
                temp.remove(sessionId);
                if (temp.size() == 0) {
                    masterPool.remove(key);
                }
            }
            WebSocketSession session = slavePool.remove(sessionId);
            log.info("用户={}的ws连接已断开", key);
            return session;
        }
        return null;
    }

    @Override
    public void close(Long key, WebSocketSession socketSession) {
        WebSocketSession session = remove(key, socketSession.getId());
        if (null != session) {
            try {
                session.close();
                log.info("用户={}的ws连接已关闭", key);
            } catch (IOException e) {
                log.error("用户={}的ws连接关闭时发生错误", key);
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isExist(Long key) {
        return masterPool.containsKey(key);
    }

    @Override
    public void sendMessage(Long key, BaseMessageVO baseMessageVO) {
        if (masterPool.containsKey(key)) {
            CopyOnWriteArrayList<String> temp = masterPool.get(key);
            if (null != temp && temp.size() > 0) {
                temp.forEach(item -> {
                    if (slavePool.containsKey(item)) {
                        try {
                            slavePool.get(item).sendMessage(new TextMessage(objectMapper.writeValueAsString(baseMessageVO)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            log.info("用户={}与本服务器没有连接，无法通信", key);
        }
    }

    @Override
    public void sendMessage(List<Long> keys, BaseMessageVO baseMessageVO) {
        keys.forEach(item -> sendMessage(item, baseMessageVO));
    }

    @Override
    public void sendMessage(BaseMessageVO baseMessageVO) {
        masterPool.forEach((key, value) -> value.forEach(item -> {
            if (slavePool.containsKey(item)) {
                try {
                    slavePool.get(item).sendMessage(new TextMessage(objectMapper.writeValueAsString(baseMessageVO)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
