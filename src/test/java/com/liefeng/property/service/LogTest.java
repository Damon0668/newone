package com.liefeng.property.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	private static Logger logger = LoggerFactory.getLogger(LogTest.class);
	@Test
	public void logTest(){
		logger.debug("is debug");
		logger.info("is info {} {} {} {}","caishaodong","dong",1,"中文");
		logger.warn("is warn");
		logger.error("is error {} {} {} {}","caishaodong","dong",1,"中文");
	}
}
