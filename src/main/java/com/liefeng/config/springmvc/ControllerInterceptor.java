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
		
		String openId = request.getHeader("openId");
		
		String env = CommonUtil.getActiveProfile().toLowerCase();
		
		logger.info("ControllerInterceptor openId = {} , env = {}", openId, env);
		
		//开发环境和测试环境设置。
		if("test".equals(env) || "dev".equals(env)){
			
			//网页端进入
			if(ValidateHelper.isEmptyString(openId)){
				
				ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
				
				String oemCode = (String) redisService.getValue("openId_" + env);
				
				if(ValidateHelper.isNotEmptyString(oemCode)){
					ContextManager.getInstance().setOemCode(oemCode);
				}else{
					redisService.setValue("openId_" + env, SysConstants.DEFAULT_OEM_CODE);
				}
				
				return Boolean.TRUE;
			}
		}
		
		if(ValidateHelper.isEmptyString(openId) || "null".equals(openId)){
			return Boolean.FALSE;
		}
		
		if("default".equals(openId)){
			//此线程可能被复用，清空原OEM
			ContextManager.getInstance().setOemCode("");
			return Boolean.TRUE;
		}
		
		//必须在default判断后面执行，防止default_oem_code 污染业务代码
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		
		String key = "openId_" + openId;
		
		String oemCode = (String) redisService.getValue(key);
		
		logger.info("ControllerInterceptor openId = {}, oemCode = {}", openId, oemCode);
		
		if(ValidateHelper.isEmptyString(oemCode)){
			//从openId 解密oemCode
			openId = EncryptionUtil.decrypt(openId, EncryptionUtil.OPEN_ID_PASSWORD);
			
			logger.info("ControllerInterceptor not in cache openId = {}", openId);
			
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
		//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	}
	
	/**
	 * spring mvc 处理请求，会从spring mvc 的线程池中查找一个线程来处理请求，因此避免下个请求复用原来的线程，
	 * 在渲染视图后（controller执行完成后），需要执行afterCompletion方法来清空线程的oemCode的数据
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
		ContextManager.getInstance().setOemCode("");
	}

}
