package cn.aiyls.fly.im.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Data
public class CheckSessionVO {
//    @ApiModelProperty(value = "会话的uuid")
    private String uuid;

//    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

//    @ApiModelProperty(value = "用户id")
    private Long userId;

//    @ApiModelProperty(value = "用户姓名")
    private String userName;

//    @ApiModelProperty(value = "对方id")
    private Long oppositeId;

//    @ApiModelProperty(value = "对方姓名")
    private String oppositeName;

//    @ApiModelProperty(value = "订单id")
    private String orderId;

    public CheckSessionVO() {}

    public CheckSessionVO (CreateSessionVO createSessionVO) {
        this.operatingTime = LocalDateTime.now();
        this.userId = createSessionVO.getUserId();
        this.userName = createSessionVO.getUserName();
        this.oppositeId = createSessionVO.getOppositeId();
        this.oppositeName = createSessionVO.getOppositeName();
        this.orderId = createSessionVO.getOrderId();
    }
}
