package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentPo;

/**
 * 住户信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface ResidentRepository extends JpaRepository<ResidentPo, String> {
	
	/**
	 * 获取住户信息
	 * @param projectId
	 * @param custGlobalId
	 * @return
	 */
	public ResidentPo findByProjectIdAndCustGlobalId(String projectId, String custGlobalId);

}
