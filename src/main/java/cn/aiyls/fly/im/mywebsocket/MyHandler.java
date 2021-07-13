package cn.aiyls.fly.im.mywebsocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.aiyls.fly.im.vo.BaseMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
public class MyHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessionManager sessionManager;

    /**
     * 建立连接事件
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessionManager.add(userId, session);
        } else {
            throw new RuntimeException("未获取到userId");
        }
    }

    /**
     * 接收消息事件
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        Long userId = (Long) session.getAttributes().get("userId");
        log.info("server 接收到用户 " + userId + " 发送的消息：" + message.getPayload());
        try {
            BaseMessageVO baseMessageVO = objectMapper.readValue(message.getPayload(), BaseMessageVO.class);
            baseMessageVO.handleMessage();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断开连接事件
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, @NonNull CloseStatus status) {
        log.warn("断开原因：" + status);
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessionManager.close(userId, session);
        }
    }

    /**
     * 发生错误事件
     */
    @Override
    public void handleTransportError(WebSocketSession session, @NonNull Throwable exception) {
        Long userId = (Long) session.getAttributes().get("userId");
        log.error("发生异常，和用户 " + userId + " 的连接即将关闭，异常原因：", exception);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
