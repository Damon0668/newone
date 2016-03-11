package com.liefeng.property.bo.project;

import com.liefeng.core.entity.BaseValue;

public class HouseSpecBo extends BaseValue {

	private static final long serialVersionUID = 8070405821527938546L;

	/**
	 * 系统标识
	 */
	private String oemCode;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

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
}
