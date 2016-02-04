package com.liefeng.property.domain.project;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.project.ProjectPo;
import com.liefeng.property.repository.ProjectRepository;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目领域上下文对象
 * @author Huangama
 * @author levy
 * @date 2015-12-22
 */
@Service
@Scope("prototype")
public class ProjectContext {
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ProjectContext.class);

	@Autowired
	private ProjectRepository projectRepository;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目全名
	 */
	private String fullName;
	
	/**
	 * 客户值对象
	 */
	private ProjectVo project;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ProjectContext getInstance() {
		return SpringBeanUtil.getBean(ProjectContext.class);
	}
	
	/**
	 * 根据项目值对象构建上下文
	 * @param project 项目值对象
	 * @return 项目上下文
	 */
	public static ProjectContext build(ProjectVo project) {
		ProjectContext projectContext = getInstance();
		projectContext.project = project;
		
		return projectContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 项目上下文
	 */
	public static ProjectContext build() {
		ProjectContext projectContext = getInstance();
		
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
	
	/**
	 * 根据项目全名加载项目上下文
	 * @param fullName 项目全名
	 * @return 项目上下文
	 */
	public static ProjectContext loadByFullName(String fullName) {
		ProjectContext projectContext = getInstance();
		projectContext.fullName = fullName;
		
		return projectContext;
	}
	
	public ProjectVo getProject() {
		if (project == null) {
			ProjectPo projectPo = null;
			if (ValidateHelper.isNotEmptyString(projectId)) {
				projectPo = projectRepository.findOne(projectId);
			} else if(ValidateHelper.isEmptyString(fullName)) {
				projectPo = projectRepository.findByFullNameAndOemCode(fullName, ContextManager.getInstance().getOemCode());
			}
			
			
			if (projectPo != null) {
				project = MyBeanUtil.createBean(projectPo, ProjectVo.class);
			}
		}
		return project;
	}


	public ProjectVo create() {
		if(project != null){
			project.setId(UUIDGenerator.generate());
            project.setOemCode(ContextManager.getInstance().getOemCode()); 
            project.setCreateTime(new Date());

            ProjectPo projectPo = MyBeanUtil.createBean(project, ProjectPo.class);
            projectRepository.save(projectPo);
		}
		return project;
	}

	public ProjectVo update() {
		if(project != null){
			ProjectPo projectPo = MyBeanUtil.createBean(project, ProjectPo.class);
			projectRepository.save(projectPo);
		}
		return project;
	}

	public void delete() {
		if(projectId != null)
            projectRepository.delete(projectId);
	}
	
    /**
     * 分页查询项目
     * @param page 第几页，最小为1
     * @param size 页面大小 
     * @return
     */
	public DataPageValue<ProjectVo> findProjects(int page, int size){
		Page<ProjectVo> voPage = null;
		
//		spring-data 的page从0开始
		Page<ProjectPo> poPage = projectRepository.findByOemCode(
				ContextManager.getInstance().getOemCode(), new PageRequest(page-1, size));
		voPage = poPage.map(new Po2VoConverter<ProjectPo, ProjectVo>(ProjectVo.class));
		
		return new DataPageValue<ProjectVo>(voPage.getContent(), voPage.getTotalElements(),
				size, page);
		
	}
}
