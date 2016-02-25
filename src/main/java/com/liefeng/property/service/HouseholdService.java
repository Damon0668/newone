package com.liefeng.property.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.base.ICheckService;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.intf.service.tcc.ITccMsgService;
import com.liefeng.mq.type.TccBasicEvent;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.domain.household.CheckinMaterialContext;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.vo.household.CheckinMaterialVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentVo;

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

	/**
	 * 保存业主信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void saveProprietor(ProprietorSingleHouseVo singleHouse) throws Exception {
		
		CustomerVo customer = initCustomer(singleHouse);
		customer = checkService.createCustomerCheck(customer);
		singleHouse.setCustGlobalId(customer.getGlobalId());
		
		
		/**
		 * 业主信息保存
		 */
		ProprietorVo proprietor = MyBeanUtil.createBean(singleHouse, ProprietorVo.class);
		
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		proprietor = proprietorContext.create();
		

		/**
		 * 业主房产信息保存
		 */
		ProprietorHouseVo proprietorHouse = MyBeanUtil.createBean(singleHouse, ProprietorHouseVo.class);
		
		proprietorHouse.setProprietorId(proprietor.getId());
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
		proprietorHouseContext.create();
		
		// 发送Tcc消息，保存客户信息
		tccMsgService.sendTccMsg(TccBasicEvent.CREATE_CUSTOMER, customer.toString());
		
	}
	
	/**
	 * 更新业主信息
	 */
	@Override
	@Transactional(rollbackOn=Exception.class)
	public void updatePropritor(ProprietorSingleHouseVo singleHouse) throws Exception  {
		
		CustomerVo customer = initCustomer(singleHouse);
		customer = checkService.updateCustomerCheck(customer);
		
		/**
		 * 业主信息更新
		 */
		ProprietorVo proprietor = MyBeanUtil.createBean(singleHouse, ProprietorVo.class);
		proprietor.setId(singleHouse.getProprietorId());
		
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		proprietorContext.update();

		/**
		 * 业主房产信息更新
		 */
		ProprietorHouseVo proprietorHouse = MyBeanUtil.createBean(singleHouse, ProprietorHouseVo.class);
		proprietorHouse.setId(singleHouse.getProprietorHouseId());
		
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
		proprietorHouseContext.update();
		
		// 发送Tcc消息，保存客户信息
		tccMsgService.sendTccMsg(TccBasicEvent.UPDATE_CUSTOMER, customer.toString());
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
		
		return customer;
	}

	/**
	 * 保存住户信息
	 */
	@Override
	public void saveResident(ResidentVo resident) {
		ResidentContext residentContext = ResidentContext.build(resident);
		resident = residentContext.create();
	}
	
	/**
	 * 更新住户信息
	 */
	@Override
	public void updateResident(ResidentVo resident) {
		ResidentContext residentContext = ResidentContext.build(resident);
		resident = residentContext.update();
	}

	/**
	 * 分页查询业主综合信息
	 */
	@Override
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		ProprietorContext proprietorContext = ProprietorContext.build();

		return proprietorContext.listProprietorSingleHouse4Page(params, pageSize, currentPage);
	}

	/**
	 * 获取业主某个房产的信息
	 */
	@Override
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		logger.info("业主房产ID：proprietorHouseId=" + proprietorHouseId);

		ProprietorContext proprietorContext = ProprietorContext.build();

		return proprietorContext.getProprietorSingleHouse(proprietorHouseId);
	}

	/**
	 * 分页查询住户信息
	 */
	@Override
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		ResidentContext residentContext = ResidentContext.build();
		
		return residentContext.listResident4Page(params, pageSize, currentPage);
	}

	/**
	 * 查询住户信息
	 */
	@Override
	public ResidentVo getResident(String residentId) {
		ResidentContext residentContext = ResidentContext.loadById(residentId);
		return residentContext.getResident();
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
	 * 根据业主房产ID删除入住资料信息
	 */
	@Override
	public void deleteByProprietorHouseId(String proprietorHouseId) {
		CheckinMaterialContext checkinMaterialContext = CheckinMaterialContext.loadByProprietorHouseId(proprietorHouseId);
		
		checkinMaterialContext.delete();
	}
}
