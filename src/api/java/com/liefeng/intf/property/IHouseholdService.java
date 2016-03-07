package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.base.vo.UserVo;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.household.CheckinQueueBo;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.vo.household.CheckinMaterialVo;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * household包相关表接口类
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public interface IHouseholdService {
	/**
	 * 保存业主信息
	 * 
	 * @param singleHouse 业主单个房产信息对象
	 */
	public void saveProprietor(ProprietorSingleHouseVo singleHouse) throws LiefengException;
	
	/**
	 * 更新业主信息
	 * 
	 * @param singleHouse 业主单个房产信息对象
	 */
	public void updatePropritor(ProprietorSingleHouseVo singleHouse) throws LiefengException;
	
	/**
	 * 保存住户信息
	 * @param resident 住户信息值对象
	 */
	public void saveResident(ResidentVo resident) throws LiefengException;
	
	/**
	 * 更新住户信息
	 * @param resident 住户信息值对象
	 */
	public void updateResident(ResidentVo resident) throws LiefengException;
	
	/**
	 * 分页查询业主综合信息
	 * 
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 业主分页数据
	 */
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize,
			Integer currentPage);
	
	/**
	 * 获取业主某个房产的信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 业主房产信息
	 */
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId);
	
	/**
	 * 分页查询住户信息
	 * @param params 查询参数封装类对象
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 住户分页信息
	 */
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize, Integer currentPage);
	
	/**
	 * 查询住户信息
	 * @param residentId 住户ID
	 * @return 住户信息
	 */
	public ResidentVo getResident(String residentId);
	
	/**
	 * 根据项目ID和房号查询业主房产信息
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @return 业主房产信息
	 */
	public ProprietorHouseVo getProprietorHouse(String projectId, String houseNum);
	
	/**
	 * 根据业主房产ID查询入住资料信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 入住资料列表
	 */
	public List<CheckinMaterialVo> getCheckinMaterialByProprietorHouseId(String proprietorHouseId);
	
	/**
	 * 批量保存入住资料
	 * @param checkinMaterialList
	 */
	public void saveCheckinMaterials(List<CheckinMaterialVo> checkinMaterialList) throws Exception;
	
	/**
	 * 根据业主房产ID删除入住资料信息
	 * @param proprietorHouseId 业主房产ID
	 */
	public void delCheckinMaterialByProprietorHouseId(String proprietorHouseId) throws Exception;
	
	/**
	 * 根据业主ID获取业主信息
	 * @param id 业主ID
	 * @return 业主信息
	 */
	public ProprietorVo getProprietorById(String id);
	
	/**
	 * 分页查询业主用户信息
	 * @param params 查询过滤参数
	 * @param currentPage 分页当前页
	 * @param pageSize 分页大小
	 * @return
	 */
	public DataPageValue<UserVo> listProprietorUser(ProprietorBo params, Integer currentPage, Integer pageSize);
	
	/**
	 * 获取业主所有房产中的住户
	 * @param projectId 项目ID
	 * @param custGlobalId 业主关联客户全局ID
	 * @return 住户信息
	 */
	public List<ResidentVo> getResidentsInProprietorHouse(String projectId, String custGlobalId);
	
	/**
	 * 分页查询入住排队信息
	 * @param params 查询过滤参数
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 入住排队列表
	 */
	public DataPageValue<CheckinQueueVo> getCheckinQueues(CheckinQueueBo params, Integer pageSize, Integer currentPage);
	
	/**
	 * 更新入住排队信息
	 * @param checkinQueue 入住排队值对象
	 * @throws LiefengException
	 */
	public void updateCheckinQueue(CheckinQueueVo checkinQueue) throws LiefengException;
	
}
