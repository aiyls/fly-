package cn.aiyls.fly.im.config;

import cn.aiyls.fly.im.VO.BaseMessageVO;
import cn.aiyls.fly.im.service.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc: 单端登录 session 管理
 */
@Slf4j
public class SingleSessionManager implements SessionManager {

    private final ConcurrentHashMap<Long, ConcurrentWebSocketSessionDecorator> sessionPool = new ConcurrentHashMap<Long, ConcurrentWebSocketSessionDecorator>();

    /**  发送等待时间*/
    private static final  int sendTimeLimit = 10 * 1000;

    /** 消息长度*/
    private static final int sendBufferSizeLimit = 512 * 1024;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void add(Long key, WebSocketSession session) {
        close(key, session); // 管理连接之前先断开之前的连接
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
