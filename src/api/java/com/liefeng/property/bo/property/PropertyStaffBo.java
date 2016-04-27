package com.liefeng.property.bo.property;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 物业员工值对象
 * 
 * @author 蔡少东
 * @data 2016-02-24
 */
public class PropertyStaffBo extends BaseValue {

	private static final long serialVersionUID = 4284129283467742818L;
	
	/**
	 * 员工ID/名字
	 */
	private String idOrName;
	
	/**
	 * 部门ID
	 */
	private String departmentId;
	
	/**
	 * 在职状态
	 */
	private String workStatus;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目ID
	 */
	private List<String> projectIds;
	
	/**
	 * oemCode租户编码
	 */
	private String oemCode;

	public String getIdOrName() {
		return idOrName;
	}

	public void setIdOrName(String idOrName) {
		this.idOrName = idOrName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
}
