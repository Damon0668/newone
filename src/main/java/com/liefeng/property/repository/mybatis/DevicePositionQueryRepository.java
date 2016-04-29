package com.liefeng.property.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.guard.DevicePositionVo;

/**
 * 设备位置仓储
 * @author 蔡少东
 * @date 2016年4月12日
 */
public interface DevicePositionQueryRepository extends BaseRepository<DevicePositionVo>{
	
	public List<DevicePositionVo> findByCardId(@Param(value="cardId") String cardId);
}
