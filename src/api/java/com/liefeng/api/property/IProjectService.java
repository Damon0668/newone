package com.liefeng.api.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目接口
 * 
 * @author Huangama
 * @date 2015-12-22
 */
public interface IProjectService {
    /*
     * project 项目 ******
     */
	
	/**
	 * 创建项目
	 * @param projectVo
	 */
    public void createProject(ProjectVo projectVo);

	/**
	 * 更新项目
	 * @param projectVo
	 */
    public void updateProject(ProjectVo projectVo);

	/**
	 * 删除项目
	 * @param projectVo
	 */
    public void deleteProject(String projectId);
    
    public Page<ProjectVo> findProjectByOemCode(ProjectVo projectVo, Pageable pageable);

    /*
     * projectBuilding 楼栋与楼层 ******
     */
    
    /**
     * 创建楼栋或楼层， parentId=null 为楼栋
     * @param projectBuildingVo
     */
    void createProjectBuilding(ProjectBuildingVo projectBuildingVo);

    
    /**
     * 更新楼栋或楼层， parentId=null 为楼栋
     * @param projectBuildingVo
     */
    void updateProjectBuilding(ProjectBuildingVo projectBuildingVo);

    /**
     * 删除楼栋或楼层
     * @param ProjectBuildingId
     */
    void deleteProjectBuilding(String ProjectBuildingId);

    public Page<ProjectBuildingVo> findBuildingsByOemCodeAndProjectId(
    		ProjectBuildingVo projectBuildingVo,Pageable pageable);
    
    public Page<ProjectBuildingVo> findFloorsByOemCodeAndProjectIdAndParentId(
    		ProjectBuildingVo projectBuildingVo,Pageable pageable);
    
    
    /*
     * house 房产资料 ******
     */
    
    /**
     * 创建房产资料
     * @param houseVo
     */
    void createHouse(HouseVo houseVo);

    /**
     * 更新房产资料
     * @param houseVo
     */
    void updateHouse(HouseVo houseVo);
}
