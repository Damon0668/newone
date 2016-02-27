package com.liefeng.property.vo.staff;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.core.entity.BaseValue;

/**
 * 物业员工详细信息
 * @author 蔡少东
 * @date 2016年2月22日
 */
public class PropertyStaffDetailInfoVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7345349887080603663L;

	/**
	 * 员工信息
	 */
	public PropertyStaffVo propertyStaffVo;
	
	/**
	 * 员工档案
	 */
	public StaffArchiveVo staffArchiveVo;
	
	/**
	 * 用户信息
	 */
	public CustomerVo customerVo;
	
	/**
	 * 角色ID数组
	 */
	public Long[] roleIds;
	
	/**
	 * 员工管理相关项目ID
	 */
	public String[] manageProjects;
	
	/**
	 * 员工通讯录中部门ID
	 */
	public String[] contactProjects;

	public PropertyStaffVo getPropertyStaffVo() {
		return propertyStaffVo;
	}

	public void setPropertyStaffVo(PropertyStaffVo propertyStaffVo) {
		this.propertyStaffVo = propertyStaffVo;
	}

	public StaffArchiveVo getStaffArchiveVo() {
		return staffArchiveVo;
	}

	public void setStaffArchiveVo(StaffArchiveVo staffArchiveVo) {
		this.staffArchiveVo = staffArchiveVo;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getManageProjects() {
		return manageProjects;
	}

	public void setManageProjects(String[] manageProjects) {
		this.manageProjects = manageProjects;
	}

	public String[] getContactProjects() {
		return contactProjects;
	}

	public void setContactProjects(String[] contactProjects) {
		this.contactProjects = contactProjects;
	}

}
