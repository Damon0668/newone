package com.liefeng.property.vo.staff;

import com.liefeng.core.entity.BaseValue;

/**
 * 审批流程专用获取员工信息
 * @author wuzhijing
 *
 */
public class StaffWorkFlowUseVo extends BaseValue{

	private static final long serialVersionUID = -204693733012035820L;
	
	/**
	 * 员工姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 部门名称
	 */
	private String departmentName;
	
	/**
	 * 岗位名称
	 */
	private String positionName;
	
	/**
	 * 入职时间
	 */
	private String entryTime;
	
	/**
	 * 管理项目
	 */
	private String projectName;

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
