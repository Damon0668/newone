package com.liefeng.property.po.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工通讯录权限
 * @author 蔡少东
 * @date 2016年2月27日
 */
@Entity
@Table(name = "t_staff_contact_privilege")
public class StaffContactPrivilegePo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -593460273376838042L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 部门ID
	 */
	@Column(name = "department_id")
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
