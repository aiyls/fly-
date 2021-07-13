package cn.aiyls.fly.im.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "会话带头像VO")
public class AvatarSessionVO {

    @ApiModelProperty(value = "会话编号")
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderId;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名字，可能是手机号，也可能是实名")
    private String userName;

    @ApiModelProperty(value = "用户号码")
    private String userPhone;

    // 前端登陆后缓存用户头像，这里就不用获取了
//    @ApiModelProperty(value = "用户头像")
//    private String userAvatar;

    @ApiModelProperty(value = "对方id")
    private Long oppositeId;

    @ApiModelProperty(value = "对方名字，可能是手机号，也可能是实名")
    private String oppositeName;

    @ApiModelProperty(value = "对方号码")
    private String oppositePhone;

    @ApiModelProperty(value = "对方头像")
    private String oppositeAvatar;

    @ApiModelProperty(value = "一次会话会有两条数据库记录，关联这两条记录")
    private String uuid;

    @ApiModelProperty(value = "置顶标识")
    private String topFlag;

    @ApiModelProperty(value = "操作时间，用于排序")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

    @ApiModelProperty(value = "最后一条消息发送的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastTime;

    @ApiModelProperty(value = "最后一条消息的内容")
    private String lastContent;

    @ApiModelProperty(value = "最后一条消息的自增id")
    private Long lastId;

    @ApiModelProperty(value = "最后一条消息的消息类型")
    private String msgType;

    @ApiModelProperty(value = "未读消息数量")
    private Integer unreadCount;

}
