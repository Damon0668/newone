package com.liefeng.property.repository.sys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.sys.SysRoleMenuPo;

/**
 * 角色--菜单
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Transactional
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuPo, Long>{
	
	/**
	 * 查找菜单
	 * @param roleId 角色ID
	 * @return
	 */
	public List<SysRoleMenuPo> findByRoleId(Long roleId);
	
	/**
	 * 根据菜单ID,删除对应关系
	 * @param menuId 菜单ID
	 */
	public void deleteByMenuId(Long menuId);
}
