package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.guard.GuardPRUserVo;
import com.liefeng.property.vo.guard.GuardStaffVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 出入管理
 * 用户管理
 * 查询接口
 */
public interface GuardUserQueryRepository extends BaseRepository<ResidentVo> {
	
	/**
	 * 根据ID和房屋ID查询住户
	 * @param param 查询过滤参数
	 * @return 某房屋某个住户信息
	 */
	public ResidentVo queryByIdAndHouseId(PagingParamVo param);
	
	/**
	 * 根据客户全局ID和项目ID查询客户
	 * @param param 查询过滤参数
	 * @return 住户信息
	 */
	public ResidentVo queryByCustGlobalIdAndProjectId(PagingParamVo param);
	
	/**
	 * 获取账号关联房子中的住户或业主
	 * @param param 查询过滤参数
	 * @return 住户列表
	 */
	public List<ResidentVo> queryRelatedHouses(PagingParamVo param);

	/**
	 * 门禁模块
	 * 查询住户列表
	 * @return
	 */
	public List<GuardPRUserVo> queryGuardPRUser(PagingParamVo param);

	/**
	 * 查询集合总数
	 */
	public Long queryGuardPRUserByCount(PagingParamVo param);
	
	/**
	 * 门禁模块
	 * 查询员工列表
	 * @return
	 */
	public List<GuardStaffVo> queryStaff(PagingParamVo param);

	/**
	 * 查询员工总数
	 */
	public Long queryStaffByCount(PagingParamVo param);
	
}
