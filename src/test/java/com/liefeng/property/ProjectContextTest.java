package com.liefeng.property;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.vo.household.ProprietorVo;

/**
 * 项目领域测试类
 * 
 * @author Huangama
 * @date 2015-12-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProjectContextTest {
	
	@Test
	public void updateProprietor() {
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
