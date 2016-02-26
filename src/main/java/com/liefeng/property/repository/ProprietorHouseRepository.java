package com.liefeng.property.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ProprietorHousePo;

/**
 * 业主房产信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface ProprietorHouseRepository extends JpaRepository<ProprietorHousePo, String> {
	
	/**
	 * 根据项目ID和房号查询房产
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @return
	 */
	public ProprietorHousePo findByProjectIdAndHouseNum(String projectId, String houseNum);

	/**
	 * 获取项目下的业主房产信息
	 * @param projectId
	 * @return
	 */
	public List<ProprietorHousePo> findByProjectId(String projectId);

}
