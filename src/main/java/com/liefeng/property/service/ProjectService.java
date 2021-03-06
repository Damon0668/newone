package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.domain.project.AppHomeImageContext;
import com.liefeng.property.domain.project.HouseCheckContext;
import com.liefeng.property.domain.project.HouseCheckitemConfigContext;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.domain.project.HouseSpecContext;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.domain.project.ProjectNoticeContext;
import com.liefeng.property.vo.household.HouseGraphVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.UserClientIdVo;
import com.liefeng.property.vo.project.AppHomeImageVo;
import com.liefeng.property.vo.project.HouseCheckVo;
import com.liefeng.property.vo.project.HouseCheckitemConfigVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectNoticeVo;
import com.liefeng.property.vo.project.ProjectVo;
import com.liefeng.service.constant.PushActionConstants;

/**
 * 项目服务实现类
 * 
 * @author Huangama
 * @author levy
 * @author 蔡少东
 * @author ZhenTingJun
 * @date 2015-12-22
 */
@Service
public class ProjectService implements IProjectService {
	private static Logger logger = LoggerFactory
			.getLogger(WorkbenchService.class);
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private HouseholdService householdService;
	
	@Autowired
	private IPushMsgService pushMsgService;
	
	@Autowired
	private PropertyPushMsgService propertyPushMsgService;
	
	@Override
	public ProjectVo findProjectById(String projectId) {
		ProjectContext projectContext = ProjectContext.loadById(projectId);
		ProjectVo projectVo = projectContext.get();
		return projectVo;
	}
	
	@Override
	public ProjectVo findProjectByFullName(String fullName) {
		ProjectContext projectContext = ProjectContext.loadByFullName(fullName);
		ProjectVo projectVo = projectContext.get();
		return projectVo;
	}

	@Override
	public void createProject(ProjectVo projectVo)  throws LiefengException {
		ProjectContext projectContext = ProjectContext.build(projectVo);
		projectContext.create();
	}

	@Override
	public void updateProject(ProjectVo projectVo) {
		ProjectContext projectContext = ProjectContext.build(projectVo);
		projectContext.update();

	}

	@Override
	public void deleteProject(String projectId) {
		ProjectContext projectContext = ProjectContext.loadById(projectId);
		projectContext.delete();
	}
	
	@Override
	public List<ProjectVo> listProjects(){
		ProjectContext projectContext = ProjectContext.build();
		return projectContext.listProjects();
	}

	@Override
	public DataPageValue<ProjectVo> findProjects(int page, int size) {
		ProjectContext projectContext = ProjectContext.build();
		return projectContext.findProjects(page, size);
	}

	@Override
	public void createProjectBuilding(ProjectBuildingVo projectBuildingVo) throws LiefengException {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.build(projectBuildingVo);
		projectBuildingContext.create();
	}

	@Override
	public void updateProjectBuilding(ProjectBuildingVo projectBuildingVo) {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.build(projectBuildingVo);
		projectBuildingContext.update();
	}

	@Override
	public void deleteProjectBuilding(String projectBuildingId) {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadById(projectBuildingId);
		projectBuildingContext.delete();
	}

	@Override
	public DataPageValue<ProjectBuildingVo> findBuildingsByProjectId(String projectId, int page, int size) {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadByProjectId(projectId);
		return projectBuildingContext.findBuildingsByProjectId(page, size);
	}

	@Override
	public DataPageValue<ProjectBuildingVo> findFloorsByBuildingId(String projectBuildingId, int page, int size) {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadById(projectBuildingId);
		return projectBuildingContext.findFloorsByBuildingId(page, size);
	}

	@Override
	public void createHouse(HouseVo houseVo) throws LiefengException {
		HouseContext houseContext = HouseContext.build(houseVo);
		houseContext.create();
	}

	@Override
	public void updateHouse(HouseVo houseVo) {
		HouseContext houseContext = HouseContext.build(houseVo);
		houseContext.update();
	}
	
	@Override
	public HouseVo findHouseById(String houseId) {
		HouseContext houseContext = HouseContext.loadById(houseId);
		HouseVo house = houseContext.get();
		
		ProjectBuildingContext buildingContext = ProjectBuildingContext.loadById(house.getBuildingId());
		ProjectBuildingVo building = buildingContext.get();
		
		ProjectBuildingContext floorContext = ProjectBuildingContext.loadById(house.getFloorId());
		ProjectBuildingVo floor = floorContext.get();
		
		house.setBuildingName(building.getName());
		house.setFloorName(floor.getName());
		return house;
	}
	
	@Override
	public HouseVo findHouse(String projectId, String houseNum){
		HouseContext houseContext = HouseContext.loadByProjectIdAndHouseNum(projectId, houseNum);
		HouseVo house = houseContext.get();
		
		return house;
	}

