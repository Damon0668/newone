package com.liefeng.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring bean
 * @author Huangama
 * @date 2015-12-2
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

	// Spring应用上下文
    private static ApplicationContext applicationContext;
    
    /**
	 * 根据名称获取bean
	 * @param name bean名称
	 * @return bean实例
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	/**
	 * 根据类型获取bean
	 * @param clazz bean的Class类型
	 * @return bean实例
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringBeanUtil.applicationContext = applicationContext;
	}
	
}
