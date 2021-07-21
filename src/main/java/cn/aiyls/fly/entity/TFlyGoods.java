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
@TableName("t_fly_goods")
public class TFlyGoods  implements Serializable {

	private static final long serialVersionUID =  9185857431366506867L;

   @TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 商品名称
	 */
	private String goodsName;

	/**
	 * 商品价格
	 */
	private BigDecimal goodsPrice;

	/**
	 * 商品库存
	 */
	private Integer goodsStock;

	/**
	 * 年份
	 */
	private String goodsYear;

	/**
	 * 商品所在地
	 */
	private String goodsAddress;

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
	 * 区县
	 */
	private String area;

	/**
	 * 街道
	 */
	private String street;

	/**
	 * 附件
	 */
	private String enclosure;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
   	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

	/**
	 * 商品类型
	 */
	private Integer type;

	/**
	 * 状态1：正常 2：已删除
	 */
	private Integer status;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsStock() {
		return this.goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getGoodsYear() {
		return this.goodsYear;
	}

	public void setGoodsYear(String goodsYear) {
		this.goodsYear = goodsYear;
	}

	public String getGoodsAddress() {
		return this.goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
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

	public String getEnclosure() {
		return this.enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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
					"goodsName='" + goodsName + '\'' +
					"goodsPrice='" + goodsPrice + '\'' +
					"goodsStock='" + goodsStock + '\'' +
					"goodsYear='" + goodsYear + '\'' +
					"goodsAddress='" + goodsAddress + '\'' +
					"longitude='" + longitude + '\'' +
					"latitude='" + latitude + '\'' +
					"province='" + province + '\'' +
					"city='" + city + '\'' +
					"area='" + area + '\'' +
					"street='" + street + '\'' +
					"enclosure='" + enclosure + '\'' +
					"remarks='" + remarks + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
					"type='" + type + '\'' +
					"status='" + status + '\'' +
					"userId='" + userId + '\'' +
				'}';
	}

	public TFlyGoods() {}

	public TFlyGoods(TFlyGoods model) {
		this.id = model.id;
		this.goodsName = model.goodsName;
		this.goodsPrice = model.goodsPrice;
		this.goodsStock = model.goodsStock;
		this.goodsYear = model.goodsYear;
		this.goodsAddress = model.goodsAddress;
		this.longitude = model.longitude;
		this.latitude = model.latitude;
		this.province = model.province;
		this.city = model.city;
		this.area = model.area;
		this.street = model.street;
		this.enclosure = model.enclosure;
		this.remarks = model.remarks;
		this.createTime = model.createTime;
		this.updateTime = model.updateTime;
		this.type = model.type;
		this.status = model.status;
		this.userId = model.userId;
	}

}
