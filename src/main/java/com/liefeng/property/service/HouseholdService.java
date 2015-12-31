package com.liefeng.property.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.api.property.IHouseholdService;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.property.bo.household.ProprietorBo;
import com.liefeng.property.bo.household.ResidentBo;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentContext;
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

	/**
	 * 保存业主信息
	 */
	@Override
	@Transactional
	public void saveProprietor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse) {
		/**
		 * 业主信息保存
		 */
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		proprietor = proprietorContext.create();

		/**
		 * 业主房产信息保存
		 */
		proprietorHouse.setProprietorId(proprietor.getId());
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
		proprietorHouseContext.create();
	}

	/**
	 * 更新业主信息
	 */
	@Override
	@Transactional
	public void updatePropritor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse) {
		/**
		 * 业主信息更新
		 */
		ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
		proprietorContext.update();

		/**
		 * 业主房产信息更新
		 */
		ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
		proprietorHouseContext.update();
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
	 * 分页查询业主综合信息
	 */
	@Override
	public DataPageValue<ProprietorSingleHouseVo> listProprietorSingleHouse4Page(ProprietorBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		ProprietorContext proprietorContext = ProprietorContext.getInstance();

		return proprietorContext.listProprietorSingleHouse4Page(params, pageSize, currentPage);
	}

	/**
	 * 获取业主某个房产的信息
	 */
	@Override
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		logger.info("业主房产ID：proprietorHouseId=" + proprietorHouseId);

		ProprietorContext proprietorContext = ProprietorContext.getInstance();

		return proprietorContext.getProprietorSingleHouse(proprietorHouseId);
	}

	/**
	 * 分页查询住户信息
	 */
	@Override
	public DataPageValue<ResidentVo> listResident4Page(ResidentBo params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		ResidentContext residentContext = ResidentContext.getInstance();
		
		return residentContext.listResident4Page(params, pageSize, currentPage);
	}

	/**
	 * 查询住户信息
	 */
	@Override
	public ResidentVo getResident(String residentId) {
		ResidentContext residentContext = ResidentContext.loadById(residentId);
		return residentContext.queryResidentById();
	}

}
