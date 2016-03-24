package com.liefeng.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.liefeng.core.aop.ControllerAspect;

/**
 * service 拦截器
 * 计算方法调用时间
 * @author 蔡少东
 * @date 2016年3月24日
 */
@Component
@Aspect
public class ServiceAspect {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
	
	@Around("execution(public * com.liefeng..*.service..*.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		
		Method targetMethod = methodSignature.getMethod();
		
		String targetMethodFullName = pjp.getTarget().getClass().getName() + "."
				+ targetMethod.getName();
		
		Long startTime = System.currentTimeMillis();
		
		Object result = pjp.proceed();
		
		Long endTime = System.currentTimeMillis();
		
		Long useTime = (endTime - startTime);
		
		logger.info("{} useTime = {} ms", targetMethodFullName, useTime);
		
		return result;
	}
}
