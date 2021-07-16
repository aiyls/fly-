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
@TableName("t_fly_dynamic")
public class TFlyDynamic  implements Serializable {

	private static final long serialVersionUID =  7403486004125098144L;

   @TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 图片
	 */
	private String image;

	/**
	 * 喜爱人数
	 */
	private Integer likeNum;

	/**
	 * 评论人数
	 */
	private Integer commentNum;

	/**
	 * 查看人数
	 */
	private Integer seeNum;

	/**
	 * 1、出让  2、收藏  3、动态
	 */
	private Integer type;

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

	/**
	 * 关联商品ID
	 */
	private String goodsIds;

	/**
	 * 关联商品信息，json格式保存
	 */
	private String goodsInfo;

	/**
	 * 状态1：正常 2：被举报属实 3：已删除
	 */
	private Integer status;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getLikeNum() {
		return this.likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getCommentNum() {
		return this.commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getSeeNum() {
		return this.seeNum;
	}

	public void setSeeNum(Integer seeNum) {
		this.seeNum = seeNum;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getGoodsIds() {
		return this.goodsIds;
	}

	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getGoodsInfo() {
		return this.goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"userId='" + userId + '\'' +
					"userName='" + userName + '\'' +
					"content='" + content + '\'' +
					"image='" + image + '\'' +
					"likeNum='" + likeNum + '\'' +
					"commentNum='" + commentNum + '\'' +
					"seeNum='" + seeNum + '\'' +
					"type='" + type + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
					"goodsIds='" + goodsIds + '\'' +
					"goodsInfo='" + goodsInfo + '\'' +
					"status='" + status + '\'' +
				'}';
	}

	public TFlyDynamic() {}

	public TFlyDynamic(TFlyDynamic model) {
		this.id = model.id;
		this.userId = model.userId;
		this.userName = model.userName;
		this.content = model.content;
		this.image = model.image;
		this.likeNum = model.likeNum;
		this.commentNum = model.commentNum;
		this.seeNum = model.seeNum;
		this.type = model.type;
		this.createTime = model.createTime;
		this.updateTime = model.updateTime;
		this.goodsIds = model.goodsIds;
		this.goodsInfo = model.goodsInfo;
		this.status = model.status;
	}

}
