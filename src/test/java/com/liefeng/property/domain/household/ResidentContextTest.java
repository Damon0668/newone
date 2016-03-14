package com.liefeng.property.domain.household;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.household.ResidentFeedbackVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 住户领域模型测试类
 * 
 * @author ZhenTingJun
 * @author xhw
 * @date 2016年3月3日
 */
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ResidentContextTest {
	@Autowired
	private IHouseholdService householdService;
	
	@Before
	public void before(){
		ContextManager.getInstance().setOemCode(SysConstants.DEFAULT_OEM_CODE);
	}
	
	@Test
	public void getResidents() {
		
		ResidentContext residentContext = ResidentContext.build();
		List<ResidentVo> dataList = residentContext.getResidentsInProprietorHouse("0000000052a7943f0152a7943fc00000", "C1602271525558af41b8e436795");
		
		System.out.println(dataList);
	}
	
	/**
	 * 创建用户反馈
	 * @author xhw
	 * @date 2016年3月14日 上午10:42:21
	 */
	@Test
	public void crateResidentFeedback(){
		ResidentFeedbackVo residentFeedbackVo = new ResidentFeedbackVo();
		residentFeedbackVo.setHouseId("402889f952fd79a60152fd79a6820000");
		residentFeedbackVo.setContent("不错");
		residentFeedbackVo.setIsProprietor("1");
		residentFeedbackVo.setResidentId("402889bf5320ceae015320ceaee60000");
		
		householdService.createResidentFeedback(residentFeedbackVo);
	}
}
