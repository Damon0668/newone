package com.liefeng.property.domain.project;

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
import com.liefeng.property.error.ProjectErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.po.project.ProjectPo;
import com.liefeng.property.repository.ProjectBuildingRepository;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 项目楼栋楼层领域模型
 * 
 * @author ZhenTingJun
 * @author levy
 * @date 2015-12-23
 */
@Service
@Scope("prototype")
public class ProjectBuildingContext {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ProjectBuildingContext.class);

	@Autowired
	private ProjectBuildingRepository projectBuildingRepository;

	/**
	 * 项目楼栋楼层ID
	 */
	private String projectBuildingId;

	/**
	 * 项目楼栋楼层值对象
	 */
	private ProjectBuildingVo projectBuilding;

	/**
	 * 项目楼栋楼层所属项目ID
	 */
	private String projectId;

	/**
	 * 获取本类实例，每次返回一个新的对象
	 * 
	 * @return 本类实例
	 */
	private static ProjectBuildingContext getInstance() {
		return SpringBeanUtil.getBean(ProjectBuildingContext.class);
	}

	/**
	 * 根据项目楼栋楼层值对象构建上下文
	 * 
	 * @param projectBuilding 项目楼栋楼层值对象
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext build(ProjectBuildingVo projectBuilding) {
		ProjectBuildingContext projectBuildingContext = getInstance();
		projectBuildingContext.projectBuilding = projectBuilding;

		return projectBuildingContext;
	}

	/**
	 * 构建上下文（无参）
	 * 
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext build() {
		ProjectBuildingContext projectBuildingContext = getInstance();

		return projectBuildingContext;
	}

	/**
	 * 根据项目楼栋楼层ID加载上下文
	 * 
	 * @param projectBuildingId 项目楼栋楼层ID
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext loadById(String projectBuildingId) {
		ProjectBuildingContext projectBuildingContext = getInstance();
		projectBuildingContext.projectBuildingId = projectBuildingId;

		return projectBuildingContext;
	}

	/**
	 * 根据项目ID加载上下文
	 * 
	 * @param projectId 项目ID
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext loadByProjectId(String projectId) {
		ProjectBuildingContext projectBuildingContext = getInstance();
		projectBuildingContext.projectId = projectId;

		return projectBuildingContext;
	}

	/**
	 * 获取楼栋楼层信息
	 * 
	 * @return 楼栋楼层信息
	 */
	public ProjectBuildingVo getProjectBuilding() {
		if (projectBuilding == null) {
			ProjectBuildingPo projectBuildingPo = null;
			if (ValidateHelper.isNotEmptyString(projectBuildingId)) {
				projectBuildingPo = projectBuildingRepository.findOne(projectBuildingId);
			}

			if (projectBuildingPo != null) {
				projectBuilding = MyBeanUtil.createBean(projectBuildingPo, ProjectBuildingVo.class);
			}
		}

		return projectBuilding;
	}

	/**
	 * 保存楼栋楼层信息
	 */
	public ProjectBuildingVo create() throws PropertyException {
		if (projectBuilding != null) {
			if(ValidateHelper.isNotEmptyString(projectBuilding.getName())) {
				if(projectBuildingRepository.findByNameAndOemCode(projectBuilding.getName(), 
						ContextManager.getInstance().getOemCode()) != null) {
					throw new PropertyException(ProjectErrorCode.BUILDING_ALREADY_EXIST);
				}
			}
			
			projectBuilding.setId(UUIDGenerator.generate());
			projectBuilding.setOemCode(ContextManager.getInstance().getOemCode()); 

			ProjectBuildingPo projectBuildingPo = MyBeanUtil.createBean(projectBuilding, ProjectBuildingPo.class);
			projectBuildingRepository.save(projectBuildingPo);
		}
		return projectBuilding;
	}

	public ProjectBuildingVo update() {
		if (projectBuilding != null && ValidateHelper.isNotEmptyString(projectBuilding.getId())) {
			
			ProjectBuildingPo projectBuildingPo = projectBuildingRepository.findOne(projectBuilding.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(projectBuilding, projectBuildingPo);
			projectBuildingRepository.save(projectBuildingPo);
		}

		return projectBuilding;
	}

	public void delete() {
		if (projectBuildingId != null)
			projectBuildingRepository.delete(projectBuildingId);
	}

	/**
	 * 查询楼栋
	 * 
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findBuildingsByProjectId(int page, int size) {
		Page<ProjectBuildingVo> voPage = null;

		// spring-data 的page从0开始
		Page<ProjectBuildingPo> poPage = projectBuildingRepository.findBuildingsByProjectIdAndParentIdIsNull(projectId,
				new PageRequest(page - 1, size));
		voPage = poPage.map(new Po2VoConverter<ProjectBuildingPo, ProjectBuildingVo>(ProjectBuildingVo.class));

		return new DataPageValue<ProjectBuildingVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}

	/**
	 * 查询楼层
	 * 
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findFloorsByBuildingId(int page, int size) {
		Page<ProjectBuildingVo> voPage = null;

		// spring-data 的page从0开始
		Page<ProjectBuildingPo> poPage = projectBuildingRepository.findFloorsByParentId(projectBuildingId,
				new PageRequest(page - 1, size));
		voPage = poPage.map(new Po2VoConverter<ProjectBuildingPo, ProjectBuildingVo>(ProjectBuildingVo.class));
		
		return new DataPageValue<ProjectBuildingVo>(voPage.getContent(), voPage.getTotalElements(), size, page);
	}
}
