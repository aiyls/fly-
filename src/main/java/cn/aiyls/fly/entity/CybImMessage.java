package cn.aiyls.fly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Data
public class CybImMessage {
    private Long id;
    private Long receiverId;
    private String msgType;
    private String msgContent;
    private String readFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
    private String uuid;
    private Long msgOrder;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String read) {
        this.readFlag = read;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getMsgOrder() {
        return msgOrder;
    }

    public void setMsgOrder(Long msgOrder) {
        this.msgOrder = msgOrder;
    }

    private Long senderId;
}
