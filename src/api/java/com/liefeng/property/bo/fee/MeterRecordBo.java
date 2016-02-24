package com.liefeng.property.bo.fee;


import com.liefeng.core.entity.BaseValue;

public class MeterRecordBo extends BaseValue {
	
	private static final long serialVersionUID = -5140958747627490287L;

	/**
	 * 所属公司id
	 */
	private String propertyId;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

	/**
	 * 楼层ID
	 */
	private String floorId;

	/**
	 * 房号
	 */
	private String houseNum;

	/**
	 * 表类型
	 * 1：水表；2：电表；3：燃气表
	 */
	private String meterType;

	/**
	 * 表属主
	 * 1：业主表；2：公摊表
	 */
	private String meterOwner;

	/**
	 * 业主名称
	 */
	private String proprietorName;

	/**
	 * 费用所属开始日期
	 */
	private String startDate;

	/**
	 * 数据来源
	 * 1：WEB；2：Android；3：IOS
	 */
	private String from;

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
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

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getMeterOwner() {
		return meterOwner;
	}

	public void setMeterOwner(String meterOwner) {
		this.meterOwner = meterOwner;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
