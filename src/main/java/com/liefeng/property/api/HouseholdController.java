package com.liefeng.property.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.AppMsgSettingVo;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 入住办理公共服务类（app） 
 * @author xhw
 * @date 2016年3月8日 下午1:45:25
 */
@RestController
@RequestMapping(value = "/api/household")
public class HouseholdController {

	@Autowired
	private IHouseholdService householdService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ITccMsgService tccMsgService;
	
	/**
	 * 通过扫二维码，获取排队号
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param houseId 房间id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午5:12:08
	 */
	@RequestMapping("createCheckinQueue")
	@ResponseBody
	public DataValue<CheckinQueueVo> createCheckinQueue(String projectId, String houseId, String userId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		CheckinQueueVo queueVo = householdService.createCheckinQueue(projectId, houseId, userId);
		
		return DataValue.success(queueVo);
	}
	
	/**
	 * 获取排队情况中的头部信息
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午11:00:23
	 */
	@RequestMapping("getCheckinQueue")
	@ResponseBody
	public DataValue<CheckinQueueVo> getCheckinQueue(String projectId, String houseId, String userId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		CheckinQueueVo queueVo = householdService.getCheckinQueue(projectId, houseId, userId);
		
		return DataValue.success(queueVo);
	}
	
	/**
	 * 获取排队情况详情
	 * @param projectId 项目id
	 * @param page 第几页
	 * @param size 每页条数
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午11:19:50
	 */
	@RequestMapping("getCheckinQueueList")
	@ResponseBody
	public DataPageValue<CheckinQueueVo> getCheckinQueueList(String projectId, Integer page, Integer size) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		DataPageValue<CheckinQueueVo> queDataPageValue = householdService.getCheckinQueueOfNotStatus(projectId, HouseholdConstants.CheckinQueueStatus.UNTREATED, TimeUtil.format(new Date(), "yyyy-MM-dd"), page, size);
		
