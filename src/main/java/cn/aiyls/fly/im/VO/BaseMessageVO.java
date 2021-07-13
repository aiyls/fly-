package cn.aiyls.fly.im.vo;

import cn.aiyls.fly.entity.CybImMessage;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * 基本消息VO模型，其他类型的消息都基于此扩展
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "msgType", visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = TextMessageVO.class, name = "text"),
        @JsonSubTypes.Type(value = NoticeMessageVO.class, name = "notice"),
        @JsonSubTypes.Type(value = PingMessageVO.class, name = "ping")
})
public interface BaseMessageVO {

    default CybImMessage becomeEntity() {
        return null;
    }

    default void handleMessage() {
        System.out.println("该消息处理类没有 handleMessage 的实现");
    }

    Long fetchReceiver();

}
