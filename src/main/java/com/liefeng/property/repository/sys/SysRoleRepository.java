package com.liefeng.property.repository.sys;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.liefeng.property.po.sys.SysRolePo;

/**
 * 系统角色
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Transactional
public interface SysRoleRepository extends JpaRepository<SysRolePo, Long>{
	
	/**
	 * 查询角色
	 * @param name 模糊查找 "%name%"
	 * @param pageable
	 * @return
	 */
	public Page<SysRolePo> findByNameLike(String name, Pageable pageable);

}