	@Override
	public List<HouseVo> findHouseList(String projectId, String buildingId) {
		HouseContext houseContext = HouseContext.loadByProjectIdAndBuildingId(projectId, buildingId);
		return houseContext.getHouseList();
	}

	@Override
	public DataPageValue<ProprietorSingleHouseVo> listHouse4Page(HouseBo houseBo, Integer page, Integer size) {
		HouseContext context = HouseContext.build();
		return context.listHouse4Page(houseBo, page, size);
	}

	@Override
	public ProjectBuildingVo findProjectBuildingById(String projectBuildingId) {
		ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadById(projectBuildingId);
		ProjectBuildingVo projectBuildingVo = projectBuildingContext.get();
		return projectBuildingVo;
	}

	@Override
	public void createHouseSpec(HouseSpecVo houseSpec) throws LiefengException{
		HouseSpecContext houseSpecContext = HouseSpecContext.build(houseSpec);
		houseSpecContext.create();
	}

	@Override
	public void upateHouseSpec(HouseSpecVo houseSpec) {
		HouseSpecContext houseSpecContext = HouseSpecContext.build(houseSpec);
		houseSpecContext.update();
	}

	@Override
	public DataPageValue<HouseSpecVo> listHouseSpec4Page(HouseSpecBo params, Integer page, Integer size) {
		HouseSpecContext houseSpecContext = HouseSpecContext.build();
		DataPageValue<HouseSpecVo> dataPage = houseSpecContext.findHouseSpecs4Page(params, page, size);
		return dataPage;
	}
	
	@Override
	public HouseSpecVo findHouseSpecById(String houseSpecId) {
		HouseSpecContext houseSpecContext = HouseSpecContext.loadById(houseSpecId);
		HouseSpecVo houseSpecVo = houseSpecContext.getHouseSpec();
		
		// 设置项目名称
		ProjectContext projectContext = ProjectContext.loadById(houseSpecVo.getProjectId());
		ProjectVo projectVo = projectContext.get();
		
		houseSpecVo.setProjectName(projectVo.getFullName());
		
		return houseSpecVo;
	}

	@Override
	public String deleteHouseSpec(String houseSpecId) {
		HouseSpecContext houseSpecContext = HouseSpecContext.loadById(houseSpecId);
		return houseSpecContext.delete();
	}

	@Override
	public ProprietorSingleHouseVo getSingleHouse(String houseId) {
		HouseContext houseContext = HouseContext.loadById(houseId);
		return houseContext.getSingleHouse();
	}

	@Override
	public List<ProjectVo> findProjectByStaffId(String staffId) {
		return ProjectContext.build().findProjectsByStaffId(staffId);
	}

	@Override
	public List<String> findProjectIdByStaffId(String staffId) {
		List<ProjectVo> projectList = this.findProjectByStaffId(staffId);
		List<String> projectIdList = new ArrayList<String>();
		for (ProjectVo projectVo : projectList) {
			projectIdList.add(projectVo.getId());
		}
		return projectIdList;
	}

	@Override
	public ProjectNoticeVo createProjectNotice(ProjectNoticeVo projectNotice) {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build(projectNotice);
		ProjectNoticeVo projectNoticeVo = projectNoticeContext.create();
		
		//业主、住户clientId
		List<UserClientIdVo> proprietorClientIdList = householdService.listClientIdByProjectId(projectNoticeVo.getProjectId());
		//员工clientId
		List<UserClientIdVo> staffClientIdList = propertyStaffService.findStaffClientIdList("", projectNoticeVo.getProjectId());
		//发布物业须知时群推消息
		propertyPushMsgService.pushMsgOfUserIdClientId(PushActionConstants.PROPERTY_NEW_NOTE, staffClientIdList, proprietorClientIdList, null);
			
		return projectNoticeVo;
	}

	@Override
	public void deleteProjectNotice(String projectNoticeIds) {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.loadById(projectNoticeIds);
		projectNoticeContext.delete();
	}

	@Override
	public ProjectNoticeVo updateProjectNotice(ProjectNoticeVo projectNotice) {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build(projectNotice);
		ProjectNoticeVo projectNoticeVo = projectNoticeContext.update();
		
		//业主、住户clientId
		List<UserClientIdVo> proprietorClientIdList = householdService.listClientIdByProjectId(projectNoticeVo.getProjectId());
		//员工clientId
		List<UserClientIdVo> staffClientIdList = propertyStaffService.findStaffClientIdList("", projectNoticeVo.getProjectId());
		//发布物业须知时群推消息
		propertyPushMsgService.pushMsgOfUserIdClientId(PushActionConstants.PROPERTY_NEW_NOTE, staffClientIdList, proprietorClientIdList, null);
				
		return projectNoticeVo;
	}

