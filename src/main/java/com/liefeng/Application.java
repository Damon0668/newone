package com.liefeng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.fastjson.JSON;
import com.liefeng.core.constant.SystemConstants;
import com.liefeng.core.error.SystemErrorCode;
import com.liefeng.core.exception.SystemException;

@SpringBootApplication
@ImportResource({"classpath:dubbo/dubbo.xml","classpath:timetask.xml"})
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
			
	public static void main(String[] args) {
		if (args.length == 0) {
			logger.info("Boot arguments is empty, use default profile 'dev'.");
			String defaultProfile = "--spring.profiles.active=dev";
			args = new String[] {defaultProfile};
		}
		
		boolean setProfile = false;
		
		// 设置profile到系统属性中
		for (int i=0; i < args.length; i++) {
			String arg = args[i];
			if (arg.contains(SystemConstants.ACTIVE_PROFILE_KEY)) {
				setProfile = true;
				String activeProfile = arg.split("=")[1];
				System.setProperty(SystemConstants.ACTIVE_PROFILE_KEY, activeProfile);
				logger.info("Set active profile '{}' to system property.", activeProfile);
				break;
			}
		}
		
		if (!setProfile) {
			logger.error("Please set '--spring.profiles.active' argument when start, " +
					"the value of active profile is one in ['dev', 'test', 'prod'].");
			throw new SystemException(SystemErrorCode.PROFILE_NOT_SET);
		}
		
		logger.info("Prepare to launch spring boot...");
		logger.info("Launch args are: " + JSON.toJSONString(args));
		
		SpringApplication.run(Application.class, args);
	}
}