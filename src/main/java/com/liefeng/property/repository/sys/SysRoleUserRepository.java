package com.liefeng.property.repository.sys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.sys.SysRoleUserPo;

/**
 * 角色--用户
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Transactional
public interface SysRoleUserRepository extends JpaRepository<SysRoleUserPo, Long>{
	
	/**
	 * 根据菜单ID,删除对应关系
	 * @param menuId 菜单ID
	 */
	public void deleteByUserId(String userId);
	
	/**
	 * 根据用户ID,查找角色关系
	 * @param userId
	 * @return
	 */
	public List<SysRoleUserPo> findByUserId(String userId);
	
}
