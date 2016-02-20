package com.liefeng.property.po.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 字典组持久化对象
 * @author Huangama
 * @date 2016-2-19
 */
@Entity
@Table(name = "t_sys_dict_group")
public class SysDictGroupPo extends BaseValue {

	private static final long serialVersionUID = -4028062992476390096L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 字典组编码
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * 字典组名称
	 */
	@Column(name = "name")
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
