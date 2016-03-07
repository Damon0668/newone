package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目查询接口
 * @author Huangama
 * @author 蔡少东
 * @date 2015-12-22
 */
public interface ProjectQueryRepository {

	public ProjectVo getProjectById(String projectId);
	
	/**
	 * 根据员工ID查找此员工管理的项目列表
	 * @param staffId 员工ID
	 * @return
	 */
	public List<ProjectVo> findByStaffId(String staffId);
}
