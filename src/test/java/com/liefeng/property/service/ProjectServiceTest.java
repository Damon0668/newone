package com.liefeng.property.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.vo.household.HouseGraphVo;

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
	private IProjectService projectService;
	
	@Test
	public void listProjects(){
		ContextManager.getInstance().setOemCode("liefeng");
		System.out.println(projectService.listProjects());
	}
	
	@Test
	public void getHouseGraphs() {
		HouseBo param = new HouseBo();
		param.setOemCode("property");
		param.setProjectId("0000000052a7943f0152a7943fc00000");
		
		List<HouseGraphVo> dataList = projectService.getHouseGraphs(param);
		
		System.out.println(dataList);
	}
}
