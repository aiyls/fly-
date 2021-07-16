package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
 * @Description  
 * @Author  mahongyi
 * @Date 2021-07-15 11:02:49 
 */

@Data
@TableName("t_fly_im_session_temp")
public class TFlyImSessionTemp  implements Serializable {

	private static final long serialVersionUID =  6722931143557672570L;

	/**
	 * 主键
	 */
   @TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 订单id
	 */
	private String orderId;

	/**
	 * 订单类型
	 */
	private String orderType;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 对方id
	 */
	private Long oppositeId;

	/**
	 * 是否删除：0.否；1.是
	 */
	private String deleteFlag;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 一次会话会有两条数据库记录，关联这两条记录
	 */
	private String uuid;

	/**
	 * 置顶标识
	 */
	private String topFlag;

	/**
	 * 更新时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOppositeId() {
		return this.oppositeId;
	}

	public void setOppositeId(Long oppositeId) {
		this.oppositeId = oppositeId;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTopFlag() {
		return this.topFlag;
	}

	public void setTopFlag(String topFlag) {
		this.topFlag = topFlag;
	}

	public LocalDateTime getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"orderId='" + orderId + '\'' +
					"orderType='" + orderType + '\'' +
					"userId='" + userId + '\'' +
					"oppositeId='" + oppositeId + '\'' +
					"deleteFlag='" + deleteFlag + '\'' +
					"createTime='" + createTime + '\'' +
					"uuid='" + uuid + '\'' +
					"topFlag='" + topFlag + '\'' +
					"updateTime='" + updateTime + '\'' +
				'}';
	}

	public TFlyImSessionTemp() {}

	public TFlyImSessionTemp(TFlyImSessionTemp model) {
		this.id = model.id;
		this.orderId = model.orderId;
		this.orderType = model.orderType;
		this.userId = model.userId;
		this.oppositeId = model.oppositeId;
		this.deleteFlag = model.deleteFlag;
		this.createTime = model.createTime;
		this.uuid = model.uuid;
		this.topFlag = model.topFlag;
		this.updateTime = model.updateTime;
	}

}
