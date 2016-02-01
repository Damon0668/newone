package com.liefeng.property.domain.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.sys.SysRoleUserRepository;

/**
 * 角色--用户
 * 领域层
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Service
@Scope("prototype")
public class SysRoleUserContext {

	@Autowired
	private SysRoleUserRepository sysRoleUserRepository;
	
	private Long roleId;
	
	private static SysRoleUserContext getInstance() {
		return SpringBeanUtil.getBean(SysRoleUserContext.class);
	}
	
	public static SysRoleUserContext build() {
		SysRoleUserContext sysRoleUserContext = getInstance();
		
		return sysRoleUserContext;
	}
	
	public static SysRoleUserContext loadByRoleId(Long roleId){
		SysRoleUserContext sysRoleUserContext = getInstance();
		sysRoleUserContext.roleId = roleId;
		return sysRoleUserContext;
	}
}
