package com.liefeng.property.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;

/**
 * 
 * <pre>
 * Title:
 * Description:项目服务测试类
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing        
 * @version 1.0      
 * @created 2016年2月18日上午9:09:06
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;
	
	@Test
	public void listProjects(){
		ContextManager.getInstance().setOemCode("liefeng");
		System.out.println(projectService.listProjects());
	}
}
