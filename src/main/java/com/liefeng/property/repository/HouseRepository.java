package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.HousePo;

/**
 * 房产信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface HouseRepository extends JpaRepository<HousePo, String> {
	
	/**
	 * 根据房产编码查询房产信息
	 * @param projectId 房产所属项目ID
	 * @param houseNum 房产编码
	 * @return 房产信息
	 */
	public HousePo findByProjectIdAndHouseNum(String projectId, String houseNum);
}
