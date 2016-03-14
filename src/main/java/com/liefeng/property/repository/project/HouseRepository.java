package com.liefeng.property.repository.project;

import java.util.List;

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
	
	/**
	 * 根据项目ID和楼栋ID查询房产资料
	 * @param projectId 项目ID
	 * @param buildingId 楼栋ID
	 * @return 房产资料列表
	 */
	public List<HousePo> findByProjectIdAndBuildingId(String projectId, String buildingId);
}
