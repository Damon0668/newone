package com.liefeng.property.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.constant.UserConstants;
import com.liefeng.base.exception.UserException;
import com.liefeng.base.vo.CustomerVo;
import com.liefeng.base.vo.UserVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.household.CheckinQueueBo;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.bo.household.ResidentFeedbackBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.constant.ProjectConstants;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.domain.household.AppFriendContext;
import com.liefeng.property.domain.household.AppMsgSettingContext;
import com.liefeng.property.domain.household.CheckinMaterialContext;
import com.liefeng.property.domain.household.CheckinQueueContext;
import com.liefeng.property.domain.household.CheckinScheduleContext;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentCarContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.domain.household.ResidentFeedbackContext;
import com.liefeng.property.domain.household.ResidentHouseContext;
import com.liefeng.property.domain.household.VisitorContext;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.error.HouseholdErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.household.AppFriendVo;
import com.liefeng.property.vo.household.AppMsgSettingVo;
import com.liefeng.property.vo.household.CheckinMaterialVo;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.CheckinScheduleVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentCarVo;
import com.liefeng.property.vo.household.ResidentFeedbackVo;
import com.liefeng.property.vo.household.ResidentHouseVo;
import com.liefeng.property.vo.household.ResidentVo;
import com.liefeng.property.vo.household.VisitorVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.service.constant.PushActionConstants;
import com.liefeng.service.constant.PushMsgConstants;
import com.liefeng.service.vo.PushMsgTemplateVo;
import com.liefeng.service.vo.msg.ListUserMsg;
import com.liefeng.service.vo.msg.SingleUserMsg;

