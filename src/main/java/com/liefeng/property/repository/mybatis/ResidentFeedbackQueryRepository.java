package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;

/**
 * 用户反馈查询
 *  
 * @author xhw
 * @date 2016年3月15日 下午1:51:11
 */
public interface ResidentFeedbackQueryRepository extends BaseRepository<ResidentFeedbackVo> {
	
	/**
	 * 查询用户反馈
	 * @param param {projectId：项目id；buildingId：楼栋id； floorId：楼层id；houseNum：房号；residentName：姓名}
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午1:52:11
	 */
	public List<ResidentFeedbackVo> queryResidentFeedback(PagingParamVo param);
	
	/**
	 * 查询用户反馈总数
	 * @param param {projectId：项目id；buildingId：楼栋id； floorId：楼层id；houseNum：房号；residentName：姓名}
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午1:52:35
	 */
	public Long queryResidentFeedbackCount(PagingParamVo param);
	
}
