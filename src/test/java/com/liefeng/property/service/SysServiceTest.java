package com.liefeng.property.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.intf.property.ISysService;
import com.liefeng.property.constant.SysConstants;
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
	
	@Test
	public void getDictByGroupCode() {
		List<SysDictVo> dictList = 
				sysService.getDictByGroupCode(SysConstants.DictGroup.FEE_TYPE);
		System.out.println(dictList);
	}
}
