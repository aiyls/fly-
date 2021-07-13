package cn.aiyls.fly.im.vo;

import cn.aiyls.fly.im.subpubmq.PublishService;
import cn.aiyls.fly.im.utils.ApplicationContextProvider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "心跳消息VO，由客户端发给服务器，然后服务器原封不动返回给客户端")
public class PingMessageVO implements BaseMessageVO {

    @ApiModelProperty(value = "消息类型，暂时默认为“ping”")
    private String msgType;

    @ApiModelProperty(value = "接收人id，心跳发起者的userId")
    private Long receiverId;

    @ApiModelProperty(value = "心跳时间戳")
    private String t;

    private final static PublishService publishService = ApplicationContextProvider.getBean(PublishService.class);

    @Override
    public void handleMessage() {
        publishService.publish(this);
    }

    @Override
    public Long fetchReceiver() {
        return this.receiverId;
    }
}
