package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 车位租售信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
public class ParkingRentalVo extends BaseValue {

	private static final long serialVersionUID = 7891256804427468324L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 车位ID
	 */
	private String parkingId;

	/**
	 * 类型
	 * 1：租；2：售
	 */
	private String type;

	/**
	 * 房号
	 */
	private String houseNum;

	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 车牌号
	 */
	private String plateNum;

	/**
	 * 租售起始日期
	 */
	private Date startDate;

	/**
	 * 租赁到期日期
	 */
	private Date endDate;

	/**
	 * 出租/出售价格
	 */
	private Double price;

	/**
	 * 管理授权
	 */
	private String authorization;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
}
