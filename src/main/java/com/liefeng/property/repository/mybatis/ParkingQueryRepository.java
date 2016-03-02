package com.liefeng.property.repository.mybatis;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;

/**
 * 车位信息查询
 * @author wuzhijing
 */
public interface ParkingQueryRepository extends BaseRepository<ParkingSingleRentalVo>{

	public ParkingSingleRentalVo queryById(String id);
}
