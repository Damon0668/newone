package com.liefeng.property.vo.parking;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

public class ParkingSingleRentalVo extends BaseValue{

	private static final long serialVersionUID = 8682600642478997769L;
	
	/***车位信息***/
	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 楼栋id
	 */
	private String buildingId;
	
	/**
	 * 车位编码
	 */
	private String code;
	
	/**
	 * 车位楼层
	 */
	private String floor;
	
	/**
	 * 车位号
	 */
	private String num;
	
	/**
	 * 车位面积
	 */
	private Double area;
	
	/**
	 * 适宜车型
	 */
	private String suitableCar;
	
	/**
	 * 预租价格
	 */
	private Double preRentPrice;
	
	/**
	 * 预售价格
	 */
	private Double preSalePrice;
	
	/**
	 * 售后车位管理费
	 */
	private Double manageFee;
	
	/**
	 * 状态。0：未租售；1：已租；2：已售
	 */
	private String status;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/******车位租售信息*****/
	
	/**
	 * 车位租售信息id
	 */
	private String parkingRentalId;
	
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getSuitableCar() {
		return suitableCar;
	}

	public void setSuitableCar(String suitableCar) {
		this.suitableCar = suitableCar;
	}

	public Double getPreRentPrice() {
		return preRentPrice;
	}

	public void setPreRentPrice(Double preRentPrice) {
		this.preRentPrice = preRentPrice;
	}

	public Double getPreSalePrice() {
		return preSalePrice;
	}

	public void setPreSalePrice(Double preSalePrice) {
		this.preSalePrice = preSalePrice;
	}

	public Double getManageFee() {
		return manageFee;
	}

	public void setManageFee(Double manageFee) {
		this.manageFee = manageFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParkingRentalId() {
		return parkingRentalId;
	}

	public void setParkingRentalId(String parkingRentalId) {
		this.parkingRentalId = parkingRentalId;
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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
