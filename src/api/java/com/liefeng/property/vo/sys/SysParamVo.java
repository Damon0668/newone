package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseValue;

/**
 * 系统参数对象
 * @author Huangama
 * @date 2016-2-19
 */
public class SysParamVo extends BaseValue {

	private static final long serialVersionUID = -9147843685996689522L;

	private Long id;
	
	/**
	 * 参数名称
	 */
	private String name;
	
	/**
	 * 参数编码
	 */
	private String code;
	
	/**
	 * 参数值
	 */
	private String value;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
