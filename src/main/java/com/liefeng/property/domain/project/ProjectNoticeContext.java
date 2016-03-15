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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.project.ProjectNoticePo;
import com.liefeng.property.repository.project.ProjectNoticeRepository;
import com.liefeng.property.vo.project.ProjectNoticeVo;

/**
 * 小区通告领域模型
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Service
@Scope("prototype")
public class ProjectNoticeContext {
	
	private static Logger logger = LoggerFactory.getLogger(ProjectNoticeContext.class);

	@Autowired
	private ProjectNoticeRepository projectNoticeRepository;
	
	/**
	 * 小区通告ID
	 */
	private String projectNoticeId;
	
	/**
	 * 小区通告值对象
	 */
	private ProjectNoticeVo projectNotice;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static ProjectNoticeContext getInstance() {
		return SpringBeanUtil.getBean(ProjectNoticeContext.class);
	}
	
	/**
	 * 根据小区通告值对象构建上下文
	 * @param projectNotice 小区通告值对象
	 * @return 小区通告上下文
	 */
	public static ProjectNoticeContext build(ProjectNoticeVo projectNotice) {
		ProjectNoticeContext projectNoticeContext = getInstance();
		projectNoticeContext.setProjectNotice(projectNotice);
		
		return projectNoticeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 小区通告上下文
	 */
	public static ProjectNoticeContext build() {
		ProjectNoticeContext projectNoticeContext = getInstance();
		
		return projectNoticeContext;
	}
	
	/**
	 * 根据小区通告ID加载项目上下文
	 * @param projectNoticeId 小区通告ID
	 * @return 小区通告上下文
	 */
	public static ProjectNoticeContext loadById(String projectNoticeId) {
		ProjectNoticeContext projectNoticeContext = getInstance();
		projectNoticeContext.setProjectNoticeId(projectNoticeId);
		
		return projectNoticeContext;
	}
	
	
	public ProjectNoticeVo get() {
		if (projectNotice == null) {
			
			ProjectNoticePo projectNoticePo = null;
			if (ValidateHelper.isNotEmptyString(projectNoticeId)) {
				projectNoticePo = projectNoticeRepository.findOne(projectNoticeId);
			}
			
			if (projectNoticePo != null) {
				projectNotice = MyBeanUtil.createBean(projectNoticePo, ProjectNoticeVo.class);
			}
		}
		
		return projectNotice;
	}


	public ProjectNoticeVo create() {
		if(projectNotice != null) {
			
			projectNotice.setId(UUIDGenerator.generate());
            projectNotice.setCreateTime(new Date());

            ProjectNoticePo projectNoticePo = MyBeanUtil.createBean(projectNotice, ProjectNoticePo.class);
            projectNoticeRepository.save(projectNoticePo);
		}
		
		return projectNotice;
	}

	public ProjectNoticeVo update() {
		if(projectNotice != null && ValidateHelper.isNotEmptyString(projectNotice.getId())) {
			logger.info("更新小区通告，通告ID={}", projectNotice.getId());
			ProjectNoticePo projectNoticePo = projectNoticeRepository.findOne(projectNotice.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(projectNotice, projectNoticePo);
			projectNoticeRepository.save(projectNoticePo);
			logger.info("更新小区通告成功，通告ID={}", projectNotice.getId());
			
			// 更新后拷贝最新内容返回
			projectNotice = MyBeanUtil.createBean(projectNoticePo, ProjectNoticeVo.class);
		}
		
		return projectNotice;
	}

	public void delete() {
		if(projectNoticeId != null) {
			String[] ids = projectNoticeId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i=0; i<ids.length; i++) {
					projectNoticeRepository.delete(ids[i]);
				}
			}
		}
	}
	
    /**
     * 分页查询小区通告
     * @param projectId 项目ID
     * @param page 分页大小
     * @param size 分页大小
     * @return 小区通告分页数据
     */
	public DataPageValue<ProjectNoticeVo> findProjectNotices(String projectId, int page, int size) {
		logger.info("小区通告分页查询，projectId={}，page={}，size={}", projectId, page, size);
		
		// spring-data的page从0开始
		Page<ProjectNoticePo> poPage = projectNoticeRepository.findByProjectIdOrderByCreateTime(
				projectId, new PageRequest(page - 1, size));
		Page<ProjectNoticeVo> voPage = poPage.map(new Po2VoConverter<ProjectNoticePo,
				ProjectNoticeVo>(ProjectNoticeVo.class));
		
		return new DataPageValue<ProjectNoticeVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}

	protected void setProjectNoticeId(String projectNoticeId) {
		this.projectNoticeId = projectNoticeId;
	}

	protected void setProjectNotice(ProjectNoticeVo projectNotice) {
		this.projectNotice = projectNotice;
	}
	
}
