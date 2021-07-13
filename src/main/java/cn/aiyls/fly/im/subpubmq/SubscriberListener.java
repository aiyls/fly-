package cn.aiyls.fly.im.subpubmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import cn.aiyls.fly.im.mywebsocket.SessionManager;
import cn.aiyls.fly.im.utils.ApplicationContextProvider;
import cn.aiyls.fly.im.vo.BaseMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;


@Slf4j
public class SubscriberListener implements MessageListener {

    private final ObjectMapper objectMapper = ApplicationContextProvider.getBean(ObjectMapper.class);

    private final SessionManager sessionManager = ApplicationContextProvider.getBean(SessionManager.class);

    /**
     * 订阅者接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            BaseMessageVO baseMessageVO = objectMapper.readValue(message.getBody(), BaseMessageVO.class);
            sessionManager.sendMessage(baseMessageVO.fetchReceiver(), baseMessageVO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
