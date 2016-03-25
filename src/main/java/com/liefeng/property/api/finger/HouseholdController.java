package com.liefeng.property.api.finger;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.api.ro.CheckinQueueListRo;
import com.liefeng.property.api.ro.CheckinQueueRo;
import com.liefeng.property.api.ro.PhoneRo;
import com.liefeng.property.api.ro.ProprietorRo;
import com.liefeng.property.api.ro.ProprietorStatusRo;
import com.liefeng.property.api.ro.ResidentIdHouseIdRo;
import com.liefeng.property.api.ro.ResidentRo;
import com.liefeng.property.api.ro.ResidentUpdateRo;
import com.liefeng.property.api.ro.VisitorRo;
import com.liefeng.property.api.ro.id.HouseIdRo;
import com.liefeng.property.api.ro.id.ProprietorIdRo;
import com.liefeng.property.api.ro.id.UserIdRo;
import com.liefeng.property.api.ro.id.VisitorIdRo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;
import com.liefeng.property.vo.household.VisitorVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业主、住户公共服务类（app） 
 * @author xhw
 * @date 2016年3月8日 下午1:45:25
 */
@Api(value="[业主|住户]模块")
@RestController
@RequestMapping(value = "/api/finger/household")
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
	@ApiOperation(value="扫二维码，获取排队号")
	@RequestMapping(value="/createCheckinQueue", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<CheckinQueueVo> createCheckinQueue(@Valid @ModelAttribute CheckinQueueRo checkinQueueRo) {
		
		CheckinQueueVo queueVo = householdService.createCheckinQueue(checkinQueueRo.getProjectId(), checkinQueueRo.getHouseId(), checkinQueueRo.getUserId());
		
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
	@ApiOperation(value="获取排队详情页面的头部信息")
	@RequestMapping(value="/getCheckinQueue", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<CheckinQueueVo> getCheckinQueue(@Valid @ModelAttribute CheckinQueueRo checkinQueueRo) {
		
		CheckinQueueVo queueVo = householdService.getCheckinQueue(checkinQueueRo.getProjectId(), checkinQueueRo.getHouseId(), checkinQueueRo.getUserId());
		
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
	@ApiOperation(value="获取排队页面的列表详情")
	@RequestMapping(value="/getCheckinQueueList", method=RequestMethod.POST)
	@ResponseBody
	public DataPageValue<CheckinQueueVo> getCheckinQueueList(@Valid @ModelAttribute CheckinQueueListRo checkinQueueListRo) {
		
		DataPageValue<CheckinQueueVo> queDataPageValue = householdService.getCheckinQueueOfNotStatus(checkinQueueListRo.getProjectId(), HouseholdConstants.CheckinQueueStatus.UNTREATED, TimeUtil.format(new Date(), "yyyy-MM-dd"), checkinQueueListRo.getPage(), checkinQueueListRo.getSize());
		
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
	@ApiOperation(value="检测业主登记的状态")
	@RequestMapping(value="/checkProrietorStatus", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue checkProrietorStatus(@Valid @ModelAttribute ProprietorStatusRo proprietorStatusRo) {
		
		householdService.checkProrietorStatus(proprietorStatusRo.getProprietorId(), proprietorStatusRo.getUserId(), proprietorStatusRo.getProjectId(), proprietorStatusRo.getHouseId());
		
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
	@ApiOperation(value="登记业主资料")
	@RequestMapping(value="/registerProprietor", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue registerProprietor(@Valid @ModelAttribute ProprietorRo proprietorRo) {
		ProprietorSingleHouseVo singleHouse = new ProprietorSingleHouseVo();
		singleHouse.setProprietorId(proprietorRo.getProprietorId());
		singleHouse.setPicUrl(proprietorRo.getPicUrl());
		singleHouse.setWorkUnit(proprietorRo.getWorkUnit());
		singleHouse.setEmergencyContact(proprietorRo.getEmergencyContact());
		singleHouse.setEmergencyPhone(proprietorRo.getEmergencyPhone());
		singleHouse.setSex(proprietorRo.getSex());
		
		householdService.registerProprietor(singleHouse);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取业主的登记情况
	 * @param id 业主id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午7:58:34
	 */
	@ApiOperation(value="获取业主登记资料")
	@RequestMapping(value="/getProprietorOfRegister", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<ProprietorSingleHouseVo> getProprietorOfRegister(@Valid @ModelAttribute ProprietorIdRo proprietorIdRo) {
		
		ProprietorSingleHouseVo singleHouseVo =  householdService.getProprietorOfRegister(proprietorIdRo.getId());
		
		return DataValue.success(singleHouseVo);
	}
	
	/**
	 * 获取住户列表
	 * @param houseId
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午9:13:46
	 */
	@ApiOperation(value="获取住户列表")
	@RequestMapping(value="/getResidentList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<ResidentVo> getResidentList(@Valid @ModelAttribute HouseIdRo houseIdRo) {
		
		ResidentBo params = new ResidentBo();
		params.setHouseId(houseIdRo.getHouseId());
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
	@ApiOperation(value="登记住户资料")
	@RequestMapping(value="/registerResident", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue registerResident(@Valid @ModelAttribute ResidentRo residentRo) {
		
		ResidentBo residentBo = MyBeanUtil.createBean(residentRo, ResidentBo.class);
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
	 * 获取住户的详情
	 * @param residentId
	 * @param houseId
	 * @return 
	 * @author xhw
	 * @date 2016年3月10日 下午4:17:19
	 */
	@ApiOperation(value="获取住户资料")
	@RequestMapping(value="/getResident", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<ResidentVo> getResident(@Valid @ModelAttribute ResidentIdHouseIdRo residentIdHouseIdRo) {
		
		ResidentVo residentVo = householdService.getResident(residentIdHouseIdRo.getResidentId(), residentIdHouseIdRo.getHouseId());
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
	@ApiOperation(value="修改住户资料")
	@RequestMapping(value="/updateResident", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue updateResident(@Valid @ModelAttribute ResidentUpdateRo residentUpdateRo) {
		
		ResidentBo residentBo = MyBeanUtil.createBean(residentUpdateRo, ResidentBo.class);
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
		
		//  需要拿到residentHouseId，接口入参需带上这个参数
		ResidentHouseVo residentHouse = householdService.getResidentHouse(residentBo.getResidentId(), residentBo.getHouseId());
		residentHouseVo.setResidentRelation(residentBo.getResidentRelation());
		residentHouseVo.setId(residentHouse.getId());
		
		residentVo.setCustomer(customer);
		residentVo.setResidentHouse(residentHouseVo);
		
		householdService.updateResident(residentVo);
		return ReturnValue.success();
	}

	/**
	 * 添加住户
	 * @param visitorRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午1:52:53
	 */
	@ApiOperation(value="添加访客")
	@RequestMapping(value="/addVisitor", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<String> addVisitor(@Valid @ModelAttribute VisitorRo visitorRo) {
		
		VisitorVo visitorVo = new VisitorVo();
		MyBeanUtil.copyBeanNotNull2Bean(visitorRo, visitorVo);
		visitorVo.setType(HouseholdConstants.VisitorType.USER_CHECKIN);
		visitorVo.setInTime(TimeUtil.format(visitorRo.getVisitDate(), "yyyy-MM-dd"));
		householdService.createVisitor(visitorVo);
		
		//TODO
		String password = "123456";
		return DataValue.success(password);
	}
	
	/**
	 * 获取用户的访客列表
	 * @param userIdRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午2:57:43
	 */
	@ApiOperation(value="获取用户的访客列表")
	@RequestMapping(value="/getVisitorList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<VisitorVo> getVisitorList(@Valid @ModelAttribute UserIdRo userIdRo) {
		
		List<VisitorVo> visitorVoList = householdService.getVisitorList(userIdRo.getUserId());
		
		return DataListValue.success(visitorVoList);
	}
	
	/**
	 * 获取访客的访问记录
	 * @param phoneRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午3:33:38
	 */
	@ApiOperation(value="获取访客的访问记录")
	@RequestMapping(value="/getVisitorHistory", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<VisitorVo> getVisitorHistory(@Valid @ModelAttribute PhoneRo phoneRo) {
		
		List<VisitorVo> visitorVoList = householdService.getVisitorHistory(phoneRo.getPhone());
		
		return DataListValue.success(visitorVoList);
	}
	
	/**
	 * 获取访客的信息
	 * @param visitorIdRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月23日 下午4:11:05
	 */
	@ApiOperation(value="获取访客的信息")
	@RequestMapping(value="/getVisitor", method=RequestMethod.POST)
	@ResponseBody
	public DataValue<VisitorVo> getVisitor(@Valid @ModelAttribute VisitorIdRo visitorIdRo) {
		
		VisitorVo visitorVo = householdService.getVisitor(visitorIdRo.getVisitorId());
		
		return DataValue.success(visitorVo);
	}
}
