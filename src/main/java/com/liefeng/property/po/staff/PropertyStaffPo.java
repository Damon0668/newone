package com.liefeng.property.po.staff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 物业员工持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_property_staff")
public class PropertyStaffPo extends BaseValue {

	private static final long serialVersionUID = -5024971470570292948L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 登陆账号
	 */
	@Column(name = "account")
	private String account;
	
	/**
	 * 登陆密码
	 */
	@Column(name = "password")
	private String password;
	
	/**
	 * 员工姓名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 员工工号
	 */
	@Column(name = "number")
	private String number;
	
	/**
	 * 入职时间
	 */
	@Column(name = "entry_time")
	private Date entryTime;
	
	/**
	 * 在职状态
	 * 1：在职；2：离职
	 */
	@Column(name = "work_status")
	private String workStatus;
	
	/**
	 * 所属项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 所属部门ID
	 */
	@Column(name = "department_id")
	private String departmentId;

	/**
	 * 职位
	 */
	@Column(name = "position")
	private String position;
	
	/**
	 * 管理的项目
	 * 多个项目用逗号','隔开
	 */
	@Column(name = "manage_projects")
	private String manageProjects;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 创建者ID
	 */
	@Column(name = "creator_id")
	private String creatorId;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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
	
}
