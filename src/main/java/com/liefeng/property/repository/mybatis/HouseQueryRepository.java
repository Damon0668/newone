package com.liefeng.property.repository.mybatis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.api.UserHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;

/**
 * t_house 房产资料查询 基本的查询父接口已经有了
 * Mybatis 直接返回vo
 * HouseVo字段不能满足需求，所以使用ProprietorSingleHouseVo
 * @author levy
 * @date 2016年1月4日
 */
public interface HouseQueryRepository extends BaseRepository<ProprietorSingleHouseVo>{
	
	/**
	 * 获取房产图形界面数据
	 * @param param 查询过滤参数
	 * @return 房子列表
	 */
	public List<ProprietorSingleHouseVo> queryGraphData(PagingParamVo param);
	
	/**
	 * 获取房产图形界面统计数据
	 * @param param 查询过滤参数
	 * @return 已录入数和未录入数
	 */
	public Map<String, BigDecimal> queryGraphCount(PagingParamVo param);
	
	/**
	 * 根据登陆ID查询用户房产
	 * APP 房产列表
	 * @param custGlobalId 用户全局ID
	 * @return 房产列表
	 */
	public List<UserHouseVo> queryUserHouses(@Param("custGlobalId") String custGlobalId);
	
	/**
	 * 根据全局ID查询用户房产
	 * @param custGlobalId 用户全局ID
	 * @param oemCode 
	 * @return 房产列表
	 */
	public List<UserHouseVo> queryUserHousesByOem(@Param("custGlobalId") String custGlobalId, @Param("oemCode") String oemCode);
}
