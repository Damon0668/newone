package com.liefeng.property.vo.staff;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工管理项目
 * 
 * @author 蔡少东
 * @date 2016年2月23日
 */
public class ManageProjectVo extends BaseValue{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8691418207197622343L;

	private String id;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
}
