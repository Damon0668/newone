package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 项目楼栋楼层仓储层
 * 
 * @author ZhenTingJun
 * @author levy
 * @date 2015-12-24
 */
@Transactional
public interface ProjectBuildingRepository extends JpaRepository<ProjectBuildingPo, String> {

    /**
     * 分页查询项目下的楼栋
     * @param projectId 项目id
     * @return
     */
	Page<ProjectBuildingPo> findBuildingsByProjectId(String projectId, Pageable pageable);
	
    /**
     * 分页查询楼栋下的楼层
     * @param buildingId 楼栋id，楼栋id是唯一的，故不用再指定项目id
     * @return
     */
    public Page<ProjectBuildingPo> findFloorsByBuildingId(String buildingId, Pageable pageable);
}
