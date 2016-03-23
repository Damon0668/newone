package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.project.HouseSpecVo;

/**
 * 房产规格Mybatis查询仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年2月18日
 */
public interface HouseSpecQueryRepository extends BaseRepository<HouseSpecVo>{
	
	/**
	 * 查询已关联房型的房产数量
	 * @param houseSpecId 房型ID
	 * @return 房产数量
	 */
	public Long queryRelatedHouseCount(String houseSpecId);
	
}
