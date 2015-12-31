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

	/**
	 * @param oemCode
	 * @param projectId
	 * @param parentId 传null表示查询楼栋(buiding); 否则查询楼层(floor)
	 * @param pageable
	 * @return
	 */
	Page<ProjectBuildingPo> findByOemCodeAndProjectIdAndParentId(String oemCode, String projectId, 
			String parentId, Pageable pageable);
}
