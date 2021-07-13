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

/**
 * 只能单端登陆的websocket session管理器
 */
@Slf4j
public class SingleSessionManager implements SessionManager {

    private final ConcurrentHashMap<Long, ConcurrentWebSocketSessionDecorator> sessionPool = new ConcurrentHashMap<>();

    /**
     * 根据策略LimitStrategy选择注入该SessionManager，如果注入成功则ObjectMapper的注入是生效的，可正常使用
     */
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void add(Long key, WebSocketSession session) {
        close(key, session); // 管理连接之前先断开之前的连接

        int sendTimeLimit = 10 * 1000;
        int sendBufferSizeLimit = 512 * 1024;
        //这两个限制条件默认值参见 - org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;
        ConcurrentWebSocketSessionDecorator sessionDecorator = new ConcurrentWebSocketSessionDecorator(session, sendTimeLimit, sendBufferSizeLimit);
        sessionPool.put(key, sessionDecorator);
        log.info("用户={}建立ws连接", key);
    }

    @Override
    public WebSocketSession remove(Long key, String... args) {
        WebSocketSession session = sessionPool.remove(key);
        log.info("用户={}的ws连接已断开", key);
        return session;
    }

    @Override
    public void close(Long key, WebSocketSession socketSession) {
        WebSocketSession session = remove(key);
        if (session != null) {
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
        return sessionPool.containsKey(key);
    }

    @Override
    public void sendMessage(Long key, BaseMessageVO baseMessageVO) {
        WebSocketSession session = sessionPool.get(key);
        if (session != null) {
            try {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(baseMessageVO)));
            } catch (IOException e) {
                e.printStackTrace();
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
        sessionPool.forEach((key, value) -> sendMessage(key, baseMessageVO));
    }
}
