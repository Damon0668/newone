package com.liefeng.property.domain.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.po.sys.SysRoleMenuPo;
import com.liefeng.property.repository.sys.SysRoleMenuRepository;
import com.liefeng.property.vo.sys.SysRoleMenuVo;

/**
 * 角色--菜单
 * 领域层
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Service
@Scope("prototype")
public class SysRoleMenuContext {

	@Autowired
	private SysRoleMenuRepository sysRoleMenuRepository;
	
	private Long roleId;
	
	private SysRoleMenuVo sysRoleMenu;

	private static SysRoleMenuContext getInstance() {
		return SpringBeanUtil.getBean(SysRoleMenuContext.class);
	}
	
	public static SysRoleMenuContext build() {
		SysRoleMenuContext sysRoleMenuContext = getInstance();
		
		return sysRoleMenuContext;
	}
	
	public static SysRoleMenuContext loadByRoleId(Long roleId){
		SysRoleMenuContext sysRoleMenuContext = getInstance();
		sysRoleMenuContext.roleId = roleId;
		return sysRoleMenuContext;
	}
	
	/**
	 * 删除所有菜单
	 */
	public void deleteAllMenu(){
		sysRoleMenuRepository.deleteAllInBatch();
	}
	
	/**
	 * 创建菜单
	 * ids以,分割
	 */
	public void createMenus(String menuIds){
		deleteAllMenu();
		String oemCode = SysRoleContext.loadById(roleId).getRole().getOemCode();
		String[] menusArray = menuIds.split(",");
		List<SysRoleMenuPo> roleMenus = new ArrayList<SysRoleMenuPo>();
		for (int i = 0; i < menusArray.length; i++) {
			SysRoleMenuPo sysRoleMenuPo= new SysRoleMenuPo();
			sysRoleMenuPo.setRoleId(roleId);
			sysRoleMenuPo.setMenuId(Long.parseLong(menusArray[i]));
			sysRoleMenuPo.setOemCode(oemCode);
			roleMenus.add(sysRoleMenuPo);
		}
		sysRoleMenuRepository.save(roleMenus);
	}
	
	/**
	 * 根据角色ID,查询被选中菜单ID
	 * 
	 * @return
	 */
	public List<Long> findSelectedMenuIdsByRoleId(){
		List<Long> menuIds = null;
		
		if(roleId != null){
			
			List<SysRoleMenuPo> sysRoleMenuList = sysRoleMenuRepository.findByRoleId(roleId);
			
			if(!sysRoleMenuList.isEmpty()){
				menuIds = new ArrayList<Long>();
			}
			
			for (SysRoleMenuPo sysRoleMenuPo : sysRoleMenuList) {
				menuIds.add(sysRoleMenuPo.getMenuId());
			}
			
		}
		
		return menuIds;
	}
	
	public SysRoleMenuVo getSysRoleMenu() {
		return sysRoleMenu;
	}

	public void setSysRoleMenu(SysRoleMenuVo sysRoleMenu) {
		this.sysRoleMenu = sysRoleMenu;
	}
}
