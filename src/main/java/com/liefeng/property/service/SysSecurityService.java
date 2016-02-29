package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.property.domain.sys.SysMenuContext;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.domain.sys.SysRoleMenuContext;
import com.liefeng.property.domain.sys.SysRoleUserContext;
import com.liefeng.property.vo.sys.SysMenuVo;
import com.liefeng.property.vo.sys.SysRoleVo;

/**
 * 系统权限服务
 * @author 蔡少东
 * @date 2016年2月16日
 */
@Service
public class SysSecurityService implements ISysSecurityService{

	@Override
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size) {
		return SysRoleContext.build().findRolesByName4page(name, page, size);
	}

	@Override
	public ReturnValue createRole(SysRoleVo sysRole) {
		SysRoleContext.build(sysRole).create();
		return ReturnValue.success();
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public ReturnValue delRole(Long id) {
		SysRoleContext.loadById(id).delete();
		SysRoleMenuContext.loadByRoleId(id).deleteAll();
		SysRoleUserContext.loadByRoleId(id).deleteAll();
		return ReturnValue.success();
	}

	@Override
	public List<SysMenuVo> listMenusAndCheck(Long roleId) {
		return SysMenuContext.build().findMenusAndCheck(roleId);
	}

	@Override
	public ReturnValue grantRoleMenus(Long roleId, String menuIds) {
		SysRoleMenuContext.loadByRoleId(roleId).createMenus(menuIds);
		return ReturnValue.success();
	}

	@Override
	public List<SysMenuVo> listMenuTree() {
		return SysMenuContext.build().findMenuTree();
	}
	
	@Override
	public DataPageValue<SysMenuVo> listMenus(Long parentId, boolean isIgnoreButton, int page, int size){
		DataPageValue<SysMenuVo> menus = null;
		
		if(isIgnoreButton){
			menus = SysMenuContext.build().findMenusIgnoreButton(parentId, page, size);
		}else{
			menus = SysMenuContext.build().findSubMenu(parentId, page, size);
		}
		
		return menus;
	}

	@Override
	public ReturnValue createMenu(SysMenuVo sysMenu) {
		SysMenuContext sysMenuContext = SysMenuContext.build(sysMenu);
		sysMenuContext.create();
		return ReturnValue.success();
	}
	
	@Override
	public ReturnValue deleteMenus(String[] ids) {
		SysMenuContext.build().delMenus(ids);
		return ReturnValue.success();
	}

	@Override
	public SysMenuVo findMenu(Long menuId) {
		return SysMenuContext.loadById(menuId).getMenu();
	}

	@Override
	public SysMenuVo findMenuByCode(String code) {
		return SysMenuContext.loadByCode(code).getMenu();
	}

	@Override
	public DataPageValue<SysMenuVo> listButtons(Long parentId, int page, int size) {
		return SysMenuContext.build().findButtons(parentId, page, size);
	}

	@Override
	public ReturnValue updateMenu(SysMenuVo sysMenu) {
		SysMenuContext.build(sysMenu).update();
		return ReturnValue.success();
	}

	@Override
	public List<SysRoleVo> listAllRoles() {
		return SysRoleContext.build().findAll();
	}

	@Override
	public ReturnValue grantRoleUser(String userId, Long[] roleIds) {
		SysRoleUserContext.loadByUserId(userId).grantRoles(roleIds);
		return ReturnValue.success();
	}

	@Override
	public ReturnValue updateRole(SysRoleVo sysRole) {
		SysRoleContext.build(sysRole).update();
		return ReturnValue.success();
	}

	@Override
	public List<String> listButtonsCodeByUserId(String userId) {
		return SysMenuContext.build().findButtonsCodeByUserId(userId);
	}

	@Override
	public List<SysMenuVo> listMenusByUserId(String userId) {
		return SysMenuContext.build().findMenusByUserId(userId);
	}

	@Override
	public List<SysRoleVo> findRolesByUserId(String userId) {
		return SysRoleContext.build().findRolesByUserId(userId);
	}
	

}
