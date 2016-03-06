package com.liefeng.property.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.base.constant.UserConstants;
import com.liefeng.base.vo.CustomerVo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.property.ISysService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.domain.sys.SysMenuContext;
import com.liefeng.property.vo.sys.SysDictVo;

/**
 * 系统类测试
 * @author Huangama
 * @date 2016-2-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SysServiceTest {

	@Autowired
	private ISysService sysService;
	
	@Autowired
	private ICheckService checkService;
	
	@Test
	public void getDictByGroupCode() {
		List<SysDictVo> dictList = 
				sysService.getDictByGroupCode(SysConstants.DictGroup.FEE_TYPE);
		System.out.println(dictList);
	}
	
	@Test
	public void exceptionTest() {
		ContextManager.getInstance().setOemCode("property");
		
		UserVo userVo = new UserVo();
		CustomerVo customerVo = new CustomerVo();
		customerVo.setNational("汉族");
		customerVo.setNativePlace("广东");
		customerVo.setRealName("甄庭俊");
		customerVo.setSex("1");
		customerVo.setStatus("1");
		
		userVo.setEmail("zhentingjun@liefengtech.com");
		userVo.setHouseholdType("1");
		userVo.setMobile("13032045915");
		userVo.setName("13032045915");
		userVo.setPassword("111111");
		userVo.setQq("306675799");
		userVo.setRegisterType(UserConstants.RegisterType.PC);
		
		userVo.setCustomer(customerVo);
		
		try {
			checkService.createUserCheck(userVo);
		} catch (LiefengException e) {
			System.out.println(e.getCode());
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void test() {
		SysMenuContext context = SysMenuContext.build();
		context.findMenusByUserId("40282081531cf49b01531d3f4e1c0006");
		System.out.println("***********");
	}
}
