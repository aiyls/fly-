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
@TableName("t_fly_report")
public class TFlyReport  implements Serializable {

	private static final long serialVersionUID =  2113512920672890835L;

   @TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 举报理由
	 */
	private String content;

	/**
	 * 附件
	 */
	private String enclosure;

	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 用户账号
	 */
	private String userName;

	/**
	 * 用户昵称
	 */
	private String nickname;

	/**
	 * 处理状态1已处理，2不处理
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

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

	public String getEnclosure() {
		return this.enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
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
					"enclosure='" + enclosure + '\'' +
					"userId='" + userId + '\'' +
					"userName='" + userName + '\'' +
					"nickname='" + nickname + '\'' +
					"status='" + status + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
				'}';
	}

	public TFlyReport(TFlyReport model) {
		this.id = model.id;
		this.content = model.content;
		this.enclosure = model.enclosure;
		this.userId = model.userId;
		this.userName = model.userName;
		this.nickname = model.nickname;
		this.status = model.status;
		this.createTime = model.createTime;
		this.updateTime = model.updateTime;
	}

}
