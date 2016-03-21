package com.liefeng.property.vo.staff;

import java.util.List;

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
	private static final long serialVersionUID = 6339014585411695312L;

	/**
	 * 员工信息
	 */
	private PropertyStaffVo propertyStaffVo;
	
	/**
	 * 员工档案
	 */
	private StaffArchiveVo staffArchiveVo;
	
	/**
	 * 用户信息
	 */
	private CustomerVo customerVo;
	
	/**
	 * 角色ID数组
	 */
	private Long[] roleIds;
	
	/**
	 * 员工管理相关项目ID
	 */
	private String[] manageProjects;
	
	/**
	 * 员工通讯录中部门ID
	 */
	private String[] contactProjects;
	
	/**
	 * 员工附件
	 */
	private List<StaffAttachVo> staffAttachs;
	
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

	public List<StaffAttachVo> getStaffAttachs() {
		return staffAttachs;
	}

	public void setStaffAttachs(List<StaffAttachVo> staffAttachs) {
		this.staffAttachs = staffAttachs;
	}

}
