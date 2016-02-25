package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 项目接口
 * 
 * @author Huangama
 * @date 2015-12-22
 */
public interface IProjectService {

	/**
	 * 通过ID查找具体项目
	 * 
	 * @param projectId 项目ID
	 * @return 具体项目对象
	 */
	public ProjectVo findProjectById(String projectId);

	/**
	 * 根据项目全名查找具体项目
	 * 
	 * @param fullName 项目全名
	 * @return 具体项目对象
	 */
	public ProjectVo findProjectByFullName(String fullName);
	
	/**
	 * 根据员工ID查找项目
	 * @param staffId 员工ID
	 * @return
	 */
	public List<ProjectVo> findProjectByStaffId(String staffId);

	/**
	 * 创建项目
	 * 
	 * @param projectVo
	 */
	public void createProject(ProjectVo projectVo) throws LiefengException;

	/**
	 * 更新项目
	 * 
	 * @param projectVo
	 */
	public void updateProject(ProjectVo projectVo);

	/**
	 * 删除项目
	 * 
	 * @param projectVo
	 */
	public void deleteProject(String projectId);

	/**
	 * 获取oem下所有的项目列表
	 * @return
	 */
	public List<ProjectVo> listProjects();

	/**
	 * 分页查询项目
	 * 
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProjectVo> findProjects(int page, int size);

	/*
	 * projectBuilding 楼栋与楼层 ******
	 */

	/**
	 * 通过ID查找具体楼栋楼层
	 * 
	 * @param projectBuildingId 楼栋楼层ID
	 * @return 具体楼栋楼层对象
	 */
	public ProjectBuildingVo findProjectBuildingById(String projectBuildingId);

	/**
	 * 创建楼栋或楼层， parentId=null 为楼栋
	 * 
	 * @param projectBuildingVo
	 */
	void createProjectBuilding(ProjectBuildingVo projectBuildingVo) throws LiefengException;

	/**
	 * 更新楼栋或楼层， parentId=null 为楼栋
	 * 
	 * @param projectBuildingVo
	 */
	void updateProjectBuilding(ProjectBuildingVo projectBuildingVo);

	/**
	 * 删除楼栋或楼层
	 * 
	 * @param ProjectBuildingId
	 */
	void deleteProjectBuilding(String projectBuildingId);

	/**
	 * 分页查询项目下的楼栋
	 * 
	 * @param projectId 项目id
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findBuildingsByProjectId(String projectId, int page, int size);

	/**
	 * 分页查询楼栋下的楼层
	 * 
	 * @param buildingId 楼栋id
	 * @param page 第几页，最小为1
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProjectBuildingVo> findFloorsByBuildingId(String buildingId, int page, int size);

	/*
	 * house 房产资料 ******
	 */

	/**
	 * 创建房产资料
	 * 
	 * @param houseVo
	 */
	public void createHouse(HouseVo houseVo) throws LiefengException;

	/**
	 * 更新房产资料
	 * 
	 * @param houseVo
	 */
	public void updateHouse(HouseVo houseVo);
	
	/**
	 * 根据ID查找房产资料
	 * @param houseId 房产资料ID
	 * @return 房产资料
	 */
	public HouseVo findHouseById(String houseId);
	
	/**
	 * 根据项目ID和房号查询房产
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @return 房产资料
	 */
	public HouseVo findHouse(String projectId, String houseNum);
	
	/**
	 * 根据项目ID和楼栋ID查找房产资料
	 * @param projectId 项目ID
	 * @param buildingId 楼栋ID
	 * @return 房产资料列表
	 */
	public List<HouseVo> findHouseList(String projectId, String buildingId);

	/**
	 * 分页查询房产信息
	 * 
	 * @param params 查询参数封装类对象
	 * @param page 当前页
	 * @param size 页面大小
	 * @return
	 */
	public DataPageValue<ProprietorSingleHouseVo> listHouse4Page(HouseBo houseBo, Integer page, Integer size);

	/**
	 * 保存房产规格
	 * 
	 * @param houseSpec 房产规格值对象
	 */
	public void createHouseSpec(HouseSpecVo houseSpec) throws LiefengException;

	/**
	 * 更新房产规格
	 * 
	 * @param houseSpec 房产规格值对象
	 */
	public void upateHouseSpec(HouseSpecVo houseSpec);
	
	/**
	 * 更新房产规格
	 * 
	 * @param houseSpecId 房产规格ID 或 以“,”分隔的ID串
	 */
	public void deleteHouseSpec(String houseSpecId);

	/**
	 * 分页查询房产规格
	 * @param params 查询参数封装类对象
	 * @param page 当前页
	 * @param size 分页大小
	 * @return
	 */
	public DataPageValue<HouseSpecVo> listHouseSpec4Page(HouseSpecBo params, Integer page, Integer size);
	
	/**
	 * 根据ID查询房产规格
	 * @param houseSpecId 房产规格ID
	 * @return
	 */
	public HouseSpecVo findHouseSpecById(String houseSpecId);

	/**
	 * 获取业主单套房产的信息
	 * @param houseId 房产ID
	 * @return 
	 */
	public ProprietorSingleHouseVo getSingleHouse(String houseId);
	
}
