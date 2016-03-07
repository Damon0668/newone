package com.liefeng.property.vo.parking;

import com.liefeng.core.entity.BaseValue;

/**
 * 车位信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
public class ParkingVo extends BaseValue {

	private static final long serialVersionUID = 4459613393376513642L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 楼栋ID
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
	 * 状态
	 * 0：未租售；1：已租；2：已售
	 */
	private String status;

	/**
	 * OEM编码
	 */
	private String oemCode;

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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
