package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.parking.ParkingSingleRentalVo;

/**
 * 车位信息查询
 * @author wuzhijing
 */
public interface ParkingQueryRepository extends BaseRepository<ParkingSingleRentalVo>{

	public ParkingSingleRentalVo queryById(String id);
	
	public List<ParkingSingleRentalVo> queryByProjectId(String projectId);
}
