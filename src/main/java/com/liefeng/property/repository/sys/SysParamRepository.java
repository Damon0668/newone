package com.liefeng.property.repository.sys;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.sys.SysParamPo;

/**
 * 系统参数仓储层
 * @author Huangama
 * @date 2016-2-20
 */
@Transactional
public interface SysParamRepository extends JpaRepository<SysParamPo, Long>{
	
}
