package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁设备查询参数
 * @author 蔡少东
 * @date 2016年3月1日
 */
public class GuardDeviceBo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3534863940338880953L;

	private String projectId;
	
	private String installPosition;
	
	private String guardNum;
	
	private String status;
	
	private String runStatus;
	
	/**
	 * 系统标识
	 */
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getInstallPosition() {
		return installPosition;
	}

	public void setInstallPosition(String installPosition) {
		this.installPosition = installPosition;
	}

	public String getGuardNum() {
		return guardNum;
	}

	public void setGuardNum(String guardNum) {
		this.guardNum = guardNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}