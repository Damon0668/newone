package com.liefeng.property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.domain.project.HouseSpecContext;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目服务实现类
 * 
 * @author Huangama
 * @author levy
 * @date 2015-12-22
 */
@Service
public class ProjectService implements IProjectService {
	
	@Override
	public ProjectVo findProjectById(String projectId) {
		ProjectContext projectContext = ProjectContext.loadById(projectId);
		ProjectVo projectVo = projectContext.getProject();
		return projectVo;
	}
	
	@Override
	public ProjectVo findProjectByFullName(String fullName) {
		ProjectContext projectContext = ProjectContext.loadByFullName(fullName);
		ProjectVo projectVo = projectContext.getProject();
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
		HouseVo house = houseContext.getHouse();
		
		ProjectBuildingContext buildingContext = ProjectBuildingContext.loadById(house.getBuildingId());
		ProjectBuildingVo building = buildingContext.getProjectBuilding();
		
		ProjectBuildingContext floorContext = ProjectBuildingContext.loadById(house.getFloorId());
		ProjectBuildingVo floor = floorContext.getProjectBuilding();
		
		house.setBuildingName(building.getName());
		house.setFloorName(floor.getName());
		return house;
	}
	
	@Override
	public HouseVo findHouse(String projectId, String houseNum){
		HouseContext houseContext = HouseContext.loadByProjectIdAndHouseNum(projectId, houseNum);
		HouseVo house = houseContext.getHouse();
		
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
		ProjectBuildingVo projectBuildingVo = projectBuildingContext.getProjectBuilding();
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
		ProjectVo projectVo = projectContext.getProject();
		
		houseSpecVo.setProjectName(projectVo.getFullName());
		
		return houseSpecVo;
	}

	@Override
	public void deleteHouseSpec(String houseSpecId) {
		HouseSpecContext houseSpecContext = HouseSpecContext.loadById(houseSpecId);
		houseSpecContext.delete();
	}

	@Override
	public ProprietorSingleHouseVo getSingleHouse(String houseId) {
		HouseContext houseContext = HouseContext.loadById(houseId);
		return houseContext.getSingleHouse();
	}
}
