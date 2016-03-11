package com.liefeng.property.po.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 部门信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_property_department")
public class PropertyDepartmentPo extends BaseValue {

	private static final long serialVersionUID = -1337881439423936862L;
	
	/**
	 * 主键
	 */
	@Id
	private String Id;
	
	/**
	 * 部门名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 部门电话
	 */
	@Column(name = "tel")
	private String tel;
	
	/**
	 * 负责人ID
	 */
	@Column(name = "director_id")
	private String directorId;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;

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
}
