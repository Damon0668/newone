
package com.liefeng.property.domain.sys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.sys.SysRoleUserPo;
import com.liefeng.property.repository.sys.SysRoleUserRepository;
import com.liefeng.property.vo.sys.SysRoleUserVo;

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
	public void grantRoles(Long[] roleIds){
		
		deleteAll();
		
		for (Long roleId : roleIds) {
			SysRoleUserPo sysRoleUserPo = new SysRoleUserPo();
			sysRoleUserPo.setUserId(this.userId);
			sysRoleUserPo.setRoleId(roleId);
			sysRoleUserPo.setOemCode("test");
			sysRoleUserRepository.save(sysRoleUserPo);
		}
	}
	
	/**
	 * 查找用户角色
	 * @return
	 */
	public List<SysRoleUserVo> findRoles(){
		List<SysRoleUserPo> dataList = sysRoleUserRepository.findByUserId(userId);
		return MyBeanUtil.createList(dataList, SysRoleUserVo.class);
	}
	
	/**
	 * 删除所有
	 */
	public void deleteAll(){
		if(ValidateHelper.isNotEmptyString(userId)){
			sysRoleUserRepository.deleteByUserId(userId);
		}
		
		if(roleId != null){
			sysRoleUserRepository.deleteByRoleId(roleId);
		}
	}
}
