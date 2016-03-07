package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.HouseSpecPo;

/**
 * 房产规格仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年2月17日
 */
@Transactional
public interface HouseSpecRepository extends JpaRepository<HouseSpecPo, String> {
	
	/**
	 * 根据项目ID，楼栋ID和房号尾数查询房产规格
	 * @param projectId 项目ID
	 * @param buildingId 楼栋ID
	 * @param num 房号尾数
	 * @return
	 */
	public HouseSpecPo findByProjectIdAndBuildingIdAndNum(String projectId, String buildingId, String num);
}
