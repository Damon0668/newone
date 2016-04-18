package com.liefeng.intf.property.api;

import com.liefeng.property.vo.api.LoginUserVo;

/**
 * 登陆用户服务
 * @author 蔡少东
 * @date 2016年3月18日
 */
public interface ILoginUserService {
	
	/**
	 * 查找登陆用户信息
	 * @param loginId 登陆ID
	 * @param type 用户类型
	 * @return
	 */
	LoginUserVo findLoginUser(String loginId, String type);
}
