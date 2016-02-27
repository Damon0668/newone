package com.liefeng.property.bo.fee;

import com.liefeng.core.entity.BaseValue;

public class FeeItemBo extends BaseValue {

	private static final long serialVersionUID = 1686086443428551319L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

	/**
	 * 房号
	 */
	private String houseNum;
	
	/**
	 * 缴费状态 
	 * 0：未交费；1：已正常缴费；2：过期缴费。
	 */
	private String status;
	
	/**
	 * 业主名称
	 */
	private String proprietorName;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}
}
