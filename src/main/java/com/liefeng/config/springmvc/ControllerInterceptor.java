package com.liefeng.config.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;

public class ControllerInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String auth = request.getHeader("Authorization");
		
		String remoteAddr = request.getRemoteAddr();
		
		logger.info("ControllerInterceptor auth = {}, remoteAddr = {}", auth, remoteAddr);
		
		if(ValidateHelper.isEmptyString(auth)){
			auth = "hzwy_property";
			//return false;
		}
		
		String oemCode = auth;
		
		ContextManager.getInstance().setOemCode(oemCode);

		return true;//只有返回true才会继续向下执行，返回false取消当前请求
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
		System.out.println("afterCompletion");
	}

}
