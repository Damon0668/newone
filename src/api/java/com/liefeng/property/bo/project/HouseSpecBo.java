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
	
	/**
	 * 楼层ID
	 */
	private String floorId;
	
	/**
	 * 标识
	 */
	private String flag;

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

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
