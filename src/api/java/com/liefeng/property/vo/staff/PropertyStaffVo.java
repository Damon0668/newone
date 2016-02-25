package com.liefeng.property.vo.staff;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 物业员工值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
public class PropertyStaffVo extends BaseValue {

	private static final long serialVersionUID = 4284129283467742818L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 登陆账号
	 */
	private String account;
	
	/**
	 * 登陆密码
	 */
	private String password;
	
	/**
	 * 员工姓名
	 */
	private String name;
	
	/**
	 * 员工工号
	 */
	private String number;
	
	/**
	 * 入职时间
	 */
	private Date entryTime;
	
	/**
	 * 在职状态
	 * 1：在职；2：离职
	 */
	private String workStatus;
	
	/**
	 * 所属部门ID
	 */
	private String departmentId;
	
	/**
	 * 部门信息
	 */
	private PropertyDepartmentVo propertyDepartment;
	/**
	 * 上级领导
	 */
	private String director;
	
	/**
	 * 职位
	 */
	private String position;
	
	/**
	 * 管理的项目
	 * 多个项目用逗号','隔开
	 */
	private String manageProjects;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建者ID
	 */
	private String creatorId;
	
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getManageProjects() {
		return manageProjects;
	}

	public void setManageProjects(String manageProjects) {
		this.manageProjects = manageProjects;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public PropertyDepartmentVo getPropertyDepartment() {
		return propertyDepartment;
	}

	public void setPropertyDepartment(PropertyDepartmentVo propertyDepartment) {
		this.propertyDepartment = propertyDepartment;
	}
}
