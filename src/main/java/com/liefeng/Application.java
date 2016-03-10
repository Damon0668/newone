package com.liefeng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.fastjson.JSON;

@SpringBootApplication
//"classpath:timetask.xml"
@ImportResource("classpath:dubbo/dubbo.xml")
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
			
	public static void main(String[] args) {
		if (args.length == 0) {
			logger.info("Boot arguments is empty, use default profile 'dev'.");
			String defaultProfile = "--spring.profiles.active=dev";
			args = new String[] {defaultProfile};
		}
		
		logger.info("Prepare to launch spring boot...");
		logger.info("Launch args are: " + JSON.toJSONString(args));
		
		SpringApplication.run(Application.class, args);
	}
}