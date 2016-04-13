package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.base.constant.DeviceConstants;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IGuardService;
import com.liefeng.property.bo.guard.GuardDeviceBo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.guard.GuardDeviceVo;

/**
 * 门禁设备测试
 * @author 蔡少东
 * @date 2016年3月1日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GuardDeviceServiceTest {

	@Autowired
	private IGuardService guardSerivce;
	
	@Before
	public void setOemCode(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void createTest(){
		GuardDeviceVo guardDeviceVo = new GuardDeviceVo();
		guardDeviceVo.setType(DeviceConstants.Type.GUARD);
		guardDeviceVo.setProjectId("0000000052a7943f0152a7943fc00000");
		guardDeviceVo.setBuildingId("1123456789");
		guardDeviceVo.setInstallPosition("大门口");
		guardDeviceVo.setStatus("1");
		guardDeviceVo.setRunStatus("1");
		guardDeviceVo.setManufacturer("测试");
		guardDeviceVo.setProducerTel("155");
		guardDeviceVo.setBuyDate(new Date());
		guardDeviceVo.setWarrantyDate(new Date());
		guardSerivce.createGuardDevice(guardDeviceVo);
	}
	
	@Test
	public void updateTest(){
		GuardDeviceVo guardDeviceVo = new GuardDeviceVo();
		guardDeviceVo.setDeviceGlobalId("D1603011521178af41b8e952784");
		guardDeviceVo.setType(DeviceConstants.Type.GUARD);
		guardDeviceVo.setId("4028208153310d520153310d52590000");
		guardDeviceVo.setProjectId("2");
		guardDeviceVo.setBuildingId("11234567890");
		guardDeviceVo.setManufacturer("测试2");
		guardSerivce.updateGuradDevice(guardDeviceVo);
	}
	
	@Test
	public void deleteTest(){
		List<String> guardDeviceIds = new ArrayList<String>();
		guardDeviceIds.add("4028208153310d520153310d52590000");
		guardSerivce.deleteGuradDevice(guardDeviceIds);
	}
	
	@Test
	public void queryTest(){
		System.out.println(guardSerivce.listGuardDevice(new GuardDeviceBo(), 1, 10));
	}
}
