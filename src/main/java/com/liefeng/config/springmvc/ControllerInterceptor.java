package com.liefeng.config.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liefeng.common.util.CommonUtil;
import com.liefeng.common.util.EncryptionUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.service.cache.IRedisService;
import com.liefeng.property.constant.SysConstants;

public class ControllerInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		IRedisService redisService = SpringBeanUtil.getBean(IRedisService.class);
		
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		
		String openId = request.getHeader("openId");
		
		String env = CommonUtil.getActiveProfile().toLowerCase();
		
		logger.info("ControllerInterceptor openId = {} , env = {}", openId, env);
		
		//开发环境和测试环境设置。
		if("test".equals(env) || "dev".equals(env)){
			if(ValidateHelper.isEmptyString(openId)){
				String oemCode = (String) redisService.getValue("openId_" + env);
				
				if(ValidateHelper.isNotEmptyString(oemCode)){
					ContextManager.getInstance().setOemCode(oemCode);
				}else{
					redisService.setValue("openId_" + env, SysConstants.DEFAULT_OEM_CODE);
				}
				
				return Boolean.TRUE;
			}
		}
		
		if(ValidateHelper.isEmptyString(openId)){
			return Boolean.FALSE;
		}
		
		if("default".equals(openId)){
			return Boolean.TRUE;
		}
		
		String key = "openId_" + openId;
		
		String oemCode = (String) redisService.getValue(key);
		
		logger.info("ControllerInterceptor openId = {}, oemCode = {}", openId, oemCode);
		
		if(ValidateHelper.isEmptyString(oemCode)){
			//从openId 解密oemCode
			openId = EncryptionUtil.decrypt(openId, EncryptionUtil.OPEN_ID_PASSWORD);
			String[] openIdArray = openId.split("\\|");
			if(openIdArray.length == 2){
				oemCode = openIdArray[1];
			}else{
				return Boolean.FALSE;
			}
		}
		
		ContextManager.getInstance().setOemCode(oemCode);
			
		return Boolean.TRUE;//只有返回true才会继续向下执行，返回false取消当前请求
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	}

}
