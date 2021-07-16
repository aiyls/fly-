package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * @Description  
 * @Author  mahongyi
 * @Date 2021-07-15 11:02:49 
 */

@Data
@TableName("t_fly_base_region")
public class TFlyBaseRegion  implements Serializable {

	private static final long serialVersionUID =  1L;

	/**
	 * ID
	 */
    @TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 区域代码
	 */
	private String regionCode;

	/**
	 * 父级区域代码
	 */
	private String parentCode;

	/**
	 * 层级
	 */
	private Boolean regionLevel;

	/**
	 * 区域名称
	 */
	private String regionName;

	/**
	 * 区域英文名
	 */
	private String enName;

	/**
	 * 首字母
	 */
	private String firstLetter;

	/**
	 * 经度
	 */
	private BigDecimal longitude;

	/**
	 * 纬度
	 */
	private BigDecimal latitude;

	/**
	 * 是否热门城市,1是，其他不是
	 */
	private Boolean isHot;

	/**
	 * 是否末级，0不是，1是
	 */
	private Boolean isLeaf;

	/**
	 * 逻辑删除，0正常，1删除
	 */
	private Boolean isDelete;

	/**
	 * 创建人ID
	 */
	private Integer createUser;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 更新人ID
	 */
	private Integer updateUser;

	/**
	 * 更新时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/**
	 * 英文首字母
	 */
	private String enCode;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Boolean getRegionLevel() {
		return this.regionLevel;
	}

	public void setRegionLevel(Boolean regionLevel) {
		this.regionLevel = regionLevel;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getFirstLetter() {
		return this.firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Boolean getIsHot() {
		return this.isHot;
	}

	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	public Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnCode() {
		return this.enCode;
	}

	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"regionCode='" + regionCode + '\'' +
					"parentCode='" + parentCode + '\'' +
					"regionLevel='" + regionLevel + '\'' +
					"regionName='" + regionName + '\'' +
					"enName='" + enName + '\'' +
					"firstLetter='" + firstLetter + '\'' +
					"longitude='" + longitude + '\'' +
					"latitude='" + latitude + '\'' +
					"isHot='" + isHot + '\'' +
					"isLeaf='" + isLeaf + '\'' +
					"isDelete='" + isDelete + '\'' +
					"createUser='" + createUser + '\'' +
					"createTime='" + createTime + '\'' +
					"updateUser='" + updateUser + '\'' +
					"updateTime='" + updateTime + '\'' +
					"enCode='" + enCode + '\'' +
				'}';
	}

	public TFlyBaseRegion() {

	}

	public TFlyBaseRegion(TFlyBaseRegion model) {
		this.id = model.id;
		this.regionCode = model.regionCode;
		this.parentCode = model.parentCode;
		this.regionLevel = model.regionLevel;
		this.regionName = model.regionName;
		this.enName = model.enName;
		this.firstLetter = model.firstLetter;
		this.longitude = model.longitude;
		this.latitude = model.latitude;
		this.isHot = model.isHot;
		this.isLeaf = model.isLeaf;
		this.isDelete = model.isDelete;
		this.createUser = model.createUser;
		this.createTime = model.createTime;
		this.updateUser = model.updateUser;
		this.updateTime = model.updateTime;
		this.enCode = model.enCode;
	}

}
