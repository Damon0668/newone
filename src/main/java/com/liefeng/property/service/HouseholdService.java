package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.api.property.IHouseholdService;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.mybatis.vo.PagingParamVo;
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
	@Transactional
	public void saveResident(ResidentVo resident) {
		ResidentContext residentContext = ResidentContext.build(resident);
		resident = residentContext.create();
	}

	/**
	 * 分页查询业主信息
	 */
	@Override
	public DataPageValue<ProprietorSingleHouseVo> listProprietor4Page(Map<String, String> params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(params);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		ProprietorContext proprietorContext = ProprietorContext.build(new ProprietorVo());

		Integer count = proprietorContext.queryByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<ProprietorSingleHouseVo> proprietorList = proprietorContext.queryByPage(pagingParamVo);
		proprietorList = (ValidateHelper.isEmptyCollection(proprietorList) ? 
				new ArrayList<ProprietorSingleHouseVo>() : proprietorList);

		DataPageValue<ProprietorSingleHouseVo> proprietorPage = new DataPageValue<ProprietorSingleHouseVo>(
				proprietorList, count, pageSize, currentPage);

		return proprietorPage;
	}

	/**
	 * 获取业主某个房产的信息
	 */
	@Override
	public ProprietorSingleHouseVo getProprietorSingleHouse(String proprietorHouseId) {
		logger.info("业主房产ID：proprietorHouseId=" + proprietorHouseId);

		PagingParamVo pagingParamVo = new PagingParamVo();
		Map<String, String> params = new HashMap<String, String>();
		params.put("proprietorHouseId", proprietorHouseId);
		pagingParamVo.setExtra(params);

		ProprietorContext proprietorContext = ProprietorContext.build(new ProprietorVo());
		ProprietorSingleHouseVo proprietorSingleHouse = proprietorContext.getProprietorSingleHouse(pagingParamVo);

		return proprietorSingleHouse;
	}

	/**
	 * 分页查询住户信息
	 */
	@Override
	public DataPageValue<ResidentVo> listResident4Page(Map<String, String> params, Integer pageSize,
			Integer currentPage) {
		logger.info("查询过滤条件 params=" + params + ", pageSize=" + pageSize + ", currentPage=" + currentPage);

		PagingParamVo pagingParamVo = new PagingParamVo();
		pagingParamVo.setExtra(params);
		pagingParamVo.setRows(pageSize);
		pagingParamVo.setPage(currentPage);

		ResidentContext residentContext = ResidentContext.build(new ResidentVo());

		Integer count = residentContext.queryByCount(pagingParamVo);
		count = (count == null ? 0 : count);
		logger.info("总数量：count=" + count);
		
		// 设置数据总行数，用于计算偏移量
		pagingParamVo.getPager().setRowCount(count);
		List<ResidentVo> residentVoList = residentContext.queryByPage(pagingParamVo);
		residentVoList = (ValidateHelper.isEmptyCollection(residentVoList) ? 
				new ArrayList<ResidentVo>() : residentVoList);

		DataPageValue<ResidentVo> residentPage = new DataPageValue<ResidentVo>(
				residentVoList, count, pageSize, currentPage);

		return residentPage;
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
