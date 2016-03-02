package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.base.vo.UserVo;
import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;

/**
 * 业主信息查询接口
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public interface ProprietorQueryRepository extends BaseRepository<ProprietorSingleHouseVo> {
	
	/**
	 * 获取业主某个房产的信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 业主某个房产信息
	 */
	public ProprietorSingleHouseVo queryProprietorSingleHouse(String proprietorHouseId);
	
	/**
	 * 查询业主用户信息
	 * @param param 查询过滤参数
	 * @return 
	 */
	public List<UserVo> queryProprietorUser(PagingParamVo param);
	
	/**
	 * 查询业主用户信息总数
	 * @param param 查询过滤参数 
	 * @return
	 */
	public Long queryProprietorUserCount(PagingParamVo param);
	
}
