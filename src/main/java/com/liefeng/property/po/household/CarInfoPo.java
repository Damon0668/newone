package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 车辆信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
@Entity
@Table(name = "t_car_info")
public class CarInfoPo extends BaseValue {

	private static final long serialVersionUID = 1514751165166109516L;

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
	 * 所有人ID。当为业主时，对应业主表ID；当为住户时，对应住户表ID；当为员工时，对应员工表ID
	 */
	@Column(name = "owner_id")
	private String ownerId;

	/**
	 * 所有人名字。该字段为冗余的
	 */
	@Column(name = "owner_name")
	private String ownerName;

	/**
	 * 所有人类型。1：业主；2：住户；3：员工
	 */
	@Column(name = "owner_type")
	private String ownerType;

	/**
	 * 房号
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 车牌号
	 */
	@Column(name = "plate_num")
	private String plateNum;

	/**
	 * 车辆类型。取'SUITABLE_CAR'字典
	 */
	@Column(name = "vehicle_type")
	private String vehicleType;

	/**
	 * 品牌
	 */
	@Column(name = "brand")
	private String brand;

	/**
	 * 颜色
	 */
	@Column(name = "color")
	private String color;
	
	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 录入时间
	 */
	@Column(name = "create_time")
	private Date createTime;

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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
