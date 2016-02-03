package com.liefeng.intf.property;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
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
	 * 分页查询
	 * @return
	 */
	public DataPageValue<SysRoleVo> listRoles4page(String name, String type, int page, int size);
	
	/**
	 * 添加系统角色
	 * @param sysRole
	 * @return
	 */
	public ReturnValue createRole(SysRoleVo sysRole);
	
	/**
	 * 删除系统角色
	 * @param id 角色ID
	 * @return
	 */
	public ReturnValue delRole(Long id);
}
