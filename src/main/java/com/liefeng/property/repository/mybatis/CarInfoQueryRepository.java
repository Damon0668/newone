package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.household.CarInfoVo;

/**
 * 车辆信息查询仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
public interface CarInfoQueryRepository extends BaseRepository<CarInfoVo> {
	
	/**
	 * 查询员工车辆信息
	 * @param staffId 员工ID
	 * @return
	 */
	public List<CarInfoVo> findByStaffId(String staffId);
	
	/**
	 * 查询即是业主又是住户的车辆信息
	 * @param prUserId
	 * @return
	 */
	public List<CarInfoVo> findOnPRUsrByCstGlbId(String custGlobalId);
}
