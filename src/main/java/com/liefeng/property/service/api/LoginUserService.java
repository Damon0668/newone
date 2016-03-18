package com.liefeng.property.service.api;

import org.springframework.stereotype.Service;

import com.liefeng.intf.property.api.ILoginUserService;
import com.liefeng.property.domain.api.UserContext;
import com.liefeng.property.vo.api.LoginUserVo;

/**
 * 
 * @author 蔡少东
 * @date 2016年3月18日
 */
@Service
public class LoginUserService implements ILoginUserService{

	@Override
	public LoginUserVo findLoginUser(String custGlobalId) {
		return UserContext.loadByCustGlobalId(custGlobalId).findLoginUser();
	}

}