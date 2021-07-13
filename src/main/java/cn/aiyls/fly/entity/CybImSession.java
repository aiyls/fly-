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
    private Long id;
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


    private String uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getOppositeId() {
        return oppositeId;
    }

    public void setOppositeId(Long oppositeId) {
        this.oppositeId = oppositeId;
    }

    public String getOppositeName() {
        return oppositeName;
    }

    public void setOppositeName(String oppositeName) {
        this.oppositeName = oppositeName;
    }

    public String getOppositePhone() {
        return oppositePhone;
    }

    public void setOppositePhone(String oppositePhone) {
        this.oppositePhone = oppositePhone;
    }

    public LocalDateTime getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(LocalDateTime operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
