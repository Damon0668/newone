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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.repository.ProjectBuildingRepository;
import com.liefeng.property.vo.project.ProjectBuildingVo;

/**
 * 项目楼栋楼层领域模型
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
	 * 获取本类实例，每次返回一个新的对象
	 * @return 本类实例
	 */
	public static ProjectBuildingContext getInstance() {
		return SpringBeanUtil.getBean(ProjectBuildingContext.class);
	}
	
	/**
	 * 根据项目楼栋楼层值对象构建上下文
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext build(ProjectBuildingVo projectBuilding) {
		ProjectBuildingContext projectBuildingContext = getInstance();
		projectBuildingContext.projectBuilding = projectBuilding;
		
		return projectBuildingContext;
	}
	
	/**
	 * 根据项目楼栋楼层ID加载上下文
	 * @param projectBuildingId
	 * @return 项目楼栋楼层上下文
	 */
	public static ProjectBuildingContext loadById(String projectBuildingId) {
		ProjectBuildingContext projectBuildingContext = getInstance();
		projectBuildingContext.projectBuildingId = projectBuildingId;
		
		return projectBuildingContext;
	}
	
	/**
	 * 获取楼栋楼层信息
	 * @return 楼栋楼层信息
	 */
	public ProjectBuildingVo getProjectBuilding() {
		if(projectBuilding == null) {
			ProjectBuildingPo projectBuildingPo = null;
			if(ValidateHelper.isNotEmptyString(projectBuildingId)) {
				projectBuildingPo = projectBuildingRepository.findOne(projectBuildingId);
			}
			
			if(projectBuildingPo != null) {
				projectBuilding = MyBeanUtil.createBean(projectBuildingPo, ProjectBuildingVo.class);
			}
		}
		
		return projectBuilding;
	}
	
	/**
	 * 保存楼栋楼层信息
	 */
	public ProjectBuildingVo create() {
		if(projectBuilding != null) {
			projectBuilding.setId(UUIDGenerator.generate());
			projectBuilding.setOemCode(""); // TODO 待确定后补齐
			
			ProjectBuildingPo projectBuildingPo = MyBeanUtil.createBean(projectBuilding, ProjectBuildingPo.class);
			projectBuildingRepository.save(projectBuildingPo);
		}
		return projectBuilding;
	}

	public ProjectBuildingVo update() {
		if(projectBuilding != null){
			ProjectBuildingPo projectBuildingPo = MyBeanUtil.createBean(projectBuilding, ProjectBuildingPo.class);
			projectBuildingRepository.save(projectBuildingPo);
		}

		return projectBuilding;
	}

	public void delete() {
		if(projectBuildingId != null)
            projectBuildingRepository.delete(projectBuildingId);
	}
	
	/**
	 *  查询楼栋
	 * @param projectId
     * @param page 第几页，最小为0
     * @param size 页面大小，最小为1
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findBuildingsByProjectId(String projectId, int page, int size){
		Page<ProjectBuildingVo> voPage = null;
		
		Page<ProjectBuildingPo> poPage = projectBuildingRepository.findBuildingsByProjectIdAndParentIdIsNull(
				projectId, new PageRequest(page, size));
		voPage = poPage.map(new Po2VoConverter<ProjectBuildingPo,ProjectBuildingVo>(ProjectBuildingVo.class));
		
		return new DataPageValue<ProjectBuildingVo>(voPage.getContent(), voPage.getTotalElements(),
				size, page+1);
	}
	
	/**
	 * 查询楼层
	 * @param buildingId 
     * @param page 第几页，最小为0
     * @param size 页面大小，最小为1
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findFloorsByBuildingId(String buildingId, int page, int size){
		Page<ProjectBuildingVo> voPage = null;
		
		Page<ProjectBuildingPo> poPage = projectBuildingRepository.findFloorsByParentId(
				buildingId, new PageRequest(page, size));
		voPage = poPage.map(new Po2VoConverter<ProjectBuildingPo,ProjectBuildingVo>(
				ProjectBuildingVo.class));
		return new DataPageValue<ProjectBuildingVo>(voPage.getContent(), voPage.getTotalElements(),
				size, page+1);
	}
}
