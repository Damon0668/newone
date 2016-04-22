package com.liefeng.property.vo.project;

import com.liefeng.core.entity.BaseValue;

/**
 * 项目楼栋楼层值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
public class ProjectBuildingVo extends BaseValue {

	private static final long serialVersionUID = 1533644029413291702L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 父ID，即所属楼栋ID（如果该记录为楼栋，则该字段为空）
	 */
	private String parentId;

	/**
	 * 楼栋楼层编码
	 */
	private String code;

	/**
	 * 楼栋楼层名称
	 */
	private String name;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 标记（非表字段）
	 */
	private String flag;

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
