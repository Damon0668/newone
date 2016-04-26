package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.guard.GuardResidentVo;
import com.liefeng.property.vo.household.ResidentHistoryVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户历史信息查询接口
 * 
 * @author wuzhijing
 */
public interface ResidentHistoryQueryRepository extends BaseRepository<ResidentHistoryVo> {
	
	
}
