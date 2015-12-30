package com.liefeng.property.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.api.property.IHouseholdService;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.repository.mybatis.ProprietorQueryRepository;
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
	private ProprietorQueryRepository proprietorQueryRepository;

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
	@Transactional
	public void saveResident(ResidentVo resident) {
		ResidentContext residentContext = ResidentContext.build(resident);
		residentContext.create();
	}

	/**
	 * 分页查询业主信息
	 */
	@Override
	public DataPageValue<ProprietorSingleHouseVo> listProprietor4Page(Map<String, String> params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		PagingParamVo paramVo = new PagingParamVo();
		paramVo.setExtra(params);
		paramVo.setRows(pageSize);
		paramVo.setPage(currentPage);

		Integer count = proprietorQueryRepository.queryByCount(paramVo);
		List<ProprietorSingleHouseVo> proprietorList = proprietorQueryRepository.queryByPage(paramVo);

		DataPageValue<ProprietorSingleHouseVo> proprietorPage = new DataPageValue<ProprietorSingleHouseVo>(
				proprietorList, count, pageSize, currentPage);

		return proprietorPage;
	}

	/**
	 * 获取业主某个房产的信息
	 */
	@Override
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		logger.info("业主房产ID：proprietorHouseId="+proprietorHouseId);
		
		PagingParamVo paramVo = new PagingParamVo();
		Map<String, String> params = new HashMap<String, String>();
		params.put("proprietorHouseId", proprietorHouseId);
		paramVo.setExtra(params);
		
		ProprietorSingleHouseVo proprietorSingleHouse = proprietorQueryRepository.getProprietorSingleHouse(paramVo);
		
		return proprietorSingleHouse;
	}
	
}
