package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description  
 * @Author  mahongyi
 * @Date 2021-07-15 11:02:49 
 */

@Data
@TableName("t_fly_comment")
public class TFlyComment  implements Serializable {

	private static final long serialVersionUID =  8821851274766961954L;

   @TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 父层评论ID，为空表示评论动态
	 */
	private Long parentId;

	/**
	 * 动态ID
	 */
	private Long dynamicId;

	/**
	 * 被评论的用户id
	 */
	private Long toUserId;

	/**
	 * 被评论用户的头像
	 */
	private String toUserAvatar;

	/**
	 * 被评论用户的昵称
	 */
	private String toUserName;

	/**
	 * 评论内容
	 */
	private String commentText;

	/**
	 * 评论人的用户ID
	 */
	private Long fromUserId;

	/**
	 * 评论人的用户头像
	 */
	private String fromUserAvatar;

	/**
	 * 评论人的用户昵称
	 */
	private String fromUserName;

	/**
	 * 子评论
	 */
	@TableField(exist = false)
	private List<TFlyComment> childList;

	/**
	 * 评论时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/**
	 * 状态 1：正常 2：删除 3：违规
	 */
	private Integer status;

	/**
	 * 点赞数量
	 */
	private Integer praiseNum;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getDynamicId() {
		return this.dynamicId;
	}

	public void setDynamicId(Long dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Long getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserAvatar() {
		return this.toUserAvatar;
	}

	public void setToUserAvatar(String toUserAvatar) {
		this.toUserAvatar = toUserAvatar;
	}

	public String getToUserName() {
		return this.toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Long getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserAvatar() {
		return this.fromUserAvatar;
	}

	public void setFromUserAvatar(String fromUserAvatar) {
		this.fromUserAvatar = fromUserAvatar;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<TFlyComment> getChildList() {
		return childList;
	}

	public void setChildList(List<TFlyComment> childList) {
		this.childList = childList;
	}

	public Integer getPraiseNum() {
		return this.praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"parentId='" + parentId + '\'' +
					"dynamicId='" + dynamicId + '\'' +
					"toUserId='" + toUserId + '\'' +
					"toUserAvatar='" + toUserAvatar + '\'' +
					"toUserName='" + toUserName + '\'' +
					"commentText='" + commentText + '\'' +
					"fromUserId='" + fromUserId + '\'' +
					"fromUserAvatar='" + fromUserAvatar + '\'' +
					"fromUserName='" + fromUserName + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
					"status='" + status + '\'' +
					"praiseNum='" + praiseNum + '\'' +
				'}';
	}

	public TFlyComment() {}

	public TFlyComment(TFlyComment model) {
		this.id = model.id;
		this.parentId = model.parentId;
		this.dynamicId = model.dynamicId;
		this.toUserId = model.toUserId;
		this.toUserAvatar = model.toUserAvatar;
		this.toUserName = model.toUserName;
		this.commentText = model.commentText;
		this.fromUserId = model.fromUserId;
		this.fromUserAvatar = model.fromUserAvatar;
		this.fromUserName = model.fromUserName;
		this.createTime = model.createTime;
		this.updateTime = model.updateTime;
		this.status = model.status;
		this.praiseNum = model.praiseNum;
	}

}
