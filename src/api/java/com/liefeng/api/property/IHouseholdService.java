package com.liefeng.api.property;

import java.util.Map;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * household包相关表接口
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public interface IHouseholdService {
	/**
	 * 保存业主信息
	 * 
	 * @param proprietor 业主信息值对象
	 * @param proprietorHouse 业主房产值对象
	 */
	public void saveProprietor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse);
	
	/**
	 * 更新业主信息
	 * 
	 * @param proprietor 业主信息值对象
	 * @param proprietorHouse 业主房产值对象
	 */
	public void updatePropritor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse);
	
	/**
	 * 保存住户信息
	 * @param resident 住户信息值对象
	 */
	public void saveResident(ResidentVo resident);
	
	/**
	 * 分页查询业主信息
	 * 
	 * @param params 查询过滤参数
	 * @param pageSize 分页大小
	 * @param currentPage 分页当前页
	 * @return 业主分页数据
	 */
	public DataPageValue<ProprietorSingleHouseVo> listProprietor4Page(Map<String, String> params, Integer pageSize,
			Integer currentPage);
	
	/**
	 * 获取业主某个房产的信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 业主房产信息
	 */
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId);
}
