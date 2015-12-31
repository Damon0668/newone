package com.liefeng.property.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.api.property.IHouseholdService;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * household包相关表接口测试类
 * 
 * @author ZhenTingJun
 * @date 2015-12-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class HouseholdServiceTest {
	
	@Autowired
	private IHouseholdService householdService;
	
	@Test
	public void listProprietor4Page() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("projectId", "123456789");
		
		DataPageValue<ProprietorSingleHouseVo> proprietorPage = householdService.listProprietor4Page(params, 10, 1);
		
		System.out.println(proprietorPage);
	}
	
	@Test
	public void getProprietorSingleHouse() {
		
		ProprietorSingleHouseVo ProprietorSingleHouse = householdService.getProprietorSingleHouse("123456789");
		
		System.out.println(ProprietorSingleHouse);
	}
	
	@Test
	public void listResident4Page() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("proprietorId", "123456789");
		params.put("houseId", "123456789");
		
		DataPageValue<ResidentVo> residentPage = householdService.listResident4Page(params, 10, 1);
		
		System.out.println(residentPage);
	}
	
	@Test
	public void getResident() {
		
		ResidentVo resident = householdService.getResident("1");
		System.out.println(resident);
	}

}
