package com.liefeng.property.test.integration.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.property.vo.sys.SysRoleVo;

/**
 * 系统权限测试
 * @author 蔡少东
 * @date 2016年2月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SecurityTest {

	@Autowired
	private ISysSecurityService sysSecurityService;
	
	@Test
	public void listRolesTest(){
		DataPageValue<SysRoleVo> dataPageValue = sysSecurityService.listRoles4page(null,null,1,10);
		System.out.println(dataPageValue.toString());
	}
}
