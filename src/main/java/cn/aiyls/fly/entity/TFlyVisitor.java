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
@TableName("t_fly_visitor" )
public class TFlyVisitor  implements Serializable {

	private static final long serialVersionUID =  3033838837776498642L;

   @TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

   	private Long userToId;

   	private Integer status;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserToId() {
		return userToId;
	}

	public void setUserToId(Long userToId) {
		this.userToId = userToId;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"icon='" + icon + '\'' +
					"username='" + username + '\'' +
					"userId='" + userId + '\'' +
					"createTime='" + createTime + '\'' +
					"userToId='" + userToId + '\'' +
				'}';
	}

	public TFlyVisitor() {}

	public TFlyVisitor(TFlyVisitor model) {
		this.id = model.id;
		this.icon = model.icon;
		this.username = model.username;
		this.userId = model.userId;
		this.createTime = model.createTime;
		this.status = model.status;
		this.userToId = model.userToId;
	}

}
