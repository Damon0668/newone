package com.liefeng.property.po.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工--项目持久化对象
 * 
 * @author 蔡少东
 * @date 2016年2月23日
 */
@Entity
@Table(name = "t_manage_project")
public class ManageProjectPo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1312402566987319840L;
	
	@Id
	private String id;
	
	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

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
}
