package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.property.vo.sys.SysMenuVo;
import com.liefeng.property.vo.sys.SysRoleVo;

/**
 * 系统权限服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年2月3日
 */
public interface ISysSecurityService {
	
	/**
	 * 查询系统角色
	 * @return
	 */
	public List<SysRoleVo> listAllRoles();
	
	/**
	 * 查询系统角色
	 * 分页查询
	 * @return
	 */
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size);
	

	/**
	 * 根据用户ID查询用户角色
	 * @param userId 用户ID
	 * @return
	 */
	public List<SysRoleVo> findRolesByUserId(String userId);
	
	/**
	 * 添加系统角色
	 * @param sysRole
	 * @return
	 */
	public ReturnValue createRole(SysRoleVo sysRole);
	
	/**
	 * 更新系统角色
	 * @param sysRole
	 * @return
	 */
	public ReturnValue updateRole(SysRoleVo sysRole);
	
	/**
	 * 删除系统角色
	 * @param id 角色ID
	 * @return
	 */
	public ReturnValue delRole(Long id);
	
	/**
	 * 查询菜单树
	 * @return
	 */
	public List<SysMenuVo> listMenuTree();
	
	/**
	 * 查询菜单并且根据角色选中
	 * @param roleId 角色ID
	 * @return
	 */
	public List<SysMenuVo> listMenusAndCheck(Long roleId);
	
	/**
	 * 角色授权菜单
	 * @return
	 */
	public ReturnValue grantRoleMenus(Long roleId,String menuIds);
	
	/**
	 * 查询菜单（只包含按钮）
	 * @param parentId 父ID
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysMenuVo> listButtons(Long parentId, int page, int size);
	
	/**
	 * 查询菜单（不包含按钮）
	 * @param parentId 父ID
	 * @param type 类型
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<SysMenuVo> listMenus(Long parentId, boolean isIgnoreButton, int page, int size);
	
	/**
	 * 根据用户ID查询此用户拥有按钮的code编码
	 * @param userId 用户Id
	 * @return
	 */
	public List<String> listButtonsCodeByUserId(String userId);
	
	/**
	 * 根据用户ID查询此用户拥有按钮的code编码
	 * @param userId 用户Id
	 * @return
	 */
	public List<SysMenuVo> listMenusByUserId(String userId);
	
	/**
	 * 根据 menuId 查询菜单
	 * @param menuId 菜单ID
	 * @return
	 */
	public SysMenuVo findMenu(Long menuId);
	
	/**
	 * 根据code 查询菜单
	 * @param code
	 * @return
	 */
	public SysMenuVo findMenuByCode(String code);
	/**
	 * 创建菜单
	 * @param sysMenu
	 * @return
	 */
	public ReturnValue createMenu(SysMenuVo sysMenu);
	
	/**
	 * 更新菜单
	 * @param sysMenu
	 * @return
	 */
	public ReturnValue updateMenu(SysMenuVo sysMenu);
	
	/**
	 * 批量删除菜单
	 * @param ids 菜单ID数组
	 * @return
	 */
	public ReturnValue deleteMenus(String[] ids);
	
	
	/**
	 * 用户授权角色
	 * @param userId 用户ID
	 * @param roleIds 角色ID
	 * @return
	 */
	public ReturnValue grantRoleUser(String userId, Long[] roleIds);
}
