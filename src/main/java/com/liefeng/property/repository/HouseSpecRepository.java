package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 分页查询项目下的房产规格
     * @param projectId 项目ID
     * @return
     */
	Page<HouseSpecPo> findByProjectId(String projectId, Pageable pageable);
	
	
	/**
     * 分页查询项目下的房产规格
     * @param projectId 项目ID
     * @param buildingId 楼栋ID
     * @return
     */
	Page<HouseSpecPo> findByProjectIdAndBuildingId(String projectId, String buildingId, Pageable pageable);
	
}
