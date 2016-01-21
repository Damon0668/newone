package com.liefeng.property.service;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
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
	public void createProject(ProjectVo projectVo) {
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
	public DataPageValue<ProjectVo> findProjects(int page, int size) {
		ProjectContext projectContext = ProjectContext.build();
		return projectContext.findProjects(page, size);
	}

	@Override
	public void createProjectBuilding(ProjectBuildingVo projectBuildingVo) {
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
	public void createHouse(HouseVo houseVo) {
		HouseContext houseContext = HouseContext.build(houseVo);
		houseContext.create();
	}

	@Override
	public void updateHouse(HouseVo houseVo) {
		HouseContext houseContext = HouseContext.build(houseVo);
		houseContext.update();
	}

	@Override
	public DataPageValue<ProprietorSingleHouseVo> listHouse4Page(HouseBo houseBo, Integer page, Integer size) {
		HouseContext context = HouseContext.build();
		return context.listHouse4Page(houseBo, page, size);
	}

}
