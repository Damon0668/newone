package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主业务查询参数封装类
 * 
 * @author ZhenTingJun
 * @date 2015-12-31
 */
public class ProprietorBo extends BaseValue {

	private static final long serialVersionUID = -2355844351653574093L;
	
	/**
	 * 业主ID
	 */
	private String proprietorId;
	
	/**
	 * 业主名称
	 */
	private String proprietorName;
	
	/**
	 * 业主房产ID
	 */
	private String proprietorHouseId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 楼层ID
	 */
	private String floorId;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 房号编码
	 */
	private String houseNum;
	
	/**
	 * 手机号码
	 */
	private String phone;

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getProprietorHouseId() {
		return proprietorHouseId;
	}

	public void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
