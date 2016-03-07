package com.liefeng.property.po.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 房产信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Entity
@Table(name = "t_house")
public class HousePo extends BaseValue {

	private static final long serialVersionUID = -7087491406767387933L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 房产编码：楼栋编码+楼层编码+房号后缀
	 */
	@Column(name = "house_num")
	private String houseNum;

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
	 * 楼层ID
	 */
	@Column(name = "floor_id")
	private String floorId;

	/**
	 * 户型
	 */
	@Column(name = "house_type")
	private String houseType;

	/**
	 * 实际面积
	 */
	@Column(name = "actual_area")
	private Double actualArea;

	/**
	 * 建筑面积
	 */
	@Column(name = "gross_area")
	private Double grossArea;

	/**
	 * 使用面积
	 */
	@Column(name = "usable_area")
	private Double usableArea;

	/**
	 * 分摊面积
	 */
	@Column(name = "apportion_area")
	private Double apportionArea;

	/**
	 * 物业管理费单价
	 */
	@Column(name = "property_fee")
	private Double propertyFee;

	/**
	 * 销售状态
	 * 0：未出售 1：待售 2：已售
	 */
	@Column(name = "sale_status")
	private String saleStatus;

	/**
	 * 产权归属
	 * 1：商品房；2：小产权；3：大产权；4：集资房；5：安居房。
	 */
	@Column(name = "ownership")
	private String ownership;

	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * 登记时间
	 */
	@Column(name = "register_time")
	private Date registerTime;

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

}
