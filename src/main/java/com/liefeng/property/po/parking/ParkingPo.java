package com.liefeng.property.po.parking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 车位信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Entity
@Table(name = "t_parking")
public class ParkingPo extends BaseValue {

	private static final long serialVersionUID = -4904420370558927566L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	@Column(name = "building_id")
	private String buildingId;

	/**
	 * 车位编码
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 车位楼层
	 */
	@Column(name = "floor")
	private String floor;

	/**
	 * 车位号
	 */
	@Column(name = "num")
	private String num;

	/**
	 * 车位面积
	 */
	@Column(name = "area")
	private Double area;

	/**
	 * 适宜车型
	 */
	@Column(name = "suitable_car")
	private String suitableCar;

	/**
	 * 预租价格
	 */
	@Column(name = "pre_rent_price")
	private Double preRentPrice;

	/**
	 * 预售价格
	 */
	@Column(name = "pre_sale_price")
	private Double preSalePrice;

	/**
	 * 售后车位管理费
	 */
	@Column(name = "manage_fee")
	private Double manageFee;

	/**
	 * 状态
	 * 0：未租售；1：已租；2：已售
	 */
	@Column(name = "status")
	private String status;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
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
