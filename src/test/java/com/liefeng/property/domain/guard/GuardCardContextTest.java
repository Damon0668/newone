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
import com.liefeng.property.bo.guard.GuardCardBo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.guard.GuardCardVo;

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
	public void create(){
		GuardCardVo guardCardVo = new GuardCardVo();
		guardCardVo.setSn("123");
		guardCardVo.setType("1");
		GuardCardContext.build(guardCardVo).create();
	}
	
	@Test
	public void listGuardCard() {
		GuardCardBo guardCardBo = new GuardCardBo();
		guardCardBo.setProjectId("0000000052a7943f0152a7943fc00000");
		guardCardBo.setOemCode("property");
		
		GuardCardContext guardCardContext = GuardCardContext.build();
		System.out.println(guardCardContext.listGuardCard(guardCardBo, 1, 10));
		
		
	}
}
