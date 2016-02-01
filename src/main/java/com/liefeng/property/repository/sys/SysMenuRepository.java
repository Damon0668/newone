package com.liefeng.property.repository.sys;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.sys.SysMenuPo;

/**
 * 系统菜单
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Transactional
public interface SysMenuRepository extends JpaRepository<SysMenuPo, Long>{

}
