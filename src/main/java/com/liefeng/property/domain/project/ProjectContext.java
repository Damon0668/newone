package com.liefeng.property.domain.project;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.PinyinUtil;
import com.liefeng.common.util.Po2VoConverter;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.domain.staff.ManageProjectContext;
import com.liefeng.property.error.ProjectErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.project.ProjectPo;
import com.liefeng.property.repository.mybatis.ProjectQueryRepository;
import com.liefeng.property.repository.project.ProjectRepository;
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
	
	private static Logger logger = LoggerFactory.getLogger(ProjectContext.class);

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectQueryRepository projectQueryRepository;
	
	/**
	 * 项目ID或以“,”分隔的ID串
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
		projectContext.setProject(project);;
		
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
		projectContext.setProjectId(projectId);
		
		return projectContext;
	}
	
	/**
	 * 根据项目全名加载项目上下文
	 * @param fullName 项目全名
	 * @return 项目上下文
	 */
	public static ProjectContext loadByFullName(String fullName) {
		ProjectContext projectContext = getInstance();
		projectContext.setFullName(fullName);
		
		return projectContext;
	}
	
	public ProjectVo get() {
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


	public ProjectVo create() throws PropertyException {
		if(project != null) {
			if(ValidateHelper.isNotEmptyString(project.getFullName())) {
				if(projectRepository.findByFullNameAndOemCode(project.getFullName(), 
						ContextManager.getInstance().getOemCode()) != null) {
					throw new PropertyException(ProjectErrorCode.PROJECT_ALREADY_EXIST);
				}
			}
			
			project.setId(UUIDGenerator.generate());
            project.setOemCode(ContextManager.getInstance().getOemCode());
            // 设置项目编码，编码为项目中文名称中各个拼音的首字母
            project.setCode(PinyinUtil.getFirstLetters(project.getFullName())); 
            project.setCreateTime(new Date());

            ProjectPo projectPo = MyBeanUtil.createBean(project, ProjectPo.class);
            projectRepository.save(projectPo);
		}
		
		return project;
	}

	public ProjectVo update() {
		if(project != null && ValidateHelper.isNotEmptyString(project.getId())) {
			ProjectPo projectPo = projectRepository.findOne(project.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(project, projectPo);
			projectRepository.save(projectPo);
		}
		
		return project;
	}

	@Transactional
	public void delete() {
		logger.info("delete projectId ={}", projectId);
		if(projectId != null) {
			String[] ids = projectId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i=0; i<ids.length; i++) {
					projectRepository.delete(ids[i]);
					ManageProjectContext.build().deleteByProjectId(ids[i]);
				}
			}
		}
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
		Page<ProjectPo> poPage = projectRepository.findByOemCodeOrderByCreateTimeDesc(
				ContextManager.getInstance().getOemCode(), new PageRequest(page-1, size));
		voPage = poPage.map(new Po2VoConverter<ProjectPo, ProjectVo>(ProjectVo.class));
		
		return new DataPageValue<ProjectVo>(voPage.getContent(), voPage.getTotalElements(),
				size, page);
		
	}
	
	/**
	 * 根据员工ID查找此员工管理的项目列表
	 * @param staffId 员工ID
	 * @return
	 */
	public List<ProjectVo> findProjectsByStaffId(String staffId){
		return projectQueryRepository.findByStaffId(staffId);
	}
	
	/**
	 * 查询某个oem下的所有项目列表
	 */
	public List<ProjectVo> listProjects(){
		String oemCode = ContextManager.getInstance().getOemCode();
		List<ProjectPo> projectPoList = projectRepository.findByOemCode(oemCode);
		return MyBeanUtil.createList(projectPoList, ProjectVo.class);
	}

	public List<ProjectVo> findAll() {
		List<ProjectPo> projectPoList = projectRepository.findAll();
		return MyBeanUtil.createList(projectPoList, ProjectVo.class);

	}

	protected void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	protected void setFullName(String fullName) {
		this.fullName = fullName;
	}

	protected void setProject(ProjectVo project) {
		this.project = project;
	}
}
