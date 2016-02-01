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
	
	private Integer roleId;

	private Integer menuId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
