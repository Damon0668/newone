package com.liefeng.property.vo.staff;

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
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 部门电话
	 */
	private String tel;
	
	/**
	 * 负责人ID
	 */
	private String directorId;
	
	/**
	 * 负责人
	 */
	private PropertyStaffVo director;
	
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

}
