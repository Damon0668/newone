package com.liefeng.property.po.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 字典持久化对象
 * @author Huangama
 * @date 2016-2-19
 */
@Entity
@Table(name = "t_sys_dict")
public class SysDictPo extends BaseValue {

	private static final long serialVersionUID = -6279147571135986990L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 字典组编码
	 */
	@Column(name = "group_code")
	private String groupCode;
	
	/**
	 * 字典名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 字典值
	 */
	@Column(name = "value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
