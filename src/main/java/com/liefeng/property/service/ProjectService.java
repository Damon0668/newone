package com.liefeng.property.service;

import com.liefeng.api.property.IProjectService;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
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
    public ProjectVo saveProject(ProjectVo projectVo) {
        ProjectContext projectContext = ProjectContext.build(projectVo);
        return projectContext.create();
    }

    @Override
    @Transactional
    public ProjectVo updateProject(ProjectVo projectVo) {
        ProjectContext projectContext = ProjectContext.build(projectVo);
        return projectContext.update();

    }

    @Override
    @Transactional
    public void deleteProject(String projectId) {
        ProjectContext projectContext = ProjectContext.loadById(projectId);
        projectContext.delete();
    }
}
