package com.liefeng.property.bo.project;

import com.liefeng.core.entity.BaseValue;

public class HouseBo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8431876361667278690L;

	private String oemCode;
	
	private String projectId;
	
	private String buildingId;
	
	private String houseNum;
	
	/**
	 * 业主名字，产权人
	 */
	private String name;
	
	private String payType;

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
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

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
