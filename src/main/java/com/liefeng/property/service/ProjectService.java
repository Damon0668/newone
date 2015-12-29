package com.liefeng.property.service;

import com.liefeng.api.property.IProjectService;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 项目服务实现类
 * 
 * @author Huangama
 * @date 2015-12-22
 */
@Service
public class ProjectService implements IProjectService {

    @Override
    @Transactional
    public void createProject(ProjectVo projectVo) {
        ProjectContext projectContext = ProjectContext.build(projectVo);
        projectContext.create();
    }

    @Override
    @Transactional
    public void updateProject(ProjectVo projectVo) {
        ProjectContext projectContext = ProjectContext.build(projectVo);
        projectContext.update();

    }

    @Override
    @Transactional
    public void deleteProject(String projectId) {
        ProjectContext projectContext = ProjectContext.loadById(projectId);
        projectContext.delete();
    }

    @Override
    @Transactional
    public void createProjectBuilding(ProjectBuildingVo projectBuildingVo) {
        ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.build(projectBuildingVo);
        projectBuildingContext.create();
    }

    @Override
    @Transactional
    public void updateProjectBuilding(ProjectBuildingVo projectBuildingVo) {
        ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.build(projectBuildingVo);
        projectBuildingContext.update();
    }

    @Override
    @Transactional
    public void deleteProjectBuilding(String projectBuildingId) {
        ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadById(projectBuildingId);
        projectBuildingContext.delete();
    }
}
