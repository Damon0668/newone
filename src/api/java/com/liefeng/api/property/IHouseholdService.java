package com.liefeng.api.property;

import com.liefeng.property.vo.household.ProprietorHouseVo;
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
}
