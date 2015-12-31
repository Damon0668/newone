package com.liefeng.api.property;

import com.liefeng.core.entity.DataPageValue;
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
    
    /**
     * 分页查询项目
     * @param page 第几页，最小为0
     * @param size 页面大小，最小为1
     * @return
     */
    public DataPageValue<ProjectVo> findProjects(int page, int size);

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
    void deleteProjectBuilding(String projectBuildingId);

    /**
     * 分页查询项目下的楼栋
     * @param projectId 项目id
     * @param page 第几页，最小为0
     * @param size 页面大小，最小为1
     * @return
     */
    public DataPageValue<ProjectBuildingVo> findBuildingsByProjectId(
    		String projectId, int page, int size);
    
    /**
     * 分页查询楼栋下的楼层
     * @param buildingId 楼栋id，指定楼栋id是唯一的，故不用再指定项目id
     * @param page 第几页，最小为0
     * @param size 页面大小，最小为1
     * @return
     */
    public DataPageValue<ProjectBuildingVo> findFloorsByBuildingId(
    		String buildingId, int page, int size);
    
    
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
