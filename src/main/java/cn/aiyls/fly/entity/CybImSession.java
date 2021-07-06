package cn.aiyls.fly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Data
public class CybImSession {

    //    @ApiModelProperty(value = "用户id")
    private Long userId;

    //    @ApiModelProperty(value = "用户名字，可能是手机号，也可能是实名")
    private String userName;

    //    @ApiModelProperty(value = "用户号码")
    private String userPhone;

    //    @ApiModelProperty(value = "对方id")
    private Long oppositeId;

    //    @ApiModelProperty(value = "对/方名字，可能是手机号，也可能是实名")
    private String oppositeName;

    //    @ApiModelProperty(/value = "对/方号码")
    private String oppositePhone;

    //    @ApiModelProperty(value = "创建会话的时间，前端不用传")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

    //    @ApiModelProperty(value = "订单id")
    private String orderId;

    //    @ApiModelProperty(value = "订单类型：2发货找车；3仓库租赁")
    private String orderType;

    private String deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String topFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
