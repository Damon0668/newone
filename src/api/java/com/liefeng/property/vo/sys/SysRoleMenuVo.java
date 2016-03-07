package com.liefeng.property.vo.sys;

import com.liefeng.core.entity.BaseVoValue;

/**
 * 角色--菜单
 * @author 蔡少东
 * @date 2016年2月1日
 */
public class SysRoleMenuVo extends BaseVoValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3242054804913327639L;

	private Long id;
	
	private Long roleId;

	private Long menuId;

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

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
}
