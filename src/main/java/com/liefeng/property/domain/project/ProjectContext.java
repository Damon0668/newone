package com.liefeng.property.domain.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.project.ProjectPo;
import com.liefeng.property.repository.ProjectRepository;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目领域上下文对象
 * @author Huangama
 * @date 2015-12-22
 */
@Service
@Scope("prototype")
public class ProjectContext {
	
	private static Logger logger = LoggerFactory.getLogger(ProjectContext.class);

	@Autowired
	private ProjectRepository projectRepository;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 客户值对象
	 */
	private ProjectVo project;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	public static ProjectContext getInstance() {
		return SpringBeanUtil.getBean(ProjectContext.class);
	}
	
	public static ProjectContext build(ProjectVo project) {
		ProjectContext projectContext = getInstance();
		projectContext.project = project;
		
		return projectContext;
	}
	
	/**
	 * 根据项目ID加载项目上下文
	 * @param projectId 项目ID
	 * @return 项目上下文
	 */
	public static ProjectContext loadById(String projectId) {
		ProjectContext projectContext = getInstance();
		projectContext.projectId = projectId;
		
		return projectContext;
	}
	
	public ProjectVo getCustomer() {
		if (project == null) {
			ProjectPo projectPo = null;
			if (ValidateHelper.isNotEmptyString(projectId)) {
				projectPo = projectRepository.findOne(projectId);
			}
			if (projectPo != null) {
				project = MyBeanUtil.createBean(projectPo, ProjectVo.class);
			}
		}
		
		return project;
	}
	
	
}
