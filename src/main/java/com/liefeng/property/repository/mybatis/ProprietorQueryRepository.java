package com.liefeng.property.repository.mybatis;

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
	 * @param params 参数
	 * @return 业主某个房产信息
	 */
	public ProprietorSingleHouseVo getProprietorSingleHouse(PagingParamVo params);
	
}
