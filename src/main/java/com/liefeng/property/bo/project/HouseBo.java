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
	
	private String proprietorName;
	
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

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
}
