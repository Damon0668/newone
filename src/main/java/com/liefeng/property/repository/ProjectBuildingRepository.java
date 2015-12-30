package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.ProjectBuildingPo;

/**
 * 项目楼栋楼层仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface ProjectBuildingRepository extends JpaRepository<ProjectBuildingPo, String> {

	Page<ProjectBuildingPo> findBuildingsByOemCodeAndProjectId(String oemCode, String projectId,
			Pageable pageable);
	
	Page<ProjectBuildingPo> findFloorsByOemCodeAndProjectIdAndParentId(String oemCode, String projectId, 
			String parentId, Pageable pageable);
}
