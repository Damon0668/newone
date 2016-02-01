package com.liefeng.property.domain.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
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
}
