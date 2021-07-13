package cn.aiyls.fly.im.subpubmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.aiyls.fly.im.vo.BaseMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private SessionManager sessionManager;

    public void publish(BaseMessageVO baseMessageVO) {
        this.publish(ChannelEnum.WEBSOCKET, baseMessageVO);
    }

    public void publish(ChannelEnum channel, BaseMessageVO baseMessageVO) {
//        Long receiver = baseMessageVO.fetchReceiver();
//        if (sessionManager.isExist(receiver)) {
//            sessionManager.sendMessage(receiver, baseMessageVO);
//        } else {
//            try {
//                redisTemplate.convertAndSend(channel.getTopic(), objectMapper.writeValueAsString(baseMessageVO));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            redisTemplate.convertAndSend(channel.getTopic(), objectMapper.writeValueAsString(baseMessageVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
