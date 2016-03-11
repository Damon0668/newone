package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ProprietorPo;

/**
 * 业主信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface ProprietorRepository extends JpaRepository<ProprietorPo, String> {
	
	/**
	 * 根据项目ID和客户全局ID查询业主
	 * @param projectId 项目ID
	 * @param custGlobalId 客户全局ID
	 * @return
	 */
	public ProprietorPo findByProjectIdAndCustGlobalId(String projectId, String custGlobalId);
}
