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
@TableName("t_fly_company")
public class TFlyCompany  implements Serializable {

	private static final long serialVersionUID =  4836472088868572645L;

   @TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 公司名称
	 */
	private String compName;

	/**
	 * 法人
	 */
	private String legalName;

	/**
	 * 法人身份照号
	 */
	private String legalIdcard;

	/**
	 * 联系方式
	 */
	private String legalPhone;

	/**
	 * 营业执照编号
	 */
	private String compNumber;

	/**
	 * 企业所在地
	 */
	private String address;

	/**
	 * 营业执照
	 */
	private String licenseEnclosure;

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
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 经度
	 */
	private BigDecimal longitude;

	/**
	 * 纬度
	 */
	private BigDecimal latitude;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String area;

	/**
	 * 街道
	 */
	private String street;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getLegalName() {
		return this.legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalIdcard() {
		return this.legalIdcard;
	}

	public void setLegalIdcard(String legalIdcard) {
		this.legalIdcard = legalIdcard;
	}

	public String getLegalPhone() {
		return this.legalPhone;
	}

	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}

	public String getCompNumber() {
		return this.compNumber;
	}

	public void setCompNumber(String compNumber) {
		this.compNumber = compNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseEnclosure() {
		return this.licenseEnclosure;
	}

	public void setLicenseEnclosure(String licenseEnclosure) {
		this.licenseEnclosure = licenseEnclosure;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"compName='" + compName + '\'' +
					"legalName='" + legalName + '\'' +
					"legalIdcard='" + legalIdcard + '\'' +
					"legalPhone='" + legalPhone + '\'' +
					"compNumber='" + compNumber + '\'' +
					"address='" + address + '\'' +
					"licenseEnclosure='" + licenseEnclosure + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
					"userId='" + userId + '\'' +
					"longitude='" + longitude + '\'' +
					"latitude='" + latitude + '\'' +
					"province='" + province + '\'' +
					"city='" + city + '\'' +
					"area='" + area + '\'' +
					"street='" + street + '\'' +
				'}';
	}

	public TFlyCompany(TFlyCompany model) {
		this.id = model.id;
		this.compName = model.compName;
		this.legalName = model.legalName;
		this.legalIdcard = model.legalIdcard;
		this.legalPhone = model.legalPhone;
		this.compNumber = model.compNumber;
		this.address = model.address;
		this.licenseEnclosure = model.licenseEnclosure;
		this.createTime = model.createTime;
		this.updateTime = model.updateTime;
		this.userId = model.userId;
		this.longitude = model.longitude;
		this.latitude = model.latitude;
		this.province = model.province;
		this.city = model.city;
		this.area = model.area;
		this.street = model.street;
	}

}
