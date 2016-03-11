package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.guard.GuardResidentVo;
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
	
	/**
	 * 查询业主所有房产中的住户
	 * @param param 查询过滤参数
	 * @return 住户列表
	 */
	public List<ResidentVo> queryResidents(PagingParamVo param);

	/**
	 * 门禁模块
	 * 查询住户列表
	 * @return
	 */
	public List<GuardResidentVo> queryGuardResidents(PagingParamVo param);

	/**
	 * 查询集合总数
	 */
	public Long queryGuardResidentsByCount(PagingParamVo param);
	
	/**
	 * 根据房间id，获取住户
	 * @param param {houseId:房间id}
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午9:05:58
	 */
	public List<ResidentVo> queryByHouseId(PagingParamVo param);
}