/**
 * household包相关表服务类
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
@Service
public class HouseholdService implements IHouseholdService {

	private static Logger logger = LoggerFactory.getLogger(HouseholdService.class);

	@Autowired
	private ICheckService checkService;

	@Autowired
	private ITccMsgService tccMsgService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IPushMsgService pushMsgService;
	
	/**
	 * 保存业主信息
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void saveProprietor(ProprietorSingleHouseVo singleHouse) throws LiefengException {
		try {
			// 业主是否存在标识
			boolean isExit = false;

			// 校验信息
			validateProprietor(singleHouse);

			// 客户信息校验
			CustomerVo customer = initCustomer(singleHouse);
			customer = checkService.createCustomerCheck(customer);
			singleHouse.setCustGlobalId(customer.getGlobalId());

			// 校验同个OEM下，同个小区业主是否已存在
			ProprietorContext proprietorContext = ProprietorContext.build();
			ProprietorVo proprietor = proprietorContext.get(singleHouse.getProjectId(), customer.getGlobalId());

			// 同个OEM下，同个小区保留一份业主信息，不同小区保留多份业主信息
			if (proprietor == null) {
				proprietor = MyBeanUtil.createBean(singleHouse, ProprietorVo.class);
				proprietor.setStatus(HouseholdConstants.ProprietorStatus.ACTIVE); // 默认为激活状态
				proprietorContext = ProprietorContext.build(proprietor);
				proprietor = proprietorContext.create();
			} else {
				isExit = true; // 同个OEM下，同个小区业主信息已存在
				logger.info("业主信息已存在，后续将不执行业主用户创建动作");
			}

			// 业主房产信息保存
			ProprietorHouseVo proprietorHouse = MyBeanUtil.createBean(singleHouse, ProprietorHouseVo.class);
			proprietorHouse.setProprietorId(proprietor.getId());
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
			proprietorHouseContext.create();

			// 更新房子销售状态为售出
			HouseVo house = new HouseVo();
			house.setId(singleHouse.getHouseId());
			house.setSaleStatus(ProjectConstants.HouseSaleStatus.HAD_SALE);
			HouseContext houseContext = HouseContext.build(house);
			houseContext.update();

			// 仅当同个OEM下，同个小区下业主不存在时，才做用户创建
			// 业主信息存在时，即使传过来的手机号不同也不做用户创建，为了保证同个OEM下，同个小区业主用户信息只有一份
			if (!isExit) {
				// 后台默认创建的手机用户信息
				UserVo user = setUpUser4Create(customer, UserConstants.HouseholdType.PROPRIETOR);
				logger.info("用户信息组装成功！");

				try {
					// 用户信息校验
					user = checkService.createUserCheck(user);
				} catch (UserException e) {
					logger.error("创建用户信息校验出现异常，异常编码为({})，异常信息为({})", e.getCode(), e.getMessage());
					return;
				}

				// 发送TCC消息，创建用户（内含创建客户或更新客户逻辑）
				tccMsgService.sendTccMsg(TccBasicEvent.CREATE_USER, user.toString());
				logger.info("推送“创建用户”TCC消息成功！");
			}

		} catch (LiefengException e) {
			logger.error("保存业主信息出现异常,异常码（{}）,异常信息（{}）。", e.getCode(), e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}

	/**
	 * 更新业主信息
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updatePropritor(ProprietorSingleHouseVo singleHouse) throws LiefengException {
		try {
			// 校验信息
			validateProprietor(singleHouse);

			// 客户信息校验
			CustomerVo customer = initCustomer(singleHouse);
			customer = checkService.updateCustomerCheck(customer);

			// 业主信息更新
			ProprietorVo proprietor = MyBeanUtil.createBean(singleHouse, ProprietorVo.class);
			proprietor.setId(singleHouse.getProprietorId());
			ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
			proprietorContext.update();

			// 业主房产信息更新
			ProprietorHouseVo proprietorHouse = MyBeanUtil.createBean(singleHouse, ProprietorHouseVo.class);
			proprietorHouse.setId(singleHouse.getProprietorHouseId());
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
			proprietorHouseContext.update();

			// 用户更新信息设置
			UserVo newUser = setUpUser4Update(customer, UserConstants.HouseholdType.PROPRIETOR);

			// 校验用户信息
			UserVo user = checkService.updateUserCheck(newUser);

			// 发送TCC消息，更新用户信息
			tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
			logger.info("推送“更新用户”TCC消息成功！");
		} catch (LiefengException e) {
			logger.error("更新业主信息出现异常,异常码（{}）,异常信息（{}）", e.getCode(), e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}

	/**
	 * 保存住户信息
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void saveResident(ResidentVo resident) throws LiefengException {
		try {
			// 住户是否存在标识
			boolean isExit = false;

			// 校验信息
			validateResident(resident);

			// 客户信息校验
			CustomerVo customer = resident.getCustomer();
			customer.setRealName(resident.getName());
			customer.setMobile(resident.getMobile());
			customer = checkService.createCustomerCheck(customer);

			// 校验住户是否已存在
			ResidentContext residentContext = ResidentContext.build();
			ResidentVo existedResident = residentContext.get(resident.getProjectId(), customer.getGlobalId());

			// 同个OEM下，同个小区只保存一份住户信息，不同小区保存多份住户信息
			if (existedResident == null) {
				resident.setStatus(HouseholdConstants.ResidentStatus.ACTIVE); // 默认为激活状态
				resident.setCustGlobalId(customer.getGlobalId());
				residentContext = ResidentContext.build(resident);
				existedResident = residentContext.create();
			} else {
				isExit = true; // 同个OEM下，同个小区住户信息已存在
				logger.info("住户信息已存在，后续将不执行住户、用户创建动作");
			}

			// 保存住户房屋信息
			ResidentHouseVo residentHouse = resident.getResidentHouse();
			residentHouse.setResidentId(existedResident.getId());
			ResidentHouseContext residentHouseContext = ResidentHouseContext.build(residentHouse);
			residentHouseContext.create();

			// 仅当同个OEM下，同个小区下住户不存在时，才做用户创建
			// 住户信息存在时，即使传过来的手机号不同也不做用户创建，为了保证同个OEM下，同个小区住户用户信息只有一份
			if (!isExit) {
				if (!ValidateHelper.isNotEmptyString(customer.getMobile())) { // 手机号为空，只保存客户信息，不保存用户信息
						customer = checkService.createCustomerCheck(customer);
						
						// 发送TCC消息，创建客户
						tccMsgService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, customer.toString());
						logger.info("保存住户【手机号为空】 --> 推送创建客户TCC消息成功！");
				} else { 
					// 后台默认创建的手机用户信息
					UserVo user = setUpUser4Create(customer, UserConstants.HouseholdType.RESIDENT);
					logger.info("保存住户 --> 组装用户信息成功！");

					try {
						// 用户信息校验
						user = checkService.createUserCheck(user);
					} catch (UserException e) { // 仅仅会捕获到用户存在的异常
						logger.error("用户创建校验出现异常，异常编码为({})，异常信息为({})", e.getCode(), e.getMessage());
						return;
					}

					// 发送TCC消息，创建用户（内含创建客户或更新客户逻辑）
					tccMsgService.sendTccMsg(TccBasicEvent.CREATE_USER, user.toString());
					logger.info("保存住户【手机号不为空】 --> 推送创建用户TCC消息成功！");
				}
			}
		} catch (LiefengException e) {
			logger.error("保存住户信息出现异常,异常码（{}）,异常信息（{}）", e.getCode(), e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}

	/**
	 * 更新住户信息
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateResident(ResidentVo resident) throws LiefengException {
		try {
			// 校验信息
			validateResident(resident);

			// 客户信息校验
			CustomerVo customer = resident.getCustomer();
			customer.setRealName(resident.getName());
			customer.setMobile(resident.getMobile());
			checkService.updateCustomerCheck(customer);

			// 更新住户信息
			ResidentContext residentContext = ResidentContext.build(resident);
			residentContext.update();

			// 更新住户房屋信息
			ResidentHouseVo residentHouse = resident.getResidentHouse();
			residentHouse.setResidentId(resident.getId());
			ResidentHouseContext residentHouseContext = ResidentHouseContext.build(residentHouse);
			residentHouseContext.update();

			
			if(!ValidateHelper.isNotEmptyString(customer.getMobile())) { // 更新客户信息
				// 校验客户信息
				customer = checkService.updateCustomerCheck(customer);
				
				// 发送Tcc消息，更新用户信息
				tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_CUSTOMER, customer.toString());
				logger.info("更新住户【手机号为空】 --> 推送更新客户TCC消息成功！");
			} else {
				// 用户更新信息设置
				UserVo user = setUpUser4Update(customer, UserConstants.HouseholdType.RESIDENT);
				
				// 用户ID为空代表保存时没有创建用户信息，即保存的时候住户的手机号为空
				if(!ValidateHelper.isNotEmptyString(user.getId())) {  // 新增住户信息
					try {
						// 用户信息校验
						user = checkService.createUserCheck(user);
					} catch (UserException e) { // 仅仅会捕获到用户存在的异常
						logger.error("用户创建校验出现异常，异常编码为({})，异常信息为({})", e.getCode(), e.getMessage());
						return;
					}

					// 发送TCC消息，创建用户（内含创建客户或更新客户逻辑）
					tccMsgService.sendTccMsg(TccBasicEvent.CREATE_USER, user.toString());
					logger.info("更新住户【手机号不为空，用户信息不存在情况】 --> 推送创建用户TCC消息成功！");
				} else {
					// 校验用户信息
					user = checkService.updateUserCheck(user);
					
					// 发送Tcc消息，更新用户信息
					tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
					logger.info("更新住户【手机号不为空，用户信息存在情况】 --> 推送更新用户TCC消息成功！");
				}
			}
			
		} catch (LiefengException e) {
			logger.error("更新住户信息出现异常,异常码（{}）,异常信息（{}）", e.getCode(), e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}

	}

	/**
	 * 分页查询业主综合信息
	 */
	@Override
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params={}, pageSize={}, currentPage={}", params, pageSize, currentPage);

		ProprietorContext proprietorContext = ProprietorContext.build();

		return proprietorContext.listProprietorSingleHouse4Page(params, pageSize, currentPage);
	}

	/**
	 * 获取业主某个房产的信息
	 */
	@Override
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		logger.info("业主房产ID：proprietorHouseId={}", proprietorHouseId);

		ProprietorContext proprietorContext = ProprietorContext.build();

		return proprietorContext.getProprietorSingleHouse(proprietorHouseId);
	}

	/**
	 * 分页查询住户信息
	 */
	@Override
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize, Integer currentPage) {
		logger.info("查询过滤条件 params={}, pageSize={}, currentPage={}", params, pageSize, currentPage);

		ResidentContext residentContext = ResidentContext.build();

		return residentContext.listResident4Page(params, pageSize, currentPage);
	}

	/**
	 * 查询某房子中某个住户信息
	 */
	@Override
	public ResidentVo getResident(String residentId, String houseId) {
		ResidentContext residentContext = ResidentContext.loadById(residentId);
		return residentContext.get(houseId);
	}

	/**
	 * 根据项目ID和房号查询业主房产
	 */
	@Override
	public ProprietorHouseVo getProprietorHouse(String projectId, String houseNum) {
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.loadByProjectIdAndHouseNum(projectId,
				houseNum);

		return proprietorHouseContext.getProprietorHouse();
	}

	/**
	 * 根据业主房产ID查询入住资料信息
	 */
	@Override
	public List<CheckinMaterialVo> getMaterialByProprietorHouseId(String proprietorHouseId) {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext
				.loadByProprietorHouseId(proprietorHouseId);

		return checkinMaterialContext.getList();
	}

	/**
	 * 批量保存入住资料信息
	 */
	@Override
	public void saveCheckinMaterials(List<CheckinMaterialVo> checkinMaterialList) throws Exception {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext.build();

		checkinMaterialContext.create(checkinMaterialList);
	}

	/**
	 * 根据业主房产ID删除入住资料信息
	 */
	@Override
	public void delMaterialByProprietorHouseId(String proprietorHouseId) throws Exception {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext
				.loadByProprietorHouseId(proprietorHouseId);

		checkinMaterialContext.delete();
	}

	/**
	 * 根据业主ID获取业主信息
	 */
	@Override
	public ProprietorVo getProprietorById(String id) {
		ProprietorContext proprietorContext = ProprietorContext.loadById(id);
		return proprietorContext.get();
	}

	/**
	 * 分页查询业主用户信息
	 */
	@Override
	public DataPageValue<UserVo> listUsers(ProprietorBo params, Integer currentPage, Integer pageSize) {
		ProprietorContext proprietorContext = ProprietorContext.build();

		return proprietorContext.listUsers(params, currentPage, pageSize);
	}

	/**
	 * 获取账号关联房子中的住户或业主
	 */
	@Override
	public List<ResidentVo> queryRelatedHouses(String projectId, String custGlobalId) {
		ResidentContext residentContext = ResidentContext.build();
		return residentContext.queryRelatedHouse(projectId, custGlobalId);
	}

	/**
	 * 分页查询入住排队信息
	 */
	@Override
	public DataPageValue<CheckinQueueVo> getCheckinQueues(CheckinQueueBo params, Integer pageSize,
			Integer currentPage) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getCheckinQueues(params, pageSize, currentPage);
	}

	/**
	 * 更新入住排队信息
	 */
	@Override
	public void updateCheckinQueue(CheckinQueueVo checkinQueue) throws LiefengException {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build(checkinQueue);
		CheckinQueueVo queueReturn = checkinQueueContext.update();
		if(queueReturn != null){
		
			//办理完成时个推
			if(HouseholdConstants.CheckinQueueStatus.FINISHED.equals(queueReturn.getStatus())){
				HouseVo houseVo = projectService.findHouseById(queueReturn.getHouseId());
				//获取某个房间的所有用户的clientId
				List<String> clientIdList = listClientIdByProjectIdAndHouseNum(queueReturn.getProjectId(), houseVo.getHouseNum());
				if(clientIdList != null && clientIdList.size() > 0){
					//获取推送消息模板
					PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.CHECKIN_SUCCESS);
					
					if(pushMsgTemplateVo != null){
						ListUserMsg message = new ListUserMsg();
						message.setAction(PushActionConstants.CHECKIN_SUCCESS);
						message.setMsgCode(pushMsgTemplateVo.getMsgCode());
						message.setTitle(pushMsgTemplateVo.getTitle());
						message.setContent(pushMsgTemplateVo.getContent());
						message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
						message.setReceiveClientIdList(clientIdList);
						
						pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
						logger.info("入住办理完成号时群推消息{}", message);
					}
				}
				
				//大于当前完成的排队号的排队
			   CheckinQueueVo queueUntreated =getCheckinQueueMoreThanSeq(queueReturn.getProjectId(), HouseholdConstants.CheckinQueueStatus.UNTREATED, queueReturn.getSeq(), TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			   
			   if(queueUntreated != null){
				   HouseVo houseUntreated = projectService.findHouseById(queueUntreated.getHouseId());
					//获取某个房间的所有用户的clientId
					List<String> clientIdUntreatedList = listClientIdByProjectIdAndHouseNum(queueReturn.getProjectId(), houseUntreated.getHouseNum());
					if(clientIdUntreatedList != null && clientIdUntreatedList.size() > 0){
						//获取推送消息模板
						PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.CHECKIN_TURN_YOU);
						
						if(pushMsgTemplateVo != null){
							ListUserMsg message = new ListUserMsg();
							message.setAction(PushActionConstants.CHECKIN_TURN_YOU);
							message.setMsgCode(pushMsgTemplateVo.getMsgCode());
							message.setTitle(pushMsgTemplateVo.getTitle());
							message.setContent(pushMsgTemplateVo.getContent());
							message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
							message.setReceiveClientIdList(clientIdList);
							
							pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
							logger.info("入住办理提醒前来办理时群推消息{}", message);
						}
					}
			   }
			}
	
			//开始办理时个推
			if(HouseholdConstants.CheckinQueueStatus.HANDLING.equals(queueReturn.getStatus())){
				HouseVo houseVo = projectService.findHouseById(queueReturn.getHouseId());
				//获取某个房间的所有用户的clientId
				List<String> clientIdList = listClientIdByProjectIdAndHouseNum(queueReturn.getProjectId(), houseVo.getHouseNum());
				if(clientIdList != null && clientIdList.size() > 0){
					//获取推送消息模板
					PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.CHECKIN_HANDLING);
					
					if(pushMsgTemplateVo != null){
						ListUserMsg message = new ListUserMsg();
						message.setAction(PushActionConstants.CHECKIN_HANDLING);
						message.setMsgCode(pushMsgTemplateVo.getMsgCode());
						message.setTitle(pushMsgTemplateVo.getTitle());
						message.setContent(pushMsgTemplateVo.getContent());
						message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
						message.setReceiveClientIdList(clientIdList);
						
						pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
						logger.info("入住办理开始时群推消息{}", message);
					}
				}
			}
		}
	}

	/**
	 * 根据项目ID查询入住安排时间
	 */
	@Override
	public List<CheckinScheduleVo> getScheduleByProjectId(String projectId) {
		CheckinScheduleContext checkinScheduleContext = CheckinScheduleContext.loadByProjectId(projectId);
		return checkinScheduleContext.getCheckinSchedules();
	}

	/**
	 * 批量保存入住安排时间
	 */
	@Override
	public void saveCheckinSchedule(String projectId, List<CheckinScheduleVo> checkinScheduleList) {
		// 删除旧的入住安排时间
		CheckinScheduleContext checkinScheduleContext = CheckinScheduleContext.loadByProjectId(projectId);
		checkinScheduleContext.delete();

		// 保存新增的入住安排时间
		checkinScheduleContext = CheckinScheduleContext.build();
		checkinScheduleContext.create(checkinScheduleList);
	}

	/**
	 * 初始化客户信息
	 */
	private CustomerVo initCustomer(ProprietorSingleHouseVo singleHouse) {
		CustomerVo customer = new CustomerVo();
		customer = MyBeanUtil.createBean(singleHouse, CustomerVo.class);
		customer.setRealName(singleHouse.getName());
		customer.setMobile(singleHouse.getPhone());
		customer.setGlobalId(singleHouse.getCustGlobalId());
		customer.setId(singleHouse.getCustomerId());
		customer.setPortraitUrl(singleHouse.getPicUrl());

		return customer;
	}

	/**
	 * 校验业主信息
	 * 
	 * @param singleHouse
	 *            业主综合信息值对象
	 * @throws PropertyException
	 */
	private void validateProprietor(ProprietorSingleHouseVo singleHouse) throws PropertyException {
		if (ValidateHelper.isEmptyString(singleHouse.getPhone())) {
			logger.error("业主手机信息为空");
			throw new PropertyException(HouseholdErrorCode.PROPRIETOR_PHONE_NULL);
		}

		if (ValidateHelper.isEmptyString(singleHouse.getName())) {
			logger.error("业主名字信息为空");
			throw new PropertyException(HouseholdErrorCode.PROPRIETOR_NAME_NULL);
		}

		// 更新不做以下校验，交由checkService校验
		if (!ValidateHelper.isNotEmptyString(singleHouse.getProprietorId())) {
			// 校验手机号是否已被其他的客户绑定，防止出现一个手机号对应多个身份证号的情况出现
			UserVo user = userService.getUserByMobile(singleHouse.getPhone());
			if (user != null && user.getCustomer() != null) {
				if (!singleHouse.getIdNum().equals(user.getCustomer().getIdNum())) {
					logger.error("手机号已被其他客户绑定");
					throw new PropertyException(HouseholdErrorCode.PHONE_ALREADY_BINDING);
				}
			}
		}
	}

	/**
	 * 校验住户信息
	 * 
	 * @param resident
	 *            住户信息
	 * @throws PropertyException
	 */
	private void validateResident(ResidentVo resident) throws PropertyException {
//		if (ValidateHelper.isEmptyString(resident.getMobile())) {
//			logger.error("住户手机信息为空");
//			throw new PropertyException(HouseholdErrorCode.RESIDENT_PHONE_NULL);
//		}

		if (ValidateHelper.isEmptyString(resident.getName())) {
			logger.error("住户名字信息为空");
			throw new PropertyException(HouseholdErrorCode.RESIDENT_NAME_NULL);
		}

		if (ValidateHelper.isNotEmptyString(resident.getMobile())) {
			// 校验手机号是否已被其他的客户绑定，防止出现一个手机号对应多个身份证号的情况出现
			UserVo user = userService.getUserByMobile(resident.getMobile());
			if (user != null && user.getCustomer() != null) {
				if (!resident.getCustomer().getIdNum().equals(user.getCustomer().getIdNum())) {
					logger.error("手机号已被其他客户绑定");
					throw new PropertyException(HouseholdErrorCode.PHONE_ALREADY_BINDING);
				}
			}
		}
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param customer 客户信息
	 * @param householdType 账号类型
	 * @return 用户信息
	 */
	private UserVo setUpUser4Create(CustomerVo customer, String householdType) {
		String oemCode = ContextManager.getInstance().getOemCode();
		// 后台默认创建的用户信息
		UserVo user = new UserVo();
		String phone = customer.getMobile();
		String password = phone.substring(phone.length() - 6); // 初始密码，默认手机后六位数字

		user.setCustomer(customer);
		user.setCustGlobalId(customer.getGlobalId()); // 客户全局ID
		String name = phone + "@" + oemCode;
		user.setName(name); // 默认设置的用户名为手机号+@+oemCode，用户名全局唯一
		user.setPassword(password); // 初始密码
		user.setMobile(phone); // 设置手机号码
		user.setAvatarUrl(customer.getPortraitUrl());
		user.setRegisterType(UserConstants.RegisterType.PC); // 注册类型
		user.setHouseholdType(householdType); // 账号类型
		user.setOemCode(oemCode); // OEM编码
		return user;
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param customer 客户信息
	 * @param householdType 账号类型
	 * @return 用户信息
	 */
	private UserVo setUpUser4Update(CustomerVo customer, String householdType) {
		UserVo user = userService.getUserByCustGlobalId(customer.getGlobalId());
		UserVo newUser = new UserVo();
		if (user != null) {
			newUser.setCustomer(customer);
			newUser.setId(user.getId());
			newUser.setName(user.getName());
			newUser.setMobile(user.getMobile());
			newUser.setPassword(user.getPassword());
		} else {
			newUser = setUpUser4Create(customer, householdType);
		}

		return newUser;
	}

	/**
	 * 根据项目id、楼栋id，获取入住安排时间
	 */
	@Override
	public CheckinScheduleVo getCheckinSchedule(String projectId, String buildingId) {
		CheckinScheduleContext checkinScheduleContext = CheckinScheduleContext.build();
		return checkinScheduleContext.getCheckinSchedule(projectId, buildingId);
	}

	/**
	 * 根据项目id、楼栋id，手机端用户id，创建排队号
	 */
	@Override
	public CheckinQueueVo createCheckinQueue(String projectId, String houseId, String userId) throws LiefengException {
		HouseVo houseVo = projectService.findHouseById(houseId);
		String buildingId = houseVo.getBuildingId();
		CheckinScheduleVo schedule = getCheckinSchedule(projectId, buildingId);

		// 还没有安排入住办理时间
		if (schedule == null) {
			throw new PropertyException(HouseholdErrorCode.CHECKIN_SCHEDULE_INFO_NULL);
		}

		// 还没到入住办理开始时间
		Date date = new Date();
		if (date.before(schedule.getStartDate())) {
			throw new PropertyException(HouseholdErrorCode.CHECKIN_SCHEDULE_NOT_START);
		}

		CheckinQueueVo queueVo = null;
		// 查用户“已经办理”的排队
		queueVo = getCheckinQueueOfStatus(userId, projectId, houseId, HouseholdConstants.CheckinQueueStatus.FINISHED);
		if (queueVo != null) {
			if (HouseholdConstants.CheckinQueueStatus.FINISHED.equals(queueVo.getStatus())) {
				throw new PropertyException(HouseholdErrorCode.CHECKIN_QUEUE_FINISHED);
			}
		}

		// 查用户今天“在办理中”的排队
		queueVo = getCheckinQueueOfToday(userId, projectId, houseId, HouseholdConstants.CheckinQueueStatus.HANDLING,
				TimeUtil.format(date, TimeUtil.PATTERN_1));
		if (queueVo != null) {
			if (queueVo.getStatus().equals(HouseholdConstants.CheckinQueueStatus.HANDLING)) {
				throw new PropertyException(HouseholdErrorCode.CHECKIN_QUEUE_HANDLING);
			}
		}

		// 查用户今天“尚未办理”的排队
		queueVo = getCheckinQueueOfToday(userId, projectId, houseId, HouseholdConstants.CheckinQueueStatus.UNTREATED,
				TimeUtil.format(date, TimeUtil.PATTERN_1));
		if (queueVo == null) {
			CheckinQueueVo queue = new CheckinQueueVo();
			queue.setHouseId(houseId);
			queue.setProjectId(projectId);
			queue.setUserId(userId);
			List<CheckinQueueVo> queueVoList = getAllOfTody(projectId, TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			if (queueVoList == null || queueVoList.size() <= 0) {
				queue.setSeq(1);
			} else {
				queue.setSeq(queueVoList.size() + 1);
			}

			CheckinQueueContext checkinQueueContext = CheckinQueueContext.build(queue);
			queueVo = checkinQueueContext.create();
			
			//获取某个房间的所有用户的clientId
			List<String> clientIdList = listClientIdByProjectIdAndHouseNum(projectId, houseVo.getHouseNum());
			if(clientIdList != null && clientIdList.size() > 0){
				
				//还差多少人
				Integer number = 0;
				// 以小区为范围，获取最新“在办理中”的排队
				CheckinQueueVo queueHandling = getLatestOfCheckinQueue(projectId, HouseholdConstants.CheckinQueueStatus.HANDLING,
						TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
				if (queueHandling != null) {
					 number = queueVo.getSeq() - queueHandling.getSeq();
					
				} else {
					// 以小区为范围，获取最新“已办理”的排队
					CheckinQueueVo queueFinished = getLatestOfCheckinQueue(projectId, HouseholdConstants.CheckinQueueStatus.FINISHED,
							TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
					if (queueFinished != null) {
						 number = queueVo.getSeq() - queueFinished.getSeq();
					}
				}
				
				if(number < 0){
					number = 0;
				}
				
				Map<String,String> data = new HashMap<String,String>();
				data.put("queueNum", String.valueOf(queueVo.getSeq()));
				data.put("count", String.valueOf(number));
				//获取推送消息模板
				PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.CHECKIN_QUEUE_NUM, data);
				
				if(pushMsgTemplateVo != null){
					ListUserMsg message = new ListUserMsg();
					message.setAction(PushActionConstants.CHECKIN_QUEUE_NUM);
					message.setMsgCode(pushMsgTemplateVo.getMsgCode());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveClientIdList(clientIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
					logger.info("扫描排队号时群推消息{}", message);
				}
			}
		}

		return queueVo;
	}

	@Override
	public CheckinQueueVo createCheckinQueue(CheckinQueueVo checkinQueue) throws LiefengException {
		logger.info("createCheckinQueue 开始执行！参数为{}", checkinQueue);
		String projectId = checkinQueue.getProjectId();
		String hosueId = checkinQueue.getHouseId();

		HouseVo houseVo = projectService.findHouseById(hosueId);
		String buildingId = houseVo.getBuildingId();
		CheckinScheduleVo schedule = getCheckinSchedule(projectId, buildingId);

		// 校验是否有安排入住办理时间
		if (schedule == null) {
			logger.error(" ===== 没有设置入住安排时间！=====");
			throw new PropertyException(HouseholdErrorCode.CHECKIN_SCHEDULE_INFO_NULL);
		}

		// 校验是否已经到了入住办理开始时间
		Date date = new Date();
		if (date.before(schedule.getStartDate())) {
			logger.error("还没到入住安排时间！");
			throw new PropertyException(HouseholdErrorCode.CHECKIN_SCHEDULE_NOT_START);
		}

		//
		synchronized (this) {
			// 查询当前最大排队号
			List<CheckinQueueVo> queueList = getAllOfTody(projectId, TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			if (ValidateHelper.isEmptyCollection(queueList)) {
				checkinQueue.setSeq(1);
			} else {
				checkinQueue.setSeq(queueList.size() + 1);
			}

			CheckinQueueContext checkinQueueContext = CheckinQueueContext.build(checkinQueue);
			checkinQueue = checkinQueueContext.create();
		}

		logger.info("createCheckinQueue 结束执行！");
		return checkinQueue;
	}

	@Override
	public CheckinQueueVo getCheckinQueueOfToday(String userId, String projectId, String houseId, String status,
			String queryDate) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getOfToday(userId, projectId, houseId, status, queryDate);
	}

	@Override
	public List<CheckinQueueVo> getAllOfTody(String projectId, String queryDate) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getAllOfToday(projectId, queryDate);
	}

	@Override
	public CheckinQueueVo getCheckinQueue(String projectId, String houseId, String userId) throws LiefengException {
		//返回对象
		CheckinQueueVo queueReturn = new CheckinQueueVo();
		CheckinQueueVo queueVo = null;
		queueVo = getCheckinQueueOfStatus(userId, projectId, houseId, HouseholdConstants.CheckinQueueStatus.FINISHED);
		if (queueVo != null) { // 已经办理
			queueReturn.setPageStatus(HouseholdConstants.CheckinPageStatus.FINISHED);
			queueReturn.setSeq(0);
		} else {
			// 今天办理中
			CheckinQueueVo queueHandling = getCheckinQueueOfToday(userId, projectId, houseId,
					HouseholdConstants.CheckinQueueStatus.HANDLING, TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			// 今天未办理
			CheckinQueueVo queueUntreated = getCheckinQueueOfToday(userId, projectId, houseId,
					HouseholdConstants.CheckinQueueStatus.UNTREATED, TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			if (queueHandling == null && queueUntreated == null) { // 没有排号
				queueReturn.setPageStatus(HouseholdConstants.CheckinPageStatus.NONUMBER);
				queueReturn.setSeq(0);
				; // 排号为0，表示没有排号
			} else { // 有排号
				queueReturn.setPageStatus(HouseholdConstants.CheckinPageStatus.HASNUMBER);
				if (queueHandling != null) {
					queueReturn.setSeq(queueHandling.getSeq());
				} else {
					queueReturn.setSeq(queueUntreated.getSeq());
				}
			}
		}

		// 以小区为范围，获取最新“在办理中”的排队
		queueVo = getLatestOfCheckinQueue(projectId, HouseholdConstants.CheckinQueueStatus.HANDLING,
				TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
		if (queueVo != null) {
			queueReturn.setNowSeq(queueVo.getSeq());
			Integer number = queueReturn.getSeq() - queueReturn.getNowSeq();
			if (number < 0) {
				number = 0;
			}
			queueReturn.setNumber(number);
		} else {
			// 以小区为范围，获取最新“已办理”的排队
			queueVo = getLatestOfCheckinQueue(projectId, HouseholdConstants.CheckinQueueStatus.FINISHED,
					TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			if (queueVo != null) {
				queueReturn.setNowSeq(0);
				Integer number = queueReturn.getSeq() - queueVo.getSeq();
				if (number < 0) {
					number = 0;
				}
				queueReturn.setNumber(number);
			} else {
				queueReturn.setNowSeq(0);
				queueReturn.setNumber(0);
			}
		}

		return queueReturn;
	}

	@Override
	public CheckinQueueVo getCheckinQueueOfStatus(String userId, String projectId, String houseId, String status) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getOfStatus(userId, projectId, houseId, status);
	}

	@Override
	public CheckinQueueVo getLatestOfCheckinQueue(String projectId, String status, String queryDate) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getLatest(projectId, status, queryDate);
	}

	@Override
	public DataPageValue<CheckinQueueVo> getCheckinQueueOfNotStatus(String projectId, String status, String queryDate,
			Integer page, Integer size) {
		CheckinQueueContext checkinQueueContext = CheckinQueueContext.build();
		return checkinQueueContext.getNotStatus(projectId, status, queryDate, page, size);
	}

	@Override
	public void checkProrietorStatus(String proprietorId, String userId, String projectId, String houseId)
			throws LiefengException {

		// 获取用户“已办理”的排队
		CheckinQueueVo queueVo = getCheckinQueueOfStatus(userId, projectId, houseId,
				HouseholdConstants.CheckinQueueStatus.FINISHED);
		if (queueVo != null) {
			throw new PropertyException(HouseholdErrorCode.CHECKIN_PROPRIETOR_CLOSE);
		}

		// 判断是否已经过了办理时间
		HouseVo houseVo = projectService.findHouseById(houseId);
		String buildingId = houseVo.getBuildingId();
		CheckinScheduleVo schedule = getCheckinSchedule(projectId, buildingId);
		if (schedule != null) {
			Date date = TimeUtil.formatDate(new Date(), TimeUtil.PATTERN_1);
			if (date.after(schedule.getEndDate())) {
				throw new PropertyException(HouseholdErrorCode.CHECKIN_PROPRIETOR_CLOSE);
			}
		}

		ProprietorVo proprietorVo = getProprietorById(proprietorId);
		// 修改
		if (ValidateHelper.isNotEmptyString(proprietorVo.getEmergencyContact())
				|| ValidateHelper.isNotEmptyString(proprietorVo.getEmergencyPhone())) {
			throw new PropertyException(HouseholdErrorCode.CHECKIN_PROPRIETOR_MODIFY);
		} else { // 登记
			throw new PropertyException(HouseholdErrorCode.CHECKIN_PROPRIETOR_CHECK);
		}
	}

	@Override
	public void registerProprietor(ProprietorSingleHouseVo singleHouse) throws LiefengException {
		// 更新业主信息
		ProprietorVo proprietor = MyBeanUtil.createBean(singleHouse, ProprietorVo.class);
		proprietor.setId(singleHouse.getProprietorId());
		proprietor.setRegisterTime(new Date());
		proprietor.setStatus(HouseholdConstants.ProprietorStatus.ACTIVE);
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		ProprietorVo proprietorVo = proprietorContext.update();

		CustomerVo customer = userService.getCustomerByGlobalId(proprietorVo.getCustGlobalId());
		customer.setSex(singleHouse.getSex());
		customer = checkService.updateCustomerCheck(customer);
		// 发送TCC消息，更新用户信息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_CUSTOMER, customer.toString());
	}

	@Override
	public ProprietorSingleHouseVo getProprietorOfRegister(String proprietorId) {
		// 业主
		ProprietorVo proprietorVo = getProprietorById(proprietorId);
		ProprietorSingleHouseVo singleHouseVo = new ProprietorSingleHouseVo();

		// copy
		MyBeanUtil.copyBeanNotNull2Bean(proprietorVo, singleHouseVo);
		singleHouseVo.setProprietorId(proprietorId);
		// 用户信息
		CustomerVo customer = userService.getCustomerByGlobalId(proprietorVo.getCustGlobalId());
		singleHouseVo.setSex(customer.getSex());

		return singleHouseVo;
	}

	@Override
	public ResidentFeedbackVo createResidentFeedback(ResidentFeedbackVo residentFeedbackVo) {
		ResidentFeedbackContext residentFeedbackContext = ResidentFeedbackContext.build(residentFeedbackVo);
		return residentFeedbackContext.create();
	}

	@Override
	public AppMsgSettingVo createAppMsgSetting(AppMsgSettingVo appMsgSettingVo) {
		AppMsgSettingContext appMsgSettingContext = AppMsgSettingContext.build(appMsgSettingVo);
		return appMsgSettingContext.create();
	}

	@Override
	public AppMsgSettingVo getAppMsgSetting(String userId) {
		AppMsgSettingContext appMsgSettingContext = AppMsgSettingContext.loadByUserId(userId);
		return appMsgSettingContext.get();
	}

	@Override
	public AppMsgSettingVo updateAppMsgSetting(AppMsgSettingVo appMsgSettingVo) {
		AppMsgSettingContext appMsgSettingContext = AppMsgSettingContext.build(appMsgSettingVo);
		return appMsgSettingContext.update();
	}

	@Override
	public DataPageValue<ResidentFeedbackVo> getResidentFeedbackPage(ResidentFeedbackBo params, Integer currentPage,
			Integer pageSize) {
		ResidentFeedbackContext residentFeedbackContext = ResidentFeedbackContext.build();
		return residentFeedbackContext.getResidentFeedPage(params, currentPage, pageSize);
	}

	@Override
	public AppFriendVo createAppFriend(AppFriendVo appFriendVo) {
		// 删除“已拒绝”好友申请记录
		AppFriendContext context = AppFriendContext.build();
		context.deleteOfStatus(appFriendVo.getUserId(), appFriendVo.getFriendId(),
				HouseholdConstants.AppFriendStatus.REFUSE);

		// 判断是否有“已添加”的记录
		AppFriendVo appFriend = context.getAppFriend(appFriendVo.getFriendId(), appFriendVo.getUserId(),
				HouseholdConstants.AppFriendStatus.ASFRIEND);
		if (appFriend == null) { // 创建一个“待审核”的记录
			AppFriendContext appFriendContext = AppFriendContext.build(appFriendVo);
			appFriend = appFriendContext.create();
		} else { // 创建一个“已成为好友”的记录
			appFriendVo.setStatus(HouseholdConstants.AppFriendStatus.ASFRIEND);
			appFriendVo.setUpdateTime(new Date());

			AppFriendContext appFriendContext = AppFriendContext.build(appFriendVo);
			appFriend = appFriendContext.create();
		}

		return appFriend;
	}

	@Override
	public void deleteAppFriend(String userId, String friendId) {
		AppFriendContext appFriendContext = AppFriendContext.build();
		appFriendContext.delete(userId, friendId);
	}

	@Override
	public List<AppFriendVo> getAppFriendList(String userId) {
		AppFriendContext appFriendContext = AppFriendContext.build();
		return appFriendContext.getAppFriendList(userId);
	}

	@Override
	public void updateAppFriend(String id, String status) {

		AppFriendVo appFriendVo = new AppFriendVo();
		appFriendVo.setId(id);
		appFriendVo.setStatus(status);

		AppFriendContext appFriendContext = AppFriendContext.build(appFriendVo);
		// 更新
		appFriendVo = appFriendContext.update();

		// 同意成为好友
		if (HouseholdConstants.AppFriendStatus.ASFRIEND.equals(status)) {
			// 创建第二条好友
			AppFriendVo appFriend = new AppFriendVo();
			appFriend.setFriendId(appFriendVo.getUserId());
			appFriend.setUserId(appFriendVo.getFriendId());
			appFriend.setStatus(HouseholdConstants.AppFriendStatus.ASFRIEND);
			appFriend.setCreateTime(new Date());
			appFriend.setUpdateTime(new Date());

			AppFriendContext friendContext = AppFriendContext.build(appFriend);
			friendContext.create();
		}

	}

	@Override
	public List<AppFriendVo> getUserList(String userId, String condition) {
		AppFriendContext appFriendContext = AppFriendContext.build();
		return appFriendContext.getUserList(userId, condition);
	}

	@Override
	public List<AppFriendVo> getAppFriendHistoryList(String userId) {
		AppFriendContext appFriendContext = AppFriendContext.build();
		return appFriendContext.getAppFriendHistoryList(userId);
	}

	@Override
	public AppFriendVo getAppFriend(String userId, String friendId, String status) {
		AppFriendContext appFriendContext = AppFriendContext.build();
		return appFriendContext.getAppFriend(userId, friendId, status);
	}

	@Override
	public ResidentHouseVo getResidentHouse(String residentId, String houseId) {
		ResidentHouseContext residentHouseContext = ResidentHouseContext.build();
		return residentHouseContext.getResidentHouse(residentId, houseId);
	}

	@Override
	public ProprietorVo findProprietor(String projectId, String proprietorName) {
		return ProprietorContext.build().findByProjectIdAndName(projectId, proprietorName);
	}

	@Override
	public void createResidentCar(ResidentCarVo residentCarVo) {
		ResidentCarVo carVo = ResidentCarContext.build().findByPlateNum(residentCarVo.getPlateNum());
		if (carVo == null) {
			ResidentCarContext.build(residentCarVo).create();
		}
	}

	@Override
	public void updateResidentCar(ResidentCarVo residentCarVo) {
		ResidentCarContext.build(residentCarVo).update();
	}

	@Override
	public VisitorVo createVisitor(VisitorVo visitorVo) {
		VisitorContext visitorContext = VisitorContext.build(visitorVo);
		return visitorContext.create();
	}

	@Override
	public List<VisitorVo> getVisitorList(String userId) {
		VisitorContext visitorContext = VisitorContext.build();
		return visitorContext.getVisitorList(userId);
	}

	@Override
	public List<VisitorVo> getVisitorHistory(String phone) {
		VisitorContext visitorContext = VisitorContext.build();
		return visitorContext.getVisitor(phone);
	}

	@Override
	public VisitorVo getVisitor(String visitorId) {
		VisitorContext visitorContext = VisitorContext.loadById(visitorId);
		return visitorContext.getVisitor();
	}

	@Override
	public List<ResidentCarVo> findResidentCarByPakingId(String pakingId) {
		return ResidentCarContext.build().findResidentCarByPakingId(pakingId);
	}

	@Override
	public List<ResidentCarVo> findResidentCarByHouseId(String houseId) {
		return ResidentCarContext.build().findResidentCarByHouseId(houseId);
	}

	@Override
	public ResidentCarVo findResidentCarById(String carId) {
		ResidentCarContext residentCarContext = ResidentCarContext.loadById(carId);
		return residentCarContext.get();
	}

	@Override
	public List<String> listClientIdByBuildingIdAndProjectId(
			String buildingId, String projectId) {
		ProprietorContext proprietorContext = ProprietorContext.build();
		return proprietorContext.listClientIdByBuildingIdAndProjectId(buildingId, projectId);
	}

	@Override
	public List<String> listClientIdByProjectId(
			String projectId) {
		return ProprietorContext.build().listClientIdByProjectId(projectId);
	}

	@Override
	public void pushMsgToUserByPhone(String phone) {
		//获取推送消息模板
		PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.CHANGE_PWD_SUCCESS);
		
		if(pushMsgTemplateVo != null){
			//个推给业主、住户
			UserVo userVo = userService.getUserByMobile(phone);
			
			if(userVo != null){
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(PushActionConstants.CHANGE_PWD_SUCCESS);
				message.setMsgCode(pushMsgTemplateVo.getMsgCode());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(userVo.getId());
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_PROPRIETOR, PushMsgConstants.TerminalType.MOBILE_PROPERTY, message);
				logger.info("修改密码时单推消息{}", message);
			}
			
		}
		
	}

	@Override
	public List<String> listClientIdByProjectIdAndHouseNum(String projectId,
			String houseNum) {
		return ProprietorContext.build().listClientIdByProjectIdAndHouseNum(projectId, houseNum);
	}

	@Override
	public CheckinQueueVo getCheckinQueueMoreThanSeq(String projectId,
			String status, Integer seq, String queryDate) {
		return CheckinQueueContext.build().getLatestOfStatusAndSeq(projectId, status, seq, queryDate);
	}

	@Override
	public ProprietorSingleHouseVo findProprietorSingleHouseVo(
			String projectId, String houseNum) {
		return ProprietorContext.build().findProprietorSingleHouseVo(projectId, houseNum);
	}

	@Override
	public DataPageValue<VisitorVo> findVisitorByPage(String projectId, String manageProjectIds,
			String name, String phone, Integer page, Integer size) {
		return VisitorContext.build().findByPage(projectId, manageProjectIds, name, phone, page, size);
	}

	@Override
	public VisitorVo updateVisitor(VisitorVo visitorVo) {
		return VisitorContext.build(visitorVo).update();
	}
		
}
