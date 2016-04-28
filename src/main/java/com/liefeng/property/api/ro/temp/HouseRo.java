package com.liefeng.property.api.ro.temp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 房产信息值对象
 * 
 * @author wuzhijing
 */
@ApiModel
public class HouseRo extends BaseValue {

	private static final long serialVersionUID = -4833119927413936743L;


	/**
	 * 房产编码：楼栋编码+楼层编码+房号后缀
	 */
	@ApiModelProperty(value="房间号", required=true)
	@NotNull
	private String houseNum;

	/**
	 * 项目ID
	 */
	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;
	
	/**
	 * 楼栋名称
	 */
	@ApiModelProperty(value="项目ID")
	private String buildingName;

	
	/**
	 * 楼层名称
	 */
	@ApiModelProperty(value="楼层名称")
	private String floorName;

	/**
	 * 户型
	 */
	@ApiModelProperty(value="户型")
	private String houseType;

	/**
	 * 实际面积
	 */
	@ApiModelProperty(value="实际面积")
	private Double actualArea;

	/**
	 * 建筑面积
	 */
	@ApiModelProperty(value="建筑面积")
	private Double grossArea;

	/**
	 * 使用面积
	 */
	@ApiModelProperty(value="使用面积")
	private Double usableArea;

	/**
	 * 分摊面积
	 */
	@ApiModelProperty(value="分摊面积")
	private Double apportionArea;

	/**
	 * 物业管理费单价
	 */
	@ApiModelProperty(value="物业管理费单价")
	private Double propertyFee;

	/**
	 * 销售状态
	 * 0：未出售 1：待售 2：已售
	 */
	@ApiModelProperty(value="销售状态[0：未出售 1：待售 2：已售]")
	private String saleStatus;

	/**
	 * 产权归属
	 * 1：商品房；2：小产权；3：大产权；4：集资房；5：安居房。
	 */
	@ApiModelProperty(value="产权归属[1：商品房；2：小产权；3：大产权；4：集资房；5：安居房]")
	private String ownership;

	/**
	 * 录入员工ID
	 */
	@ApiModelProperty(value="录入员工ID")
	private String staffId;

	/**
	 * 登记时间
	 */
	@ApiModelProperty(value="登记时间")
	private Date registerTime;
	
	/**
	 * 楼栋编码
	 */
	@ApiModelProperty(value="楼栋编码")
	private String buildingCode;
	
	/**
	 * 楼层编码
	 */
	@ApiModelProperty(value="楼层编码")
	private String floorCode;


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

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

}
