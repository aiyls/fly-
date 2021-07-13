package cn.aiyls.fly.im.vo;


import cn.aiyls.fly.entity.CybImMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "通知消息VO，由系统发送给用户，通知用户哪些id的消息已读之类的功能。消息回执")
public class NoticeMessageVO implements BaseMessageVO {

    @ApiModelProperty(value = "消息id")
    private Long id;

    @ApiModelProperty(value = "消息类型，暂时默认为“notice”")
    private String msgType;

    @ApiModelProperty(value = "该消息关联的会话的uuid")
    private String uuid;

    @ApiModelProperty(value = "接收人id")
    private Long receiverId;

    @ApiModelProperty(value = "消息排序，提供给前端使用的")
    private Long msgOrder;

    @Override
    public Long fetchReceiver() {
        return this.receiverId;
    }

    public NoticeMessageVO() {}

    public NoticeMessageVO(CybImMessage message) {
        this.id = message.getId();
        this.msgType = BaseMsgTypeEnum.NOTICE.getType();
        this.uuid = message.getUuid();
        this.receiverId = message.getSenderId();
        this.msgOrder = message.getMsgOrder();
    }
}
