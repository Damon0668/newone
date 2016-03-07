package com.liefeng.property.domain.household;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户领域模型测试类
 * 
 * @author ZhenTingJun
 * @date 2016年3月3日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ResidentContextTest {
	
	@Test
	public void getResidents() {
		
		ResidentContext residentContext = ResidentContext.build();
		List<ResidentVo> dataList = residentContext.getResidentsInProprietorHouse("0000000052a7943f0152a7943fc00000", "C1602271525558af41b8e436795");
		
		System.out.println(dataList);
	}
	
}
