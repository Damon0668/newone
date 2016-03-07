package com.liefeng.property.domain.guard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.base.constant.DeviceConstants;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.guard.GuardCardVo;

/**
 * 门禁磁卡设备
 * @author 蔡少东
 * @date 2016年3月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GuardCardContextTest {
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void create(){
		GuardCardVo guardCardVo = new GuardCardVo();
		guardCardVo.setSn("123");
		guardCardVo.setType("1");
		GuardCardContext.build(guardCardVo).create();
	}
}
