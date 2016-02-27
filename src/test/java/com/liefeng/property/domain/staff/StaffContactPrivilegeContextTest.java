package com.liefeng.property.domain.staff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.vo.staff.StaffContactPrivilegeVo;

/**
 * 员工通讯录授权
 * @author 蔡少东
 * @date 2016年2月27日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StaffContactPrivilegeContextTest {

	@Test
	public void grantTest(){
		StaffContactPrivilegeVo staffContactPrivilegeVo = new StaffContactPrivilegeVo();
		staffContactPrivilegeVo.setStaffId("1");
		staffContactPrivilegeVo.setDepartmentId("1");
		StaffContactPrivilegeContext.build(staffContactPrivilegeVo).create();
	}
	
	@Test
	public void findTest(){
		System.out.println(StaffContactPrivilegeContext.loadByStaffId("1").findContactPrivilegeToDeptIds());
	}
	
}
