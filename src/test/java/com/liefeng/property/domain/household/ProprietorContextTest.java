package com.liefeng.property.domain.household;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.vo.household.ProprietorVo;

/**
 * 业主领域测试类
 * 
 * @author ZhenTingJun
 * @date 2015-12-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProprietorContextTest {
	
	@Test
	public void updateProprietor() throws Exception {
		ProprietorVo proprietor = new ProprietorVo();
		proprietor.setId("123456789");
		proprietor.setEmail("876396352@qq.com");
		proprietor.setName("MR.Zhen");
		proprietor.setOemCode("LIE_FENG");
		proprietor.setRemark("更新后看看有没有备注");
		
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		proprietorContext.update();
	}
}
