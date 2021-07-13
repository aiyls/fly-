package cn.aiyls.fly.im.vo;

import cn.aiyls.fly.entity.CybImMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.aiyls.fly.im.service.ICybImMessageService;
import cn.aiyls.fly.im.service.ICybImSessionService;
import cn.aiyls.fly.im.subpubmq.PublishService;
import cn.aiyls.fly.im.utils.ApplicationContextProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Data
@ApiModel(value = "文本消息VO，由用户A发给用户B，聊天列表的主要内容")
public class TextMessageVO implements BaseMessageVO {

    @ApiModelProperty(value = "消息id")
    private Long id;

    @ApiModelProperty(value = "接收人id")
    private Long receiverId;

    @ApiModelProperty(value = "消息类型，暂时默认为“text”")
    private String msgType;

    @ApiModelProperty(value = "消息内容")
    private String msgContent;

    @ApiModelProperty(value = "是否已读，刚发送的消息默认是未读")
    private String read;

    @ApiModelProperty(value = "发送人id")
    private Long senderId;

    @ApiModelProperty(value = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "该消息关联的会话的uuid")
    private String uuid;

    @ApiModelProperty(value = "消息排序，提供给前端使用的")
    private Long msgOrder;

    private static final ICybImMessageService messageService = ApplicationContextProvider.getBean(ICybImMessageService.class);

    private static final ICybImSessionService sessionService = ApplicationContextProvider.getBean(ICybImSessionService.class);

    private static final PublishService publishService = ApplicationContextProvider.getBean(PublishService.class);


    @Override
    public CybImMessage becomeEntity() {
        CybImMessage message = new CybImMessage();
        message.setId(this.id);
        message.setReceiverId(this.receiverId);
        message.setMsgType(this.msgType);
        message.setMsgContent(this.msgContent);
        message.setReadFlag(this.read);
        message.setSenderId(this.senderId);
        message.setSendTime(this.sendTime);
        message.setUuid(this.uuid);
        message.setMsgOrder(this.msgOrder);
        return message;
    }

    @Override
    public Long fetchReceiver() {
        return this.receiverId;
    }

    public TextMessageVO() {
    }

    public TextMessageVO(CybImMessage message) {
        this.id = message.getId();
        this.receiverId = message.getReceiverId();
        this.msgType = message.getMsgType();
        this.msgContent = message.getMsgContent();
        this.read = message.getReadFlag();
        this.senderId = message.getSenderId();
        this.sendTime = message.getSendTime();
        this.uuid = message.getUuid();
        this.msgOrder = message.getMsgOrder();
    }

    public CheckSessionVO becomeCheckSessionVO() {
        // 用uuid做检查即可
        CheckSessionVO checkSessionVO = new CheckSessionVO();
        checkSessionVO.setUuid(this.uuid);
        checkSessionVO.setOperatingTime(LocalDateTime.now());
        return checkSessionVO;
    }

    @Override
    public void handleMessage() {
        if (this.getSendTime() == null) this.sendTime = LocalDateTime.now();

        CybImMessage messageEntity = this.becomeEntity();
        // 异步持久化
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 会话检查，如果删除则会恢复
            sessionService.checkSession(this.becomeCheckSessionVO());
            // 消息持久化
            return messageService.insertMessageAndReturnId(messageEntity);
        });

        try {
            if (future.get() == 1) {
                // 给用户发消息，消息回执
                NoticeMessageVO resultNoticeMsgVO = new NoticeMessageVO(messageEntity);
                publishService.publish(resultNoticeMsgVO);

                // 给对方发消息
                TextMessageVO resultTextMsgVO = new TextMessageVO(messageEntity);
                publishService.publish(resultTextMsgVO);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
