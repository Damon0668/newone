package com.liefeng.property.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.domain.household.ResidentCarContext;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentCarVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * household包相关表接口测试类
 * 
 * @author ZhenTingJun
 * @date 2015-12-30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
public class HouseholdServiceTest {
	
	@Autowired
	private IHouseholdService householdService;
	
	@Test
	public void listProprietor4Page() {
		ProprietorBo proprietorBo = new ProprietorBo();
		proprietorBo.setProjectId("12345678");
		
		DataPageValue<ProprietorSingleHouseVo> proprietorPage = householdService.listProprietorSingleHouse4Page(proprietorBo, 10, 1);
		
		System.out.println(proprietorPage);
	}
	
	@Test
	public void getProprietorSingleHouse() {
		
		ProprietorSingleHouseVo ProprietorSingleHouse = householdService.getProprietorSingleHouse("123456789");
		
		System.out.println(ProprietorSingleHouse);
	}
	
	@Test
	public void listResident4Page() {
		ResidentBo residentBo = new ResidentBo();
		residentBo.setProprietorId("402889be53219e9d0153219e9d230000");
		residentBo.setHouseId("402889f952fdedba0152fdedbac80000");
		
		DataPageValue<ResidentVo> residentPage = householdService.listResident4Page(residentBo, 2, 1);
		
		System.out.println(residentPage);
	}
	
	@Test
	public void getResident() {
		
		ResidentVo resident = householdService.getResident("1","1");
		System.out.println(resident);
	}
	
	@Test
	public void findResidentCarByPakingId(){
		List<ResidentCarVo> residentCarVos = householdService.findResidentCarByPakingId("4028895e533a668401533a6ec0660140");
		System.out.println(residentCarVos);
	}
}
