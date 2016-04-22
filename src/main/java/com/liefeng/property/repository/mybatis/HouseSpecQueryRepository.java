package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

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
	
	
	/**
	 * 根据id，获取房间规格信息
	 * @param paramMap
	 * @return 
	 * @author xhw
	 * @date 2016年4月22日 上午11:15:32
	 */
	public HouseSpecVo queryByHouseSpecId(PagingParamVo paramMap);
}
