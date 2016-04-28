package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁模块
 * 员工查询参数封装
 * @author 蔡少东
 * @date 2016年3月7日
 */
public class GuardStaffBo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8583522464189298008L;

	private String projectId;
	
	private String deptId;
	
	private String workStatus;
	
	private String staffName;
	
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
