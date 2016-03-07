package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.household.CheckinMaterialVo;

/**
 * 入住资料mybatis查询接口
 * 
 * @author ZhenTingJun
 * @date 2016年3月1日
 */
public interface CheckinMaterialQueryRepository extends BaseRepository<CheckinMaterialVo> {
	
	/**
	 * 根据业主房产ID获取入住资料
	 * @param proprietorHouseId 业主房产ID
	 * @return 入住资料列表
	 */
	public List<CheckinMaterialVo> queryByProprietorHouseId(String proprietorHouseId);
}