	@Override
	public ProjectNoticeVo getProjectNotice(String projectNoticeId) {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.loadById(projectNoticeId);
		ProjectNoticeVo projectNotice = projectNoticeContext.get();
		
		// 设置小区名称
		if(projectNotice != null && ValidateHelper.isNotEmptyString(projectNotice.getProjectId())) {
			ProjectContext projectContext = ProjectContext.loadById(projectNotice.getProjectId());
			ProjectVo project = projectContext.get();
			
			if(project != null) {
				projectNotice.setProjectName(project.getFullName());
			}
		}
		
		return projectNotice;
	}

	@Override
	public DataPageValue<ProjectNoticeVo> findProjectNotices(String projectId, Integer currentPage, Integer pageSize) {
		ProjectNoticeContext projectNoticeContext = ProjectNoticeContext.build();
		DataPageValue<ProjectNoticeVo> dataPage = projectNoticeContext.findProjectNotices(projectId, currentPage, pageSize);
		
		return dataPage;
	}

	@Override
	public AppHomeImageVo createAppHomeImage(AppHomeImageVo appHomeImage) {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build(appHomeImage);
		return appHomeImageContext.create();
	}

	@Override
	public void deleteAppHomeImage(String appHomeImageIds) {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.loadById(appHomeImageIds);
		appHomeImageContext.delete();
		
		// TODO 以后补上删除OSS上逻辑业务
	}

	@Override
	public AppHomeImageVo updateAppHomeImage(AppHomeImageVo appHomeImage) {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build(appHomeImage);
		return appHomeImageContext.update();
	}

	@Override
	public AppHomeImageVo getAppHomeImage(String appHomeImageId) {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.loadById(appHomeImageId);
		AppHomeImageVo appHomeImage = appHomeImageContext.get();
		
		// 设置小区名称
		if(appHomeImage != null && ValidateHelper.isNotEmptyString(appHomeImage.getProjectId())) {
			ProjectContext projectContext = ProjectContext.loadById(appHomeImage.getProjectId());
			ProjectVo project = projectContext.get();
			
			if(project != null) {
				appHomeImage.setProjectName(project.getFullName());
			}
		}
		
		return appHomeImageContext.get();
	}

	@Override
	public DataPageValue<AppHomeImageVo> findAppHomeImages(String projectId, Integer currentPage, Integer pageSize) {
		AppHomeImageContext appHomeImageContext = AppHomeImageContext.build();
		DataPageValue<AppHomeImageVo> dataPage = appHomeImageContext.findAppHomeImages(projectId, currentPage, pageSize);
		
		return dataPage;
	}
	
	@Override
	public List<HouseGraphVo> getHouseGraphs(HouseBo param) {
		List<HouseGraphVo> dataList = new ArrayList<HouseGraphVo>();
		
		String projectId = param.getProjectId();
		String buildingId = param.getBuildingId();
		
		if(ValidateHelper.isNotEmptyString(buildingId)) { // 仅查单个楼栋的
			
			ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadById(buildingId);
			ProjectBuildingVo building = projectBuildingContext.get();
			
			if(building != null) {
				HouseGraphVo houseGraph = initHouseGraph(param, building);
				dataList.add(houseGraph);
			}
		} else if(ValidateHelper.isNotEmptyString(projectId)) { // 查询所有楼栋的
			
			ProjectBuildingContext projectBuildingContext = ProjectBuildingContext.loadByProjectId(projectId);
			DataPageValue<ProjectBuildingVo> dataPage = projectBuildingContext.findBuildingsByProjectId(1, Integer.MAX_VALUE);
			if(dataPage != null && ValidateHelper.isNotEmptyCollection(dataPage.getDataList())) {
				for(ProjectBuildingVo building : dataPage.getDataList()) {
					HouseGraphVo houseGraph = initHouseGraph(param, building);
					dataList.add(houseGraph);
				}
			}
		}
		
		return dataList;
	}

