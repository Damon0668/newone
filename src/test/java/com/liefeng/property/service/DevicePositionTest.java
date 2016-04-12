package com.liefeng.property.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IGuardService;
import com.liefeng.property.bo.guard.DevicePositionBo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.guard.DevicePositionVo;

@ActiveProfiles("dev")
@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class DevicePositionTest {
	
	@Autowired
	private IGuardService guardService;
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void create(){
		DevicePositionVo devicePositionVo = new DevicePositionVo();
		devicePositionVo.setProjectId("1");
		devicePositionVo.setName("测试");
		guardService.createDevicePosition(devicePositionVo);
	}
	
	
	@Test
	public void update(){
		DevicePositionVo devicePositionVo = new DevicePositionVo();
		devicePositionVo.setId("402889945404300201540430022a0000");
		devicePositionVo.setName("测试2");
		guardService.updateDevicePosition(devicePositionVo);
	}
	
	@Test
	public void delete(){
		guardService.deleteDevicePosition("402889945404300201540430022a0000");
	}
	

	@Test
	public void findDevicePosition(){
		//System.out.println(guardService.findDevicePosition("1"));
		
		
		DevicePositionBo devicePositionBo = new DevicePositionBo();
		devicePositionBo.setProjectId("0000000052a7943f0152a7943fc00000");
		System.out.println(guardService.findDevicePosition(devicePositionBo,1,2));
	}

	
}
