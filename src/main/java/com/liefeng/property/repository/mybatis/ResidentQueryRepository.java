package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户信息查询接口
 * 
 * @author ZhenTingJun
 * @date 2015-12-30
 */
public interface ResidentQueryRepository extends BaseRepository<ResidentVo> {
	
	/**
	 * 根据客户全局ID和项目ID查询客户
	 * @param param 查询过滤参数
	 * @return 住户信息
	 */
	public ResidentVo queryByCustGlobalIdAndProjectId(PagingParamVo param);
	
}
