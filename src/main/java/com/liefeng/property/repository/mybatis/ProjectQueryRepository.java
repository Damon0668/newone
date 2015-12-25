package com.liefeng.property.repository.mybatis;

import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目查询接口
 * @author Huangama
 * @date 2015-12-22
 */
public interface ProjectQueryRepository {

	public ProjectVo getProjectById(String projectId);
}
