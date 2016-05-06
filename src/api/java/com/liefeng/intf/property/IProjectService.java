package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.vo.household.HouseGraphVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.AppHomeImageVo;
import com.liefeng.property.vo.project.HouseCheckVo;
import com.liefeng.property.vo.project.HouseCheckitemConfigVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectNoticeVo;
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
	 * 根据员工ID查找项目列表
	 * @param staffId 员工ID
	 * @return
	 */
	public List<ProjectVo> findProjectByStaffId(String staffId);
	
	/**
	 * 根据员工ID查找项目ID列表
	 * @param staffId 员工ID
	 * @return
	 */
	public List<String> findProjectIdByStaffId(String staffId);

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
	 * @param houseSpec 房产规格值对象
	 */
	public void upateHouseSpec(HouseSpecVo houseSpec);
	
	/**
	 * 删除房产规格
	 * @param houseSpecId 房产规格ID 或 以“,”分隔的ID串
	 * @return 删除信息
	 */
	public String deleteHouseSpec(String houseSpecId);

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

	/**
	 * 保存小区通告
	 * @param projectNotice 小区通告值对象
	 * @return 小区通告信息
	 */
	public ProjectNoticeVo createProjectNotice(ProjectNoticeVo projectNotice);
	
	/**
	 * 删除小区通告
	 * @param projectNoticeIds 小区通告ID串（以","隔开）
	 */
	public void deleteProjectNotice(String projectNoticeIds);
	
	
	/**
	 * 更新小区通告信息
	 * @param projectNotice 小区通告值对象
	 * @return 小区通告信息
	 */
	public ProjectNoticeVo updateProjectNotice(ProjectNoticeVo projectNotice);
	
	/**
	 * 根据ID查询小区通告
	 * @param projectNoticeId 小区通告ID
	 * @return 小区通告信息
	 */
	public ProjectNoticeVo getProjectNotice(String projectNoticeId);
	
	/**
	 * 分页获取小区通告信息
	 * @param projectId 小区ID
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return 小区通告分页数据
	 */
	public DataPageValue<ProjectNoticeVo> findProjectNotices(String projectId, Integer currentPage, Integer pageSize);
	
	/**
	 * 保存首页轮播图信息
	 * @param appHomeImage 轮播图值对象
	 * @return 轮播图信息
	 */
	public AppHomeImageVo createAppHomeImage(AppHomeImageVo appHomeImage);
	
	/**
	 * 删除首页轮播图信息
	 * @param appHomeImageIds 轮播图ID串（以","隔开）
	 */
	public void deleteAppHomeImage(String appHomeImageIds);
	
	/**
	 * 更新首页轮播图信息
	 * @param appHomeImage 轮播图值对象
	 * @return 轮播图信息
	 */
	public AppHomeImageVo updateAppHomeImage(AppHomeImageVo appHomeImage);
	
	/**
	 * 根据ID查询首页轮播图信息
	 * @param appHomeImageId 轮播图ID
	 * @return 轮播图信息
	 */
	public AppHomeImageVo getAppHomeImage(String appHomeImageId);
	
	/**
	 * 分页查询首页轮播图信息
	 * @param projectId 小区ID
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return 轮播图分页信息
	 */
	public DataPageValue<AppHomeImageVo> findAppHomeImages(String projectId, Integer currentPage, Integer pageSize);
	
	/**
	 * 查询房产视图数据
	 * @param param 查询过滤参数
	 * @return 房产数据
	 */
	public List<HouseGraphVo> getHouseGraphs(HouseBo param);
	
	/**
	 * 查询房产视图合计数据
	 * @param param 查询过滤参数
	 * @return 房产合计数据
	 */
	public HouseGraphVo getHouseGraphCount(HouseBo param);
	
	/**
	 * 获取数据库中所有项目
	 * @return
	 */
	public List<ProjectVo> findAllProject();
	
	/**
	 * 获取某楼栋的所有楼层，并对某楼层房间是否进行了房型初始化进行标记
	 * @param buildingId 楼栋id
	 * @param num 房间号后两位
	 * @return 
	 * @author xhw
	 * @date 2016年4月22日 上午11:34:10
	 */
	public List<ProjectBuildingVo> findProjectBuildingByBuildingAndNum(String buildingId, String num);
	
	/**
	 * 获取房产规格信息(项目名称、楼栋名称、楼层名称）
	 * @return 房产规格值对象
	 */
	public HouseSpecVo findHouseSpec(String id);
	
	/**
	 * 获取某项目某楼栋中的每一层的房间数
	 * @param projectId
	 * @param buildingId
	 * @return 
	 * @author xhw
	 * @date 2016年4月24日 下午3:53:03
	 */
	public List<HouseSpecVo> countHouseSpecList(String projectId, String buildingId);
	
	/**
	 * 获取楼栋或楼层
	 * @param projectId	项目id
	 * @param name 楼栋楼层名称
	 * @param parentId 楼栋名称 null就查询楼栋 ，非空查询楼层
	 * @return
	 */
	public ProjectBuildingVo findBuilding(String projectId,String parentId,
			String name);

	/**
	 * 分页获取房屋验收项配置
	 * @param projectId
	 * @param parentId
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午5:43:56
	 */
	public DataPageValue<HouseCheckitemConfigVo> findHouseCheckitemConfigByPage(String projectId, String parentId, Integer page, Integer size);
	
	/**
	 * 创建房屋验收项
	 * @param houseCheckitemConfigVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月28日 下午5:59:44
	 */
	public HouseCheckitemConfigVo createHouseCheckitemConfig(HouseCheckitemConfigVo houseCheckitemConfigVo);
	
	/**
	 * 更新房屋验收项配置
	 * @param houseCheckitemConfigVo
	 * @return 
	 * @author xhw
	 * @date 2016年4月28日 下午6:01:00
	 */
	public HouseCheckitemConfigVo updateHouseCheckitemConfig(HouseCheckitemConfigVo houseCheckitemConfigVo);
	
	/**
	 * 根据id，获取房屋验收项配置
	 * @param id
	 * @return 
	 * @author xhw
	 * @date 2016年4月28日 下午6:02:13
	 */
	public HouseCheckitemConfigVo getHouseCheckitemConfig(String id);
	
	/**
	 * 获取默认排序号
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 下午4:52:35
	 */
	public Long getSortindex(String projectId, String parentId);
	
	/**
	 * 删除房屋验收配置
	 * @param id 
	 * @author xhw
	 * @date 2016年4月28日 下午6:04:45
	 */
	public void deleteHouseCheckitemConfig(String id);
	
	/**
	 * 分页查询房屋规格（验收管理专用）
	 * @param params
	 * @param page
	 * @param size
	 * @return 
	 * @author xhw
	 * @date 2016年5月5日 上午10:47:50
	 */
	public DataPageValue<HouseSpecVo> findHouseSpecForHouseCheck(HouseSpecBo params, Integer page, Integer size);
	
	/**
	 * 创建验收单
	 * @param houseCheckVo 
	 * @author xhw
	 * @date 2016年5月5日 下午6:08:10
	 */
	public void createHouseCheck(HouseCheckVo houseCheckVo);
	
	/**
	 * 删除验收单
	 * @param projectId
	 * @param houseNum 
	 * @author xhw
	 * @date 2016年5月6日 上午8:35:37
	 */
	public void deleteHouseCheck(String projectId, String houseNum);
	
	/**
	 * 获取房屋验收单
	 * @param projectId
	 * @param houseNum
	 * @return 
	 * @author xhw
	 * @date 2016年5月6日 上午9:49:23
	 */
	public List<HouseCheckVo> getHouseCheck(String projectId, String houseNum);
}
