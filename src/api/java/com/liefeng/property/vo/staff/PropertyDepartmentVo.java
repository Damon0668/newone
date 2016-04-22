package com.liefeng.property.vo.staff;

import javax.persistence.Column;

import com.liefeng.core.entity.BaseValue;

/**
 * 部门信息值对象
 * 
 * @author ZhenTingJun
 * @author 蔡少东
 * @date 2015-12-24
 */
public class PropertyDepartmentVo extends BaseValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4443957278734231695L;

	/**
	 * 主键
	 */
	private String Id;
	

	/**
	 * 父部门ID
	 */
	private String parentId;
	
	/**
	 * 父部门名称
	 */
	private String parentName;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目
	 */
	private String projectName;
	
	/**
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 部门类型
	 */
	private String deptType;
	
	/**
	 * 部门类型名字
	 */
	private String deptTypeName;
	
	/**
	 * 部门电话
	 */
	private String tel;
	
	/**
	 * 负责人ID
	 */
	private String directorId;
	
	/**
	 * 负责人2ID
	 */
	private String director2Id;
	
	/**
	 * 负责人
	 */
	private PropertyStaffVo director;
	
	/**
	 * 负责人2
	 */
	private PropertyStaffVo director2;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 部门中人员数量
	 */
	private Long count;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDirectorId() {
		return directorId;
	}

	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public PropertyStaffVo getDirector() {
		return director;
	}

	public void setDirector(PropertyStaffVo director) {
		this.director = director;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDirector2Id() {
		return director2Id;
	}

	public void setDirector2Id(String director2Id) {
		this.director2Id = director2Id;
	}

	public PropertyStaffVo getDirector2() {
		return director2;
	}

	public void setDirector2(PropertyStaffVo director2) {
		this.director2 = director2;
	}

	public String getDeptTypeName() {
		return deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
