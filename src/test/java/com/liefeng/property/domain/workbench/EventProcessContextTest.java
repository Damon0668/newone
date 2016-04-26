package com.liefeng.property.domain.workbench;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.workbench.EventProcessVo;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EventProcessContextTest {
	
	@Test
	public void getTask(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
		EventProcessContext context = EventProcessContext.build();
		EventProcessVo eventProcessVo = context.getEventProcess("1", "2");
		System.out.println(eventProcessVo);
	}
	
}
