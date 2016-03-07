package com.liefeng.property.bo.parking;

import com.liefeng.core.entity.BaseValue;

public class ParkingBo extends BaseValue {

	private static final long serialVersionUID = -8431876361667278690L;

	private String projectId;

	private String customerName;

	private String status;

	private String buildingId;
	
	private String num;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
}