	/**
	 * 初始化房产视图对象
	 * @param param 查询参数
	 * @param building 楼栋对象
	 * @return 房产视图对象
	 */
	private HouseGraphVo initHouseGraph(HouseBo param, ProjectBuildingVo building) {
		// 查询楼栋中房子
		HouseContext houseContext = HouseContext.build();
		param.setBuildingId(building.getId());
		List<ProprietorSingleHouseVo> houses = houseContext.getHouseGraphs(param);
		
		/*// 查询楼栋中房产模型数量
		HouseSpecBo houseSpecBo = MyBeanUtil.createBean(param, HouseSpecBo.class);
		HouseSpecContext houseSpecContext = HouseSpecContext.build();
		Long houseSpecCount = houseSpecContext.getHouseSpecCount(houseSpecBo);*/
		
		//获取每层的房间说
		List<HouseSpecVo> houseSpecs = countHouseSpecList(param.getProjectId(), param.getBuildingId()); 
		
		// 初始化视图模型设值
		HouseGraphVo houseGraph = new HouseGraphVo();
		houseGraph.setHouses(houses);
		houseGraph.setBuildingName(building.getName());
		//houseGraph.setHouseSpecCount(houseSpecCount); 
		houseGraph.setHouseSpecs(houseSpecs);
		return houseGraph;
	}

	@Override
	public HouseGraphVo getHouseGraphCount(HouseBo param) {
		HouseContext houseContext = HouseContext.build();
		return houseContext.getHouseGraphsCount(param);
	}
	
	@Override
	public List<ProjectVo> findAllProject(){
		return ProjectContext.build().findAll();
	}

	@Override
	public List<ProjectBuildingVo> findProjectBuildingByBuildingAndNum(String buildingId,
			String num) {
		return ProjectBuildingContext.build().findByBuildingAndNum(buildingId, num);
	}

	@Override
	public HouseSpecVo findHouseSpec(String id) {
		return HouseSpecContext.loadById(id).findHouseSpec();
	}

	@Override
	public List<HouseSpecVo> countHouseSpecList(String projectId,
			String buildingId) {
		return HouseSpecContext.build().countByProjectIdAndBuildingId(projectId, buildingId);
	}

	@Override
	public ProjectBuildingVo findBuilding(String projectId, String parentId,
			String name) {
		return ProjectBuildingContext.build().findBuilding(projectId,parentId,name);
	}

	@Override
	public DataPageValue<HouseCheckitemConfigVo> findHouseCheckitemConfigByPage(String projectId,
			String parentId, Integer page, Integer size) {
		return HouseCheckitemConfigContext.build().findByPage(projectId, parentId, page, size);
	}

	@Override
	public HouseCheckitemConfigVo createHouseCheckitemConfig(
			HouseCheckitemConfigVo houseCheckitemConfigVo) {
		return HouseCheckitemConfigContext.build(houseCheckitemConfigVo).create();
	}

	@Override
	public HouseCheckitemConfigVo updateHouseCheckitemConfig(
			HouseCheckitemConfigVo houseCheckitemConfigVo) {
		return HouseCheckitemConfigContext.build(houseCheckitemConfigVo).update();
	}

	@Override
	public HouseCheckitemConfigVo getHouseCheckitemConfig(String id) {
		return HouseCheckitemConfigContext.loadById(id).get();
	}

	@Override
	public Long getSortindex(String projectId, String parentId) {
		return HouseCheckitemConfigContext.build().getSortindex(projectId, parentId);
	}

	@Override
	public void deleteHouseCheckitemConfig(String id) {
		HouseCheckitemConfigContext.loadById(id).delete();
	}

	@Override
	public DataPageValue<HouseSpecVo> findHouseSpecForHouseCheck(
			HouseSpecBo params, Integer page, Integer size) {
		return HouseSpecContext.build().findHouseSpecForHouseCheck(params, page, size);
	}

	@Override
	public void createHouseCheck(HouseCheckVo houseCheckVo) {
		List<HouseCheckVo> houseCheckVoList = null;
		if(houseCheckVo != null && ValidateHelper.isNotEmptyString(houseCheckVo.getItemResult())){
			houseCheckVoList = new ArrayList<HouseCheckVo>();
			
			String[] parentResult = houseCheckVo.getItemResult().split("\\|");
			
			for(int i = 0; i < parentResult.length; i++){
				String[] childResult = parentResult[i].split(",");
				
				HouseCheckVo houseCheck = new HouseCheckVo();
				MyBeanUtil.copyBeanNotNull2Bean(houseCheckVo, houseCheck);
				houseCheck.setCreate_time(new Date());
				houseCheck.setId(UUIDGenerator.generate());
				houseCheck.setOemCode(ContextManager.getInstance().getOemCode());
				houseCheck.setItemId(childResult[0]);
				houseCheck.setResultId(childResult[1]);
				
				houseCheckVoList.add(houseCheck);
			}
		}
	  HouseCheckContext.build().create(houseCheckVoList);
	}

	@Override
	public void deleteHouseCheck(String projectId, String houseNum) {
		HouseCheckContext.build().delete(projectId, houseNum);
	}

	@Override
	public List<HouseCheckVo> getHouseCheck(String projectId, String houseNum) {
		return HouseCheckContext.build().getHouseCheck(projectId, houseNum);
	}
}
