package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.ResidentHouseVo;

/**
 * 住户房子关系查询
 * @author xhw
 * @date 2016年4月26日 上午10:52:07
 */
public interface ResidentHouseQueryRepository extends BaseRepository<ResidentHouseVo> {
	
	/**
	 * 通过身份证号码，获取住户房子关系
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月26日 上午10:56:56
	 */
	public ResidentHouseVo queryByIdNum(PagingParamVo param);
}
