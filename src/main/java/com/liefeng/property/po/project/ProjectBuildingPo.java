package com.liefeng.property.po.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 项目楼栋楼层持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Entity
@Table(name = "t_project_building")
public class ProjectBuildingPo extends BaseValue {

	private static final long serialVersionUID = -4699728900878024445L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 父ID，即所属楼栋ID（如果该记录为楼栋，则该字段为空）
	 */
	@Column(name = "parent_id")
	private String parentId;

	/**
	 * 楼栋楼层编码
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 楼栋楼层名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
