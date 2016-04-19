package com.liefeng.property.domain.guard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.constant.SysConstants;

/**
 * 门禁磁卡设备
 * @author 蔡少东
 * @date 2016年3月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@WebAppConfiguration
public class GuardCardContextTest {
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void listGuardCardLog() {
		GuardCardLogContext guardCardLogContext = GuardCardLogContext.build();
		System.out.println(guardCardLogContext.listGuardCardLog("1", 1, 10));
	}
}
