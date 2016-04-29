package com.liefeng.property.domain.project;

import java.util.HashMap;
import java.util.List;

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
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.error.ProjectErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.project.ProjectBuildingPo;
import com.liefeng.property.repository.mybatis.ProjectBuildingQueryRepository;
import com.liefeng.property.repository.project.ProjectBuildingRepository;
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
	
	@Autowired
	private ProjectBuildingQueryRepository 	projectBuildingQueryRepository;

	/**
	 * 项目楼栋楼层ID 或 以“,”分隔的项目楼栋楼层ID串
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
	public ProjectBuildingVo get() {
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
			
			if(ValidateHelper.isNotEmptyString(projectBuilding.getParentId())) { // 校验楼层是否已存在
				
				if(projectBuildingRepository.findByParentIdAndName(projectBuilding.getParentId(), 
						projectBuilding.getName()) != null) {
					throw new PropertyException(ProjectErrorCode.FLOOR_ALREADY_EXIST);
				}
			} else if(ValidateHelper.isNotEmptyString(projectBuilding.getProjectId())) { // 校验楼栋是否已存在
				
				if(projectBuildingRepository.findByProjectIdAndName(projectBuilding.getProjectId(), 
						projectBuilding.getName()) != null) {
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

	/**
	 * 删除楼栋楼层
	 */
	public void delete() {
		if (projectBuildingId != null) {
			String[] ids = projectBuildingId.split(",");
			if(ids != null && ids.length > 0) {
				for(int i = 0; i< ids.length; i++) {
					projectBuildingRepository.delete(ids[i]);
				}
			}
		}
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
		Page<ProjectBuildingPo> poPage = projectBuildingRepository.findBuildingsByProjectIdAndParentIdIsNullOrderByCode(projectId,
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
	
	/**
	 * 获取项目下的所有楼栋
	 * @return
	 */
	public List<ProjectBuildingVo> finByProjectId(){
		List<ProjectBuildingPo> projectBuildingPos = projectBuildingRepository.findByProjectIdAndParentIdIsNull(projectId);
		
		return MyBeanUtil.createList(projectBuildingPos, ProjectBuildingVo.class);
	}
	
	/**
	 * 获取某楼栋的所有楼层，并对某楼层房间是否进行了房型初始化进行标记
	 * @param buildingId 楼栋id
	 * @param num 房间号后两位
	 * @return 
	 * @author xhw
	 * @date 2016年4月22日 上午11:34:10
	 */
	public List<ProjectBuildingVo> findByBuildingAndNum(String buildingId, String num){
		
		List<ProjectBuildingVo> projectBuildingList = null;
		if(ValidateHelper.isNotEmptyString(buildingId) && ValidateHelper.isNotEmptyString(num)){
			String oemCode = ContextManager.getInstance().getOemCode();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("buildingId", buildingId);
			paramMap.put("num", num);
			paramMap.put("oemCode", oemCode);

			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			projectBuildingList = projectBuildingQueryRepository.queryByBuildingIdAndNum(param);
		}
		
		return projectBuildingList;
	}

	public  ProjectBuildingVo findBuilding(String projectId,
			String parentId, String name) {
		ProjectBuildingPo projectBuildingPo = projectBuildingRepository.findByProjectIdAndParentIdAndName(projectId,parentId,name);
		return MyBeanUtil.createBean(projectBuildingPo, ProjectBuildingVo.class);
	}
}
