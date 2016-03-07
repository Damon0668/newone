package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseVoValue;

/**
 * 角色--用户
 * @author 蔡少东
 * @date 2016年2月1日
 */
public class SysRoleUserVo extends BaseVoValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4788763942135814904L;
	
	private Long id;
	
	private Long roleId;
	
	private String userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
