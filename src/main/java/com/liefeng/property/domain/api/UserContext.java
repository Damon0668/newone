package com.liefeng.property.domain.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.mybatis.api.UserQueryRepository;
import com.liefeng.property.vo.api.LoginUserVo;

/**
 * 用户服务
 * @author 蔡少东
 * @date 2016年3月18日
 */
@Service
@Scope("prototype")
public class UserContext {
	
	private static Logger logger = LoggerFactory.getLogger(UserContext.class);
	
	@Autowired
	private UserQueryRepository userQueryRepository;
	
	private String custGlobalId;
	
	protected void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	private static UserContext getInstance() {
		return SpringBeanUtil.getBean(UserContext.class);
	}
	
	public static UserContext loadByCustGlobalId(String custGlobalId) {
		UserContext userContext = getInstance();
		userContext.setCustGlobalId(custGlobalId);
		return userContext;
	}
	
	/**
	 * 查找登陆用户信息
	 * @return
	 */
	public LoginUserVo findLoginUser(String oemCode){
		return null;
	}
}
