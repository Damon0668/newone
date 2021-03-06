package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.error.IErrorCode;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工服务测试
 * @author 蔡少东
 * @date 2016年2月22日
 */
@ActiveProfiles("dev")
@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PropertyStaffServiceTest {
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void create(){
		ContextManager.getInstance().setOemCode("test");
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setAccount("test");
		propertyStaff.setPassword("test");
		propertyStaff.setNumber("test");
		propertyStaff.setWorkStatus("1");
		ReturnValue returnValue = null;
		Assert.assertTrue(IErrorCode.SUCCESS.equals(returnValue.getCode()));
	}
	
	@Test
	public void update(){
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setId("402889015307189f015307189f2b0000");
		propertyStaff.setNumber("test2");
		ReturnValue returnValue = null;
		Assert.assertTrue(IErrorCode.SUCCESS.equals(returnValue.getCode()));
	}
	
	@Test
	public void createDepartment() {
		ContextManager.getInstance().setOemCode("PROPERTY");
		
		PropertyDepartmentVo department = new PropertyDepartmentVo();
		department.setName("客服部");
		department.setTel("114");
		department.setDirectorId("123456");
		
		PropertyDepartmentVo department2 = new PropertyDepartmentVo();
		department2.setName("保安部");
		department2.setTel("110");
		department2.setDirectorId("654321");
		
		propertyStaffService.createDepartment(department);
		propertyStaffService.createDepartment(department2);
	}
	
	@Test
	public void getDepartments() {
		ContextManager.getInstance().setOemCode("PROPERTY");
		List<PropertyDepartmentVo> deparmentList = propertyStaffService.getDepartments();
		System.out.println(deparmentList);
	}
	
	@Test
	public void findPropertyStaff(){
		List<PropertyStaffVo> propertyStaffList =  propertyStaffService.findPropertyStaff("0", "0000000052a7943f0152a7943fc00000");
		System.out.println(propertyStaffList);
	}
	@Test
	public void findStaffContactPrivilege(){
		System.out.println(propertyStaffService.findStaffContactPrivilege("40282081531cf49b01531d4c72a80009"));
	}
	
	@Test
	public void findPropertyStaffByRoleId(){
		System.out.println(propertyStaffService.findPropertyStaffByRoleId(40L));
	}
	
	@Test
	public void getDepartmentsByIds(){
		List<String> ids = new ArrayList<String>();
		ids.add("402881fb530cd2a501530cd2a5580000");
		ids.add("402881fb530cd2a501530cd2a6970001");
		System.out.println(propertyStaffService.getDepartments(ids));
	}
	
	@Test
	public void getDepartments4page(){
		System.out.println(propertyStaffService.listDepartment4Page(1, 10));
	}
	
	@Test
	public void updatePassword(){
		propertyStaffService.updateStaffPassword("40282081531cf49b01531d4c72a80009", "1234567", "1");
	}
	
	@Test
	public void getClientId(){
		System.out.println(propertyStaffService.findStaffMsgClientId("1"));
	}
	
	@Test
	public void getClientIds(){
		List<String> staffIds = new ArrayList<String>();
		staffIds.add("1");
		staffIds.add("40282081531cf49b01531d3f4e1c0006");
		System.out.println(propertyStaffService.findStaffMsgClientIds(staffIds));
	}
	
	@Test
	public void findStaffByMenuCode(){
	
		System.out.println(propertyStaffService.findStaffByMenuCode("zxtg_sh"));
	}
}
