package com.liefeng.api.property;

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
     * project 项目
     */
    public void createProject(ProjectVo projectVo);

    public void updateProject(ProjectVo projectVo);

    public void deleteProject(String projectId);

    /*
     * projectBuilding 楼栋与楼层
     */
    void createProjectBuilding(ProjectBuildingVo projectBuildingVo);

    void updateProjectBuilding(ProjectBuildingVo projectBuildingVo);

    void deleteProjectBuilding(String ProjectBuildingId);

    /*
     * house 房产资料
     */
    void createHouse(HouseVo houseVo);

    void updateHouse(HouseVo houseVo);
}
