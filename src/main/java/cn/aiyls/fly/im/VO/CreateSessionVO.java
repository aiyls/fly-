package cn.aiyls.fly.im.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.aiyls.fly.entity.CybImSession;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "创建用户和服务提供者的会话的传参实体")
public class CreateSessionVO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名字，可能是手机号，也可能是实名")
    private String userName;

    @ApiModelProperty(value = "用户号码")
    private String userPhone;

    @ApiModelProperty(value = "对方id")
    private Long oppositeId;

    @ApiModelProperty(value = "对方名字，可能是手机号，也可能是实名")
    private String oppositeName;

    @ApiModelProperty(value = "对方号码")
    private String oppositePhone;

    @ApiModelProperty(value = "创建会话的时间，前端不用传")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单类型：2发货找车；3仓库租赁")
    private String orderType;

    public CybImSession createUserSession() {
        CybImSession session = new CybImSession();
        session.setOrderId(this.orderId);
        session.setOrderType(this.orderType);
        session.setUserId(this.userId);
        session.setUserName(this.userName);
        session.setUserPhone(this.userPhone);
        session.setOppositeId(this.oppositeId);
        session.setOppositeName(this.oppositeName);
        session.setOppositePhone(this.oppositePhone);
        session.setDeleteFlag("0");
        session.setCreateTime(this.operatingTime);
        session.setTopFlag("0");
        session.setUpdateTime(this.operatingTime);
        return session;
    }

    public CybImSession createOppositeSession() {
        CybImSession session = new CybImSession();
        session.setOrderId(this.orderId);
        session.setOrderType(this.orderType);
        session.setUserId(this.oppositeId);
        session.setUserName(this.oppositeName);
        session.setUserPhone(this.oppositePhone);
        session.setOppositeId(this.userId);
        session.setOppositeName(this.userName);
        session.setOppositePhone(this.userPhone);
        session.setDeleteFlag("0");
        session.setCreateTime(this.operatingTime);
        session.setTopFlag("0");
        session.setUpdateTime(this.operatingTime);
        return session;
    }
}
