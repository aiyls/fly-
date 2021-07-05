package cn.aiyls.fly.im.config;

import cn.aiyls.fly.im.VO.BaseMessageVO;
import cn.aiyls.fly.im.service.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc: 多端登录session 管理
 */

@Slf4j
public class MultiSessionManager  implements SessionManager {


    private final ConcurrentHashMap<Long, CopyOnWriteArrayList<String>> masterPool = new ConcurrentHashMap<Long, CopyOnWriteArrayList<String>>();

    private final ConcurrentHashMap<String, ConcurrentWebSocketSessionDecorator> slavePool = new ConcurrentHashMap<>();


    @Autowired
    private ObjectMapper objectMapper;

    /**  发送等待时间*/
    private static final  int sendTimeLimit = 10 * 1000;

    /** 消息长度*/
    private static final int sendBufferSizeLimit = 512 * 1024;
    /**
     * 获取链接  没有则创建
     * @param key key
     * @return
     */
    private CopyOnWriteArrayList<String> getIdsByKeys(Long key){
        CopyOnWriteArrayList<String> temp;
        if (masterPool.contains(key)){
            temp = masterPool.get(key);
        }else {
            temp = new CopyOnWriteArrayList<String>();
            masterPool.put(key, temp);
        }
        return temp;
    }

    @Override
    public void add(Long key, WebSocketSession session) {
        ConcurrentWebSocketSessionDecorator decorator = new ConcurrentWebSocketSessionDecorator(session, sendTimeLimit, sendBufferSizeLimit);
        String sessionId = decorator.getId();
        CopyOnWriteArrayList<String> temp = getIdsByKeys(key);
        temp.add(sessionId);
        slavePool.put(sessionId, decorator);
        log.info(" 用户{}建立链接", key);
    }

    @Override
    public WebSocketSession remove(Long key, String... args) {
        if (args.length == 1){
            String sessionId = args[0];
            CopyOnWriteArrayList<String> temp = null;
            if (masterPool.containsKey(key)) {
                temp = masterPool.get(key);
            }
            if (!ObjectUtils.isEmpty(temp)){
                temp.remove(sessionId);
                if (temp.size() == 0){
                    masterPool.remove(key);
                }
            }
            WebSocketSession session = slavePool.remove(sessionId);
            log.info("用户{}断开链接", key);
            return  session;
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
