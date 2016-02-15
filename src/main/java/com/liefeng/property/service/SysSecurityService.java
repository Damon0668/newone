package com.liefeng.property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.property.domain.sys.SysMenuContext;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.domain.sys.SysRoleMenuContext;
import com.liefeng.property.vo.sys.SysMenuVo;
import com.liefeng.property.vo.sys.SysRoleVo;

@Service
public class SysSecurityService implements ISysSecurityService{

	@Override
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size) {
		return SysRoleContext.build().findRoles(page, size);
	}

	@Override
	public ReturnValue createRole(SysRoleVo sysRole) {
		SysRoleContext.build(sysRole).create();
		return ReturnValue.success();
	}

	@Override
	public ReturnValue delRole(Long id) {
		SysRoleContext.loadById(id).delete();
		return ReturnValue.success();
	}

	@Override
	public List<SysMenuVo> listMenusAndCheck(Long roleId) {
		return SysMenuContext.build().findMenusAndCheck(roleId);
	}

	@Override
	public ReturnValue gruntRoleMenus(Long roleId, String menuIds) {
		SysRoleMenuContext.loadByRoleId(roleId).createMenus(menuIds);
		return ReturnValue.success();
	}

	@Override
	public List<SysMenuVo> listMenuTree() {
		return SysMenuContext.build().findMenuTree();
	}
	
	@Override
	public DataPageValue<SysMenuVo> listMenusIgnoreButton(Long parentId, int page, int size){
		return SysMenuContext.build().findMenusIgnoreButton(parentId, page, size);
	}

	@Override
	public ReturnValue createMenu(SysMenuVo sysMenu) {
		SysMenuContext.build(sysMenu).create();
		return ReturnValue.success();
	}
	

}
