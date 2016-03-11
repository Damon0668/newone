package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseValue;

/**
 * 字典对象
 * @author Huangama
 * @date 2016-2-19
 */
public class SysDictVo extends BaseValue {

	private static final long serialVersionUID = 7822997289281212266L;

	private Long id;
	
	/**
	 * 字典组编码
	 */
	private String groupCode;
	
	/**
	 * 字典名称
	 */
	private String name;
	
	/**
	 * 字典值
	 */
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
