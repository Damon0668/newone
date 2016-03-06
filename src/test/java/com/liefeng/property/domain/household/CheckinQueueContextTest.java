package com.liefeng.property.domain.household;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.household.CheckinQueueBo;
import com.liefeng.property.vo.household.CheckinQueueVo;

/**
 * 入住排队领域模型测试类
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CheckinQueueContextTest {
	
	@Test
	public void getCheckinQueues() {
		CheckinQueueBo params = new CheckinQueueBo();
		params.setProjectId("0000000052a7943f0152a7943fc00000");
		params.setOemCode("property");
		
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		DataPageValue<CheckinQueueVo> dataPage = checkinQueueContext.getCheckinQueues(params, 10, 1);
		
		System.out.println(dataPage);
	}
	
}
