package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.project.HouseVo;

/**
 * t_house 房产资料查询
 * Mybatis 直接返回vo
 * 基本的查询父接口已经有了
 * @author levy
 * @date 2016年1月4日
 */
public interface HouseQueryRepository extends BaseRepository<HouseVo>{
	
}
