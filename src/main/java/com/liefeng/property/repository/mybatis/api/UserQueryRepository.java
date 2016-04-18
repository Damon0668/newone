package com.liefeng.property.repository.mybatis.api;

import org.apache.ibatis.annotations.Param;

import com.liefeng.property.vo.api.LoginUserVo;

/**
 * 用户服务
 * @author 蔡少东
 * @date 2016年3月18日
 */
public interface UserQueryRepository {

	/**
	 * 查找登陆用户信息
	 * @param loingId 登陆ID
	 * @param type 用户类型
	 * @return
	 */
	public LoginUserVo findLoginUser(@Param("loginId") String loingId, @Param("type") String type);
}
