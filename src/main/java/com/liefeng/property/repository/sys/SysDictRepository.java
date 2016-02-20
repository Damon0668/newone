package com.liefeng.property.repository.sys;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.sys.SysDictPo;

/**
 * 数据字典仓储层
 * @author Huangama
 * @date 2016-2-20
 */
@Transactional
public interface SysDictRepository extends JpaRepository<SysDictPo, Long>{
	
	/**
	 * 根据字典组编码获取字典列表
	 * @param groupCode 字典组编码
	 * @return 字典列表
	 */
	public List<SysDictPo> findByGroupCode(String groupCode);
}
