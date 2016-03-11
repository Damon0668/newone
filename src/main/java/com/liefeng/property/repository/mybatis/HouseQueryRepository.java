package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;

/**
 * t_house 房产资料查询 基本的查询父接口已经有了
 * Mybatis 直接返回vo
 * HouseVo字段不能满足需求，所以使用ProprietorSingleHouseVo
 * @author levy
 * @date 2016年1月4日
 */
public interface HouseQueryRepository extends BaseRepository<ProprietorSingleHouseVo>{
	
}
