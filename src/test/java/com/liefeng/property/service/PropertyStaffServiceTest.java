package com.liefeng.property.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工服务测试
 * @author 蔡少东
 * @date 2016年2月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PropertyStaffServiceTest {
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Test
	public void create(){
		ContextManager.getInstance().setOemCode("test");
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setAccount("test");
		propertyStaff.setPassword("test");
		propertyStaff.setNumber("test");
		propertyStaff.setWorkStatus("1");
		propertyStaff.setProjectId("1");
		ReturnValue returnValue = propertyStaffService.createStaff(propertyStaff);
		Assert.assertTrue(IErrorCode.SUCCESS.equals(returnValue.getCode()));
	}
	
	@Test
	public void update(){
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setId("402889015307189f015307189f2b0000");
		propertyStaff.setNumber("test2");
		ReturnValue returnValue = propertyStaffService.updateStaff(propertyStaff);
		Assert.assertTrue(IErrorCode.SUCCESS.equals(returnValue.getCode()));
	}
}
