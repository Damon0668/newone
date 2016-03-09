package com.liefeng.property.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.ISysSecurityService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.domain.sys.SysMenuContext;
import com.liefeng.property.domain.sys.SysRoleContext;
import com.liefeng.property.repository.mybatis.SysMenuQueryRepository;
import com.liefeng.property.vo.sys.SysMenuVo;
import com.liefeng.property.vo.sys.SysRoleVo;

/**
 * 系统权限测试
 * @author 蔡少东
 * @date 2016年2月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SecurityServiceTest {

	@Autowired
	private ISysSecurityService sysSecurityService;

	@Autowired
	private SysMenuQueryRepository sysMenuQueryRepository;
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void listRolesTest(){
		DataPageValue<SysRoleVo> dataPageValue = sysSecurityService.listRoles4page(null,null,1,10);
		System.out.println(dataPageValue.toString());
	}
	
	@Test
	public void addRole(){
		SysRoleVo sysRole = new SysRoleVo();
		sysRole.setName("中文");
		sysRole.setType("001");
		sysRole.setOemCode("test");
		sysRole.setDescription("test");
		SysRoleContext.build(sysRole).create();
	}
	
	@Test
	public void addMenu(){
		SysMenuVo sysMenu = new SysMenuVo();
		sysMenu.setOemCode("test");
		sysMenu.setName("test");
		sysMenu.setUrl("123");
		SysMenuContext sysMenuContext = SysMenuContext.build(sysMenu);
		sysMenuContext.create();		
	}
	
	@Test
	public void delMenus(){
		String[] ids = new String[]{"10"};
		SysMenuContext sysMenuContext = SysMenuContext.build();
		sysMenuContext.delMenus(ids);
	}
	
	@Test
	public void queryButtonsCode(){
		List<String> list = sysMenuQueryRepository.queryButtonsCodeByUserId("62");
		System.out.println(list);
	}
	
	@Test
	public void listAllRoles(){
		System.out.println(sysSecurityService.listAllRoles());
	}
	
	@Test
	public void findRoleByRoleId(){
		System.out.println(sysSecurityService.findRoleByRoleId(40L));
	}

}
