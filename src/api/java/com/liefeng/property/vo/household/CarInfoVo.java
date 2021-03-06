package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

/**
 * 车辆信息值对象
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
public class CarInfoVo extends BaseValue {

	private static final long serialVersionUID = -5921285460600945715L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 所有人ID。当为业主时，对应业主表ID；当为住户时，对应住户表ID；当为员工时，对应员工表ID
	 */
	private String ownerId;

	/**
	 * 所有人名字。该字段为冗余的
	 */
	private String ownerName;

	/**
	 * 所有人类型。1：业主；2：住户；3：员工
	 */
	private String ownerType;

	/**
	 * 房号
	 */
	private String houseNum;
	
	/**
	 * 员工工号
	 */
	private String staffNum;

	/**
	 * 车牌号
	 */
	private String plateNum;

	/**
	 * 车辆类型。取'SUITABLE_CAR'字典
	 */
	@Dict(group = "SUITABLE_CAR")
	private String vehicleType;
	
	/**
	 * 该字段的值与vehicleType字段相同
	 * 由于返回数据时，vehicleType字段会做字典【值-名】处理
	 * 故用此字段保留vehicleType字段原值，用于前端页面使用
	 */
	private String realValue;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 颜色
	 */
	private String color;
	
	/**
	 * 录入员工ID
	 */
	private String staffId;
	
	/**
	 * 录入员工名字
	 */
	private String staffName;
	
	/**
	 * 录入时间
	 */
	private Date createTime;

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
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

	public String getRealValue() {
		return realValue;
	}

	public void setRealValue(String realValue) {
		this.realValue = realValue;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
