package com.liefeng.property.domain.guard;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.guard.GuardOpenLogBo;
import com.liefeng.property.vo.guard.GuardOpenLogVo;

/**
 * 开门日志领域模型测试类
 * 
 * @author ZhenTingJun
 * @date 2016年4月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@WebAppConfiguration
public class GuardOpenLogContextTest {
	
	@Test
	public void listGuardOpenLog() {
		GuardOpenLogBo params = new GuardOpenLogBo();
		params.setProjectId("0000000052a7943f0152a7943fc00000");
		params.setOpenType("1");
		params.setStartDate(new Date());
		params.setEndDate(new Date());
		params.setOemCode("property");
		
		GuardOpenLogContext guardOpenLogContext = GuardOpenLogContext.build();
		DataPageValue<GuardOpenLogVo> dataPage = guardOpenLogContext.listGuardOpenLog(params, 1, 10);
		System.out.println("密码开门 ==> dataPage = " + dataPage);
		
		params.setOpenType("2");
		guardOpenLogContext = GuardOpenLogContext.build();
		dataPage = guardOpenLogContext.listGuardOpenLog(params, 1, 10);
		System.out.println("刷卡开门 ==> dataPage = " + dataPage);
		
		
		params.setOpenType("3");
		guardOpenLogContext = GuardOpenLogContext.build();
		dataPage = guardOpenLogContext.listGuardOpenLog(params, 1, 10);
		System.out.println("二维码开门 ==> dataPage = " + dataPage);
	}
	
}
