package com.liefeng.property.vo.project;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 房产信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
public class HouseVo extends BaseValue {

	private static final long serialVersionUID = -4833119927413936743L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 房产编码：楼栋编码+楼层编码+房号后缀
	 */
	private String houseNum;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 楼栋名称
	 */
	private String buildingName;

	/**
	 * 楼层ID
	 */
	private String floorId;
	
	/**
	 * 楼层名称
	 */
	private String floorName;

	/**
	 * 户型
	 */
	private String houseType;

	/**
	 * 实际面积
	 */
	private Double actualArea;

	/**
	 * 建筑面积
	 */
	private Double grossArea;

	/**
	 * 使用面积
	 */
	private Double usableArea;

	/**
	 * 分摊面积
	 */
	private Double apportionArea;

	/**
	 * 物业管理费单价
	 */
	private Double propertyFee;

	/**
	 * 销售状态
	 * 0：未出售 1：待售 2：已售
	 */
	private String saleStatus;

	/**
	 * 产权归属
	 * 1：商品房；2：小产权；3：大产权；4：集资房；5：安居房。
	 */
	private String ownership;

	/**
	 * 录入员工ID
	 */
	private String staffId;

	/**
	 * 登记时间
	 */
	private Date registerTime;

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

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
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

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Double getActualArea() {
		return actualArea;
	}

	public void setActualArea(Double actualArea) {
		this.actualArea = actualArea;
	}

	public Double getGrossArea() {
		return grossArea;
	}

	public void setGrossArea(Double grossArea) {
		this.grossArea = grossArea;
	}

	public Double getUsableArea() {
		return usableArea;
	}

	public void setUsableArea(Double usableArea) {
		this.usableArea = usableArea;
	}

	public Double getApportionArea() {
		return apportionArea;
	}

	public void setApportionArea(Double apportionArea) {
		this.apportionArea = apportionArea;
	}

	public Double getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(Double propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

}
