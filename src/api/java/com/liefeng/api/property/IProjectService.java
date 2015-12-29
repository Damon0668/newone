package com.liefeng.api.property;

import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目接口
 * 
 * @author Huangama
 * @date 2015-12-22
 */
public interface IProjectService {
    public ProjectVo saveProject(ProjectVo projectVo);

    public ProjectVo updateProject(ProjectVo projectVo);

    public void deleteProject(String projectId);
}
