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
@TableName("t_fly_feedback")
public class TFlyFeedback  implements Serializable {

	private static final long serialVersionUID =  3558103105598202423L;

   @TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 反馈内容
	 */
	private String content;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 处理状态
	 */
	private Integer status;

	/**
	 * 更新时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
					"content='" + content + '\'' +
					"userId='" + userId + '\'' +
					"userName='" + userName + '\'' +
					"nickname='" + nickname + '\'' +
					"createTime='" + createTime + '\'' +
					"status='" + status + '\'' +
					"updateTime='" + updateTime + '\'' +
				'}';
	}

	public TFlyFeedback(TFlyFeedback model) {
		this.id = model.id;
		this.content = model.content;
		this.userId = model.userId;
		this.userName = model.userName;
		this.nickname = model.nickname;
		this.createTime = model.createTime;
		this.status = model.status;
		this.updateTime = model.updateTime;
	}

}
