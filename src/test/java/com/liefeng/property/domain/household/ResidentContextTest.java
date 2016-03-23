package com.liefeng.property.domain.household;

import java.util.Date;
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
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.bo.household.ResidentFeedbackBo;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.vo.household.AppMsgSettingVo;
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
		List<ResidentVo> dataList = residentContext.queryRelatedHouse("0000000052a7943f0152a7943fc00000", "C1602271525558af41b8e436795");
		
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
		
		/*//业主
		residentFeedbackVo.setHouseId("402889f952fd79a60152fd79a6820000");
		residentFeedbackVo.setContent("不错");
		residentFeedbackVo.setIsProprietor("1");
		residentFeedbackVo.setResidentId("402889bf5320ceae015320ceaee60000");*/
		
		residentFeedbackVo.setHouseId("402889f952fdedba0152fdedbac80000");
		residentFeedbackVo.setContent("不错");
		residentFeedbackVo.setIsProprietor("0");
		residentFeedbackVo.setResidentId("402889cc532b038301532b0383ef0000");
		
		householdService.createResidentFeedback(residentFeedbackVo);
	}
	
	/**
	 * 创建用户手机端消息设置
	 *  
	 * @author xhw
	 * @date 2016年3月14日 下午2:05:04
	 */
	@Test
	public void createAppMsgSetting(){
		AppMsgSettingVo appMsgSettingVo = new AppMsgSettingVo();
		appMsgSettingVo.setFloatFlag("1");
		appMsgSettingVo.setLockFlag("1");
		appMsgSettingVo.setPopFlag("1");
		appMsgSettingVo.setSound("1");
		appMsgSettingVo.setUserId("8af41b8e532b037f01532b0380320001");
		
		householdService.createAppMsgSetting(appMsgSettingVo);
	}
	
	/**
	 * 更新用户手机端消息设置
	 *  
	 * @author xhw
	 * @date 2016年3月14日 下午2:12:21
	 */
	@Test
	public void updateAppMsgSetting(){
		AppMsgSettingVo appMsgSettingVo = new AppMsgSettingVo();
		appMsgSettingVo.setFloatFlag("0");
		appMsgSettingVo.setLockFlag("0");
		appMsgSettingVo.setPopFlag("0");
		appMsgSettingVo.setSound("0");
		appMsgSettingVo.setUserId("8af41b8e532b037f01532b0380320001");
		appMsgSettingVo.setUpdateTime(new Date());
		householdService.updateAppMsgSetting(appMsgSettingVo);
	}
	
	/**
	 * 根据用户id，获取用户手机端消息设置
	 *  
	 * @author xhw
	 * @date 2016年3月14日 下午2:27:17
	 */
	@Test
	public void getAppMsgSetting(){
		AppMsgSettingVo appMsgSettingVo = householdService.getAppMsgSetting("8af41b8e532b037f01532b0380320001");
		System.out.println(appMsgSettingVo);
	}
	
	/**
	 * 查询用户反馈（分页）
	 *  
	 * @author xhw
	 * @date 2016年3月15日 下午2:35:38
	 */
	@Test
	public void getResidentFeedback(){
		ResidentFeedbackBo feedbackBo = new ResidentFeedbackBo();
		feedbackBo.setProjectId("0000000052a7943f0152a7943fc00000");
		feedbackBo.setBuildingId("");
		feedbackBo.setFloorId("");
		feedbackBo.setHouseNum("");
		feedbackBo.setResidentName("俊");
		feedbackBo.setOemCode(ContextManager.getInstance().getOemCode());
		DataPageValue<ResidentFeedbackVo> feedbackPage = householdService.getResidentFeedbackPage(feedbackBo, 1, 30);
		System.out.println(feedbackPage);
	}
}
