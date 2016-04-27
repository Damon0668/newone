package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 车辆信息查询参数封装类
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
public class CarInfoBo extends BaseValue {

	private static final long serialVersionUID = -4278861483126993382L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 项目ID串
	 */
	private String projectIds;

	/**
	 * 车牌号
	 */
	private String plateNum;

	/**
	 * 车辆型号
	 */
	private String vehicleType;

	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
