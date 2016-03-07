package com.liefeng.property.repository.sys;

import java.util.List;

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
	
	/**
	 * 根据OEM编码获取其所有的系统参数列表
	 * @param oemCode OEM编码
	 * @return 系统参数列表
	 */
	public List<SysParamPo> findByOemCode(String oemCode);
	
	/**
	 * 根据系统参数编码和OEM编码获取系统参数
	 * @param code 系统参数编码
	 * @param oemCode OEM编码
	 * @return 系统参数
	 */
	public SysParamPo findByCodeAndOemCode(String code, String oemCode);
}
