package com.liefeng.property.bo.guard;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁设备查询参数
 * @author 蔡少东
 * @date 2016年3月1日
 */
public class GuardDeviceBo extends BaseValue{
	
	private static final long serialVersionUID = -3534863940338880953L;

	private String projectId;
	
	private List<String> projectIds;
	
	private String guardName;
	
	private String position;
	
	private String runStatus;
	
	/**
	 * 系统标识
	 */
	private String oemCode;
	
	/**
	 * 设备类型
	 */
	private String deviceType;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	public String getGuardName() {
		return guardName;
	}

	public void setGuardName(String guardName) {
		this.guardName = guardName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
