package com.liefeng.property.vo.staff;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工通讯录权限
 * @author 蔡少东
 * @date 2016年2月27日
 */
public class StaffContactPrivilegeVo extends BaseValue{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8006692475251834654L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 部门ID
	 */
	private String departmentId;

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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
