package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
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
	
	private static final Logger logger = LoggerFactory.getLogger(SysSecurityService.class);
	
	@Override
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size) {
		return SysRoleContext.build().findRolesByName4page(name, page, size);
	}

	@Override
	public void createRole(SysRoleVo sysRole) throws LiefengException{
		SysRoleContext.build(sysRole).create();
	}

	@Transactional(rollbackOn=Exception.class)
	@Override
	public void delRole(Long id) {
		SysRoleContext.loadById(id).delete();
		SysRoleMenuContext.loadByRoleId(id).deleteAll();
		SysRoleUserContext.loadByRoleId(id).deleteAll();
	}

	@Override
	public List<SysMenuVo> listMenusAndCheck(Long roleId) {
		logger.info("listMenusAndCheck roleId = {}", roleId);
		return SysMenuContext.build().findMenusAndCheck(roleId);
	}

	@Override
	public void grantRoleMenus(Long roleId, String menuIds) {
		SysRoleMenuContext.loadByRoleId(roleId).createMenus(menuIds);
	}

	@Override
	public List<SysMenuVo> listMenuTree() {
		return SysMenuContext.build().findMenuTree();
	}
	
	@Override
	public DataPageValue<SysMenuVo> listMenus(Long parentId, boolean isIgnoreButton, int page, int size){
		
		parentId = parentId == null ? 0 : parentId;
		
		DataPageValue<SysMenuVo> menus = null;
		
		if(isIgnoreButton){
			menus = SysMenuContext.build().findMenusIgnoreButton(parentId, page, size);
		}else{
			menus = SysMenuContext.build().findSubMenu(parentId, page, size);
		}
		
		return menus;
	}

	@Override
	public void createMenu(SysMenuVo sysMenu) {
		SysMenuContext sysMenuContext = SysMenuContext.build(sysMenu);
		sysMenuContext.create();
	}
	
	@Override
	public void deleteMenus(String[] ids) {
		SysMenuContext.build().delMenus(ids);
	}

	@Override
	public SysMenuVo findMenu(Long menuId) {
		
		menuId = menuId == null ? 0L : menuId;
		
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
	public void updateMenu(SysMenuVo sysMenu) {
		SysMenuContext.build(sysMenu).update();
	}

	@Override
	public List<SysRoleVo> listAllRoles() {
		return SysRoleContext.build().findAll();
	}

	@Override
	public void grantRoleUser(String userId, Long[] roleIds) {
		SysRoleUserContext.loadByUserId(userId).grantRoles(roleIds);
	}

	@Override
	public void updateRole(SysRoleVo sysRole) throws LiefengException{
		SysRoleContext.build(sysRole).update();
	}

	@Override
	public List<String> listButtonsCodeByUserId(String userId) {
		return SysMenuContext.build().findButtonsCodeByUserId(userId);
	}

	@Override
	public List<SysMenuVo> listMenusByUserId(String userId) {
		logger.info("listMenusByUserId userId = {}", userId);
		return SysMenuContext.build().findMenusByUserId(userId);
	}

	@Override
	public List<SysRoleVo> findRolesByUserId(String userId) {
		logger.info("findRolesByUserId userId = {}", userId);
		return SysRoleContext.build().findRolesByUserId(userId);
	}

	@Override
	public SysRoleVo findRoleByRoleId(Long roleId) {
		logger.info("findRolesByRoleId roleId = {}", roleId);
		return SysRoleContext.loadById(roleId).get();
	}
	

}
