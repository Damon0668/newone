
package com.liefeng.property.domain.sys;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.po.sys.SysRoleUserPo;
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
	
	private String userId;
	
	protected void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	protected void setUserId(String userId) {
		this.userId = userId;
	}

	private static SysRoleUserContext getInstance() {
		return SpringBeanUtil.getBean(SysRoleUserContext.class);
	}
	
	public static SysRoleUserContext build() {
		SysRoleUserContext sysRoleUserContext = getInstance();
		
		return sysRoleUserContext;
	}
	
	public static SysRoleUserContext loadByRoleId(Long roleId){
		SysRoleUserContext sysRoleUserContext = getInstance();
		sysRoleUserContext.setRoleId(roleId);
		return sysRoleUserContext;
	}
	
	public static SysRoleUserContext loadByUserId(String userId){
		SysRoleUserContext sysRoleUserContext = getInstance();
		sysRoleUserContext.setUserId(userId);
		return sysRoleUserContext;
	}
	
	/**
	 * 授权用户角色
	 * @param roleIds
	 */
	@Transactional(rollbackOn=Exception.class)
	public void gruntRoles(Long[] roleIds){
		
		sysRoleUserRepository.deleteByUserId(userId);
		
		for (Long roleId : roleIds) {
			SysRoleUserPo sysRoleUserPo = new SysRoleUserPo();
			sysRoleUserPo.setUserId(this.userId);
			sysRoleUserPo.setRoleId(roleId);
			sysRoleUserPo.setOemCode("test");
			sysRoleUserRepository.save(sysRoleUserPo);
		}
	}
}
