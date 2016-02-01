package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseVoValue;

/**
 * 系统角色
 * @author 蔡少东
 * @date 2016年2月1日
 */
public class SysRoleVo extends BaseVoValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951984493359421224L;

	private Long id;
	
	private String name;
	
	private String desc;
	
	private String type;
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
