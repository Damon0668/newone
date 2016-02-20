package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseValue;

/**
 * 字典组对象
 * @author Huangama
 * @date 2016-2-19
 */
public class SysDictGroupVo extends BaseValue {

	private static final long serialVersionUID = 8885068929862998740L;

	private Long id;
	
	/**
	 * 字典组编码
	 */
	private String code;
	
	/**
	 * 字典组名称
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
