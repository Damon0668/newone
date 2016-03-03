package com.liefeng.property.service;

import java.util.List;

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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.base.user.IUserService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.constant.ProjectConstants;
import com.liefeng.property.domain.household.CheckinMaterialContext;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.error.HouseholdErrorCode;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.household.CheckinMaterialVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentVo;
import com.liefeng.property.vo.project.HouseVo;

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

	/**
	 * 保存业主信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
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
			ProprietorContext proprietorContext = null;
			proprietorContext = ProprietorContext.build();
			ProprietorVo proprietor = proprietorContext.get(singleHouse.getProjectId(), customer.getGlobalId());
			
			// 同个OEM下，同个小区保留一份业主信息，不同小区保留多份业主信息
			if( proprietor == null) { 
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
			if(!isExit) {
				// 后台默认创建的手机用户信息
				UserVo user = setUpUser4Create(customer, UserConstants.HouseholdType.PROPRIETOR);
				
				try { 
					// 用户信息校验
					user = checkService.createUserCheck(user);
				} catch (UserException e) {
					logger.error("创建用户出现异常，异常编码为({})，异常信息为({})", e.getCode(), e.getMessage());
					return;
				}
				
				// 发送TCC消息，创建用户（内含创建客户或更新客户逻辑）
				tccMsgService.sendTccMsg(TccBasicEvent.CREATE_USER, user.toString());
			}
			
		} catch (LiefengException e) {
			logger.error("保存业主信息出现异常,异常码（{}）,异常信息（{}）。", e.getCode() , e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}

	/**
	 * 更新业主信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void updatePropritor(ProprietorSingleHouseVo singleHouse) throws LiefengException  {
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
			UserVo newUser = setUpUser4Update(customer); 
			
			// 校验用户信息
			UserVo user = checkService.updateUserCheck(newUser);
			
			// 发送TCC消息，更新用户信息
			tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
		} catch (LiefengException e) {
			logger.error("更新业主信息出现异常,异常码（{}）,异常信息（{}）", e.getCode() , e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}
	
	/**
	 * 保存住户信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
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
			HouseContext houseContext = HouseContext.loadById(resident.getHouseId());
			HouseVo house = houseContext.get();
			ResidentContext residentContext = ResidentContext.build();
			ResidentVo existedResident = residentContext.get(house.getProjectId(), customer.getGlobalId());
			
			// 同个OEM下，同个小区只保存一份住户信息，不同小区保存多份住户信息
			if(existedResident == null) { 
				resident.setStatus(HouseholdConstants.ResidentStatus.ACTIVE); // 默认为激活状态
				resident.setCustGlobalId(customer.getGlobalId());
				residentContext = ResidentContext.build(resident);
				residentContext.create();
			} else {
				
				isExit = true; // 同个OEM下，同个小区住户信息已存在
				logger.info("住户信息已存在，后续将不执行住户用户创建动作");
			}
			
			// 仅当同个OEM下，同个小区下住户不存在时，才做用户创建
			// 住户信息存在时，即使传过来的手机号不同也不做用户创建，为了保证同个OEM下，同个小区住户用户信息只有一份
			if(isExit) {
				// 后台默认创建的手机用户信息
				UserVo user = setUpUser4Create(customer, UserConstants.HouseholdType.RESIDENT);
				
				try { 
					// 用户信息校验
					user = checkService.createUserCheck(user);
				} catch (UserException e) { // 仅仅会捕获到用户存在的异常
					logger.error("用户创建校验出现异常，异常编码为({})，异常信息为({})", e.getCode(), e.getMessage());
					return;
				}
				
				// 发送TCC消息，创建用户（内含创建客户或更新客户逻辑）
				tccMsgService.sendTccMsg(TccBasicEvent.CREATE_USER, user.toString());
			}
		} catch (LiefengException e) {
			logger.error("保存住户信息出现异常,异常码（{}）,异常信息（{}）", e.getCode() , e.getMessage());
			throw new LiefengException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			throw new LiefengException(e);
		}
	}

	/**
	 * 更新住户信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
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
			
			// 用户更新信息设置
			UserVo newUser = setUpUser4Update(customer); 
			
			// 校验用户信息
			UserVo user = checkService.updateUserCheck(newUser);
			
			// 发送Tcc消息，更新用户信息
			tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_USER, user.toString());
		} catch (LiefengException e) {
			logger.error("更新住户信息出现异常,异常码（{}）,异常信息（{}）", e.getCode() , e.getMessage());
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
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params={}, pageSize={}, currentPage={}", params, pageSize, currentPage);

		ResidentContext residentContext = ResidentContext.build();
		
		return residentContext.listResident4Page(params, pageSize, currentPage);
	}

	/**
	 * 查询住户信息
	 */
	@Override
	public ResidentVo getResident(String residentId) {
		ResidentContext residentContext = ResidentContext.loadById(residentId);
		return residentContext.get();
	}

	/**
	 * 根据项目ID和房号查询业主房产
	 */
	@Override
	public ProprietorHouseVo getProprietorHouse(String projectId, String houseNum) {
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.loadByProjectIdAndHouseNum(projectId, houseNum);
		
		return proprietorHouseContext.getProprietorHouse();
	}

	/**
	 * 根据业主房产ID查询入住资料信息
	 */
	@Override
	public List<CheckinMaterialVo> getCheckinMaterialByProprietorHouseId(String proprietorHouseId) {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext.loadByProprietorHouseId(proprietorHouseId);
		
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
	public void delCheckinMaterialByProprietorHouseId(String proprietorHouseId) throws Exception {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext.loadByProprietorHouseId(proprietorHouseId);
		
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
	public DataPageValue<UserVo> listProprietorUser(ProprietorBo params, Integer currentPage, Integer pageSize) {
		ProprietorContext proprietorContext = ProprietorContext.build();
		
		return proprietorContext.listProprietorUser(params, currentPage, pageSize);
	}
	
	/**
	 * 获取业主所有房产中的住户
	 */
	@Override
	public List<ResidentVo> getResidentsInProprietorHouse(String projectId, String custGlobalId) {
		ResidentContext residentContext = ResidentContext.build();
		return residentContext.getResidentsInProprietorHouse(projectId, custGlobalId);
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
	 * @param singleHouse 业主综合信息值对象
	 * @throws PropertyException
	 */
	private void validateProprietor(ProprietorSingleHouseVo singleHouse) throws PropertyException {
		if(ValidateHelper.isEmptyString(singleHouse.getPhone())) {
			logger.error("业主手机信息为空");
			throw new PropertyException(HouseholdErrorCode.PROPRIETOR_PHONE_NULL);
		}
		
		if(ValidateHelper.isEmptyString(singleHouse.getName())) {
			logger.error("业主名字信息为空");
			throw new PropertyException(HouseholdErrorCode.PROPRIETOR_NAME_NULL);
		}	
		
		// 更新不做以下校验，交由checkService校验
		if(!ValidateHelper.isNotEmptyString(singleHouse.getProprietorId())) {
			// 校验手机号是否已被其他的客户绑定，防止出现一个手机号对应多个身份证号的情况出现
			UserVo user = userService.getUserByMobile(singleHouse.getPhone());
			if(user != null && user.getCustomer() != null) {
				if(!singleHouse.getIdNum().equals(user.getCustomer().getIdNum())) {
					logger.error("手机号已被其他客户绑定");
					throw new PropertyException(HouseholdErrorCode.PHONE_ALREADY_BINDING);
				}
			}
		}
	}
	
	/**
	 * 校验住户信息
	 * @param resident 住户信息
	 * @throws PropertyException
	 */
	private void validateResident(ResidentVo resident) throws PropertyException {
		if(ValidateHelper.isEmptyString(resident.getMobile())) {
			logger.error("住户手机信息为空");
			throw new PropertyException(HouseholdErrorCode.RESIDENT_PHONE_NULL);
		}
		
		if(ValidateHelper.isEmptyString(resident.getName())) {
			logger.error("住户名字信息为空");
			throw new PropertyException(HouseholdErrorCode.RESIDENT_NAME_NULL);
		}	
		
		// 更新不做以下校验，交由checkService校验
		if(!ValidateHelper.isNotEmptyString(resident.getId())) {
			// 校验手机号是否已被其他的客户绑定，防止出现一个手机号对应多个身份证号的情况出现
			UserVo user = userService.getUserByMobile(resident.getMobile());
			if(user != null && user.getCustomer() != null) {
				if(!resident.getCustomer().getIdNum().equals(user.getCustomer().getIdNum())) {
					logger.error("手机号已被其他客户绑定");
					throw new PropertyException(HouseholdErrorCode.PHONE_ALREADY_BINDING);
				}
			}
		}
	}
	
	/**
	 * 初始化用户信息
	 * @param customer 客户信息
	 * @param householdType 账号类型
	 * @return 用户信息
	 */
	private UserVo setUpUser4Create(CustomerVo customer , String householdType) {
		// 后台默认创建的用户信息
		UserVo user = new UserVo();
		String phone = customer.getMobile();
		String password = phone.substring(phone.length() - 6); // 初始密码，默认手机后六位数字
		
		user.setCustomer(customer);
		user.setCustGlobalId(customer.getGlobalId()); // 客户全局ID
		user.setName(phone); // 账号
		user.setPassword(password); // 初始密码
		user.setMobile(phone); // 设置手机号码
		user.setAvatarUrl(customer.getPortraitUrl());
		user.setRegisterType(UserConstants.RegisterType.PC); // 注册类型
		user.setHouseholdType(householdType); // 账号类型
		user.setOemCode(ContextManager.getInstance().getOemCode()); // OEM编码
		return user;
	}
	
	/**
	 * 初始化用户信息
	 * @param customer 客户信息
	 * @return 用户信息
	 */
	private UserVo setUpUser4Update(CustomerVo customer) {
		UserVo user = userService.getUserByCustGlobalId(customer.getGlobalId());
		UserVo newUser = new UserVo();
		newUser.setCustomer(customer);
		if(user != null) {
			newUser.setId(user.getId());
			newUser.setPassword(user.getPassword());
		}
		
		return newUser;
	}

}
