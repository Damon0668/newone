package com.liefeng.property.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.CheckinMaterialPo;

/**
 * 入住资料信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年2月25日
 */
@Transactional
public interface CheckinMaterialRepository extends JpaRepository<CheckinMaterialPo, String> {
	
	/**
	 * 根据业主房产ID查询入住资料
	 * @param proprietorHouseId 业主房产ID
	 * @return 入住资料信息列表
	 */
	public List<CheckinMaterialPo> findByProprietorHouseId(String proprietorHouseId);
	
}
