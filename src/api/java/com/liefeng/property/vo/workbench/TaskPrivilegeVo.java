package com.liefeng.property.vo.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.liefeng.core.entity.BaseValue;

/**
 * 任务权限对象
 * @author xhw
 * @date 2016年2月22日下午3:39:54
 */
public class TaskPrivilegeVo extends BaseValue{

	private static final long serialVersionUID = 140069505301606742L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 任务ID
	 */
	private String taskId;
	
	/**
	 * 项目ID。-1表示所有项目都可见
	 */
	private String projectId;
	
	/**
	 * 部门ID。-1表示所有该项目下所有部门都可见
	 */
	private String deptId;
	
	/**
	 * 员工ID。-1表示所有该部门下所有员工都可见
	 */
	private String staffId;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
	
}
