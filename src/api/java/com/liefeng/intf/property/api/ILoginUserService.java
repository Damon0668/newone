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
	 * @param custGlobalId 用户全局ID
	 * @return
	 */
	LoginUserVo findLoginUser(String custGlobalId);
}
