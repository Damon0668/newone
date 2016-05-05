package com.liefeng.property.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.guard.GuardPRUserVo;
import com.liefeng.property.vo.guard.GuardStaffVo;

/**
 * 出入管理
 * 用户管理
 * 查询接口
 */
public interface GuardUserQueryRepository extends BaseRepository {

	/**
	 * 门禁模块
	 * 查询住户列表
	 * @return
	 */
	public List<GuardPRUserVo> queryGuardPRUser(PagingParamVo param);

	/**
	 * 查询住户信息
	 * @param userId 业主|租户ID
	 * @param userType 用户类型
	 * @return
	 */
	public GuardPRUserVo findPRUser(@Param(value="userId") String userId, 
			@Param(value="userType") String userType, @Param(value="oemCode") String oemCode);
	
	/**
	 * 查询员工信息
	 * @param staffId 员工ID
	 * @return
	 */
	public GuardStaffVo findStaff(@Param(value="staffId") String staffId);
	
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