		return queDataPageValue;
	}
	
	/**
	 * 检测业主登记的状态
	 * @param proprietorId 业主id
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午4:53:34
	 */
	@RequestMapping("checkProrietorStatus")
	@ResponseBody
	public ReturnValue checkProrietorStatus(String proprietorId, String userId, String projectId, String houseId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		householdService.checkProrietorStatus(proprietorId, userId, projectId, houseId);
		
		return ReturnValue.success();
	}
	
	/**
	 * 登记业主情况
	 * @param proprietorId
	 * @param picUrl
	 * @param sex
	 * @param workUnit
	 * @param emergencyContact
	 * @param emergencyPhone
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午7:02:26
	 */
	@RequestMapping("registerProprietor")
	@ResponseBody
	public ReturnValue registerProprietor(String proprietorId, String picUrl, String sex, String workUnit, String emergencyContact, String emergencyPhone) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		ProprietorSingleHouseVo singleHouse = new ProprietorSingleHouseVo();
		singleHouse.setProprietorId(proprietorId);
		singleHouse.setPicUrl(picUrl);
		singleHouse.setWorkUnit(workUnit);
		singleHouse.setEmergencyContact(emergencyContact);
		singleHouse.setEmergencyPhone(emergencyPhone);
		singleHouse.setSex(sex);
		
		householdService.registerProprietor(singleHouse);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取业主的登记情况
	 * @param proprietorId
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午7:58:34
	 */
	@RequestMapping("getProprietorOfRegister")
	@ResponseBody
	public DataValue<ProprietorSingleHouseVo> getProprietorOfRegister(String proprietorId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ProprietorSingleHouseVo singleHouseVo =  householdService.getProprietorOfRegister(proprietorId);
		
		return DataValue.success(singleHouseVo);
	}
	
	/**
	 * 获取住户列表
	 * @param houseId
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午9:13:46
	 */
	@RequestMapping("getResidentList")
	@ResponseBody
	public DataListValue<ResidentVo> getResidentList(String houseId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ResidentBo params = new ResidentBo();
		params.setHouseId(houseId);
		DataPageValue<ResidentVo> dataPage = householdService.listResident4Page(params, 1000, 1);
		List<ResidentVo> residentVoList= dataPage.getDataList();
		return DataListValue.success(residentVoList);
	}
	
	/**
	 * 登记住户情况
	 * @param pic 头像路径
	 * @param name 姓名
	 * @param sex 性别
	 * @param idNum 身份证号码
	 * @param mobile 手机号码
	 * @param residentRelation 与业主的关系
	 * @param workUnit 工作单位
	 * @param nativePlace 籍贯
	 * @return 
	 * @author xhw
	 * @date 2016年3月10日 下午1:59:08
	 */
	@RequestMapping("registerResident")
	@ResponseBody
	public ReturnValue registerResident(ResidentBo residentBo) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		// 住户信息
		ResidentVo residentVo = new ResidentVo();
		// 住户房屋信息
		ResidentHouseVo residentHouseVo = new ResidentHouseVo();
		// 客户信息
		CustomerVo customer = new CustomerVo();
		
		residentVo.setPic(residentBo.getPic());
		residentVo.setName(residentBo.getName());
		residentVo.setMobile(residentBo.getMobile());
		residentVo.setWorkUnit(residentBo.getWorkUnit());
		residentVo.setProjectId(residentBo.getProjectId());
		
		residentHouseVo.setProjectId(residentBo.getProjectId());
		residentHouseVo.setHouseId(residentBo.getHouseId());
		residentHouseVo.setProprietorId(residentBo.getProprietorId());
		residentHouseVo.setResidentRelation(residentBo.getResidentRelation());
		
		customer.setSex(residentBo.getSex());
		customer.setIdNum(residentBo.getIdNum());
		customer.setNativePlace(residentBo.getNativePlace());
		
		residentVo.setCustomer(customer);
		residentVo.setResidentHouse(residentHouseVo);
		
		householdService.saveResident(residentVo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取房子的详情
	 * @param residentId
	 * @param houseId
	 * @return 
	 * @author xhw
	 * @date 2016年3月10日 下午4:17:19
	 */
	@RequestMapping("getResident")
	@ResponseBody
	public DataValue<ResidentVo> getResident(String residentId, String houseId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ResidentVo residentVo = householdService.getResident(residentId, houseId);
		//用户信息
		CustomerVo customer = userService.getCustomerByGlobalId(residentVo.getCustGlobalId());
		
		residentVo.setCustomer(customer);
		
		return DataValue.success(residentVo);
	}
	
	/**
	 * 更新住户情况
	 * @param residentBo
	 * @return 
	 * @author xhw
	 * @date 2016年3月10日 下午4:36:32
	 */
	@RequestMapping("updateResident")
	@ResponseBody
	public ReturnValue updateResident(ResidentBo residentBo) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ResidentVo residentVo = new ResidentVo();
		CustomerVo customer = new CustomerVo();
		ResidentHouseVo residentHouseVo = new ResidentHouseVo();
		
		CustomerVo customerVo = userService.getCustomerByGlobalId(residentBo.getCustGlobalId());
		customer.setId(customerVo.getId());
		customer.setSex(residentBo.getSex());
		customer.setNativePlace(residentBo.getNativePlace());
		customer.setGlobalId(residentBo.getCustGlobalId());
		customer.setIdNum(customerVo.getIdNum());
		
		residentVo.setName(residentBo.getName());
		residentVo.setPic(residentBo.getPic());
		residentVo.setMobile(residentBo.getMobile());
		residentVo.setWorkUnit(residentBo.getWorkUnit());
		residentVo.setId(residentBo.getResidentId());
		
		// TODO 需要拿到residentHouseId，接口入参需带上这个参数
		ResidentHouseVo residentHouse = householdService.getResidentHouse(residentBo.getResidentId(), residentBo.getHouseId());
		residentHouseVo.setResidentRelation(residentBo.getResidentRelation());
		residentHouseVo.setId(residentHouse.getId());
		
		residentVo.setCustomer(customer);
		residentVo.setResidentHouse(residentHouseVo);
		
		householdService.updateResident(residentVo);
		return ReturnValue.success();
	}
	
	/**
	 * 获取用户信息
	 * @param custGlobalId
	 * @return 
	 * @author xhw
	 * @date 2016年3月11日 下午2:16:48
	 */
	@RequestMapping("getUser")
	@ResponseBody
	public DataValue<UserVo> getUser(String custGlobalId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		UserVo user = userService.getUserByCustGlobalId(custGlobalId);
		return DataValue.success(user);
	}
	
	/**
	 * 更新用户信息
	 * @param userVo 用户信息
	 * @return 
	 * @author xhw
	 * @date 2016年3月11日 下午2:01:53
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public ReturnValue updateUser(UserVo userVo) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		UserVo user = userService.getUserByCustGlobalId(userVo.getCustGlobalId());
		MyBeanUtil.copyBeanNotNull2Bean(userVo, user);
		// 校验用户信息
		user = checkService.updateUserCheck(user);
		// 发送TCC消息，更新用户信息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
		
		return ReturnValue.success();
	}
	
	/**
	 * 创建用户反馈
	 * @param houseId 房屋id
	 * @param residentId 住户/业主id
	 * @param isProprietor 是否为业主。0：否；1：是
	 * @param content 内容
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 上午11:02:05
	 */
	@RequestMapping("createResidentFeedback")
	@ResponseBody
	public ReturnValue createResidentFeedback(String houseId, String residentId, String isProprietor, String content) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ResidentFeedbackVo residentFeedbackVo = new ResidentFeedbackVo();
		residentFeedbackVo.setContent(content);
		residentFeedbackVo.setHouseId(houseId);
		residentFeedbackVo.setResidentId(residentId);
		residentFeedbackVo.setIsProprietor(isProprietor);
		householdService.createResidentFeedback(residentFeedbackVo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 保存用户手机端消息设置
	 * @param userId
	 * @param sound
	 * @param popFlag
	 * @param floatFlag
	 * @param lockFlag
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午3:06:51
	 */
	@RequestMapping("saveAppMsgSetting")
	@ResponseBody
	public ReturnValue saveAppMsgSetting(String userId, String sound, String popFlag, String floatFlag, String lockFlag) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		AppMsgSettingVo appMsgSettingVo = householdService.getAppMsgSetting(userId);
		if(appMsgSettingVo == null){  //创建
			AppMsgSettingVo appMsgSetting = new AppMsgSettingVo();
			appMsgSetting.setFloatFlag(floatFlag);
			appMsgSetting.setLockFlag(lockFlag);
			appMsgSetting.setPopFlag(popFlag);			
			appMsgSetting.setSound(sound);
			
			appMsgSetting.setUserId(userId);
			
			householdService.createAppMsgSetting(appMsgSetting);
		}else{ //更新
			appMsgSettingVo.setFloatFlag(floatFlag);
			appMsgSettingVo.setLockFlag(lockFlag);
			appMsgSettingVo.setPopFlag(popFlag);			
			appMsgSettingVo.setSound(sound);
			appMsgSettingVo.setUserId(userId);
			appMsgSettingVo.setUpdateTime(new Date());
			
			householdService.updateAppMsgSetting(appMsgSettingVo);
		}
		
		return ReturnValue.success();
	}
	
	/**
	 * 根据用户id，获取用户手机端消息设置
	 * @param userId 用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月14日 下午3:17:45
	 */
	@RequestMapping("getAppMsgSetting")
	@ResponseBody
	public DataValue<AppMsgSettingVo> getAppMsgSetting(String userId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		AppMsgSettingVo appMsgSettingVo = householdService.getAppMsgSetting(userId);
		return DataValue.success(appMsgSettingVo);
	}
	

}
