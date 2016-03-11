package com.liefeng.property.po.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 系统参数持久化对象
 * @author Huangama
 * @date 2016-2-19
 */
@Entity
@Table(name = "t_sys_param")
public class SysParamPo extends BaseValue {

	private static final long serialVersionUID = -2362638471290090991L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 参数名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 参数编码
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * 参数值
	 */
	@Column(name = "value")
	private String value;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
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
