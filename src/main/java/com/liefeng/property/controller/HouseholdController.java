package com.liefeng.property.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * 入住办理公共服务类（app） 
 * @author xhw
 * @date 2016年3月8日 下午1:45:25
 */
@RestController
@RequestMapping(value = "/household")
public class HouseholdController {

	@Autowired
	private IHouseholdService householdService;
	
	@Autowired
	private IUserService userService;
	
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
		
		List<ResidentVo> residentVoList = householdService.getResidentListByHouseId(houseId);
		
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
		ResidentVo residentVo = new ResidentVo();
		CustomerVo customer = new CustomerVo();
		customer.setSex(residentBo.getSex());
		customer.setIdNum(residentBo.getIdNum());
		customer.setNativePlace(residentBo.getNativePlace());
		
		residentVo.setCustomer(customer);
		residentVo.setHouseId(residentBo.getHouseId());
		residentVo.setProprietorId(residentBo.getProprietorId());
		residentVo.setPic(residentBo.getPic());
		residentVo.setName(residentBo.getName());
		residentVo.setMobile(residentBo.getMobile());
		residentVo.setResidentRelation(residentBo.getResidentRelation());
		residentVo.setWorkUnit(residentBo.getWorkUnit());
		
		householdService.saveResident(residentVo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取这号的详情
	 * @param residentId
	 * @return 
	 * @author xhw
	 * @date 2016年3月10日 下午4:17:19
	 */
	@RequestMapping("getResident")
	@ResponseBody
	public DataValue<ResidentVo> getResident(String residentId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		ResidentVo residentVo = householdService.getResident(residentId);
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
		customer.setSex(residentBo.getSex());
		customer.setNativePlace(residentBo.getNativePlace());
		customer.setGlobalId(residentBo.getCustGlobalId());
		
		CustomerVo customerVo = userService.getCustomerByGlobalId(residentBo.getCustGlobalId());
		customer.setId(customerVo.getId());
		
		residentVo.setCustomer(customer);
		residentVo.setPic(residentBo.getPic());
		residentVo.setMobile(residentBo.getMobile());
		residentVo.setResidentRelation(residentBo.getResidentRelation());
		residentVo.setWorkUnit(residentBo.getWorkUnit());
		residentVo.setId(residentBo.getResidentId());
		
		householdService.updateResident(residentVo);
		return ReturnValue.success();
	}
}
