package com.liefeng.property.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.domain.fee.FeeSettingContext;
import com.liefeng.property.domain.fee.LadderFeeSettingContext;
import com.liefeng.property.domain.fee.MeterRecordContext;
import com.liefeng.property.domain.fee.MeterSettingContext;
import com.liefeng.property.vo.fee.FeeSettingVo;
import com.liefeng.property.vo.fee.LadderFeeSettingVo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;

/**
 * 
 * <pre>
 * Title:抄表费用相关表接口类
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing
 * @version 1.0      
 * @created 2016年2月17日下午6:50:00
 * </pre>
 */
@Service
public class FeeService implements IFeeService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(FeeService.class);

	@Override
	public void createMeterRecord(MeterRecordVo meterRecordVo) {

		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		meterRecordContext.create();
	}

	@Override
	public DataPageValue<MeterRecordVo> findMeterRecord4Page(MeterRecordVo meterRecordVo,Integer currentPage,Integer pageSize) {
		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		return meterRecordContext.listMeterRecordVo4Page(currentPage, pageSize);
	}
	// TODO 仪表设置
	@Override
	public void createMeterSetting(MeterSettingVo meterSettingVo)
			throws LiefengException {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.build(meterSettingVo);
		meterSettingContext.create();
	}

	@Override
	public void deleteMeterSetting(String id) throws LiefengException {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.loadById(id);
		meterSettingContext.delete();
	}

	@Override
	public DataPageValue<MeterSettingVo> findMeterSetting4Page(
			String projectId, Integer pageSize, Integer currentPage) {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.loadByProjectId(projectId);
		return meterSettingContext.findByProjectId(pageSize, currentPage);
	}

	@Override
	public MeterSettingVo findMeterSettingById(String id) {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.loadById(id);
		return meterSettingContext.findById();
	}

	@Override
	public void updateMeterSetting(MeterSettingVo meterSettingVo)
			throws LiefengException {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.build(meterSettingVo);
		meterSettingContext.update();
	}

	//TODO 费用设置
	@Override
	public void createFeeSetting(FeeSettingVo feeSettingVo)
			throws LiefengException {
		FeeSettingContext feeSettingContext =FeeSettingContext
				.build(feeSettingVo);
		feeSettingContext.create();
	}

	@Override
	public void deleteFeeSetting(String id) throws LiefengException {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadById(id);
		feeSettingContext.delete();
	}

	@Override
	public DataPageValue<FeeSettingVo> findFeeSetting4Page(
			String projectId, Integer currentPage,Integer pageSize) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(projectId);
		return feeSettingContext.findByProjectId4Page(currentPage,pageSize);
	}

	@Override
	public FeeSettingVo findFeeSettingById(String id) {
		FeeSettingContext feeSettingContext = FeeSettingContext.loadById(id);
		return feeSettingContext.findById();
	}

	@Override
	public void updateFeeSetting(FeeSettingVo feeSettingVo)
			throws LiefengException {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.build(feeSettingVo);
		feeSettingContext.update();
	}

	//TODO 阶梯收费设置
	@Override
	public void createLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException {
		LadderFeeSettingContext ladderFeeSettingContext =LadderFeeSettingContext
				.build(ladderFeeSettingVo);
		ladderFeeSettingContext.create();
	}

	@Override
	public void deleteLadderFeeSetting(String id) throws LiefengException {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
				.loadById(id);
		ladderFeeSettingContext.delete();
	}

	@Override
	public DataPageValue<LadderFeeSettingVo> findLadderFeeSetting4Page(
			String projectId, Integer currentPage, Integer pageSize) {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
				.loadByProjectId(projectId);
		return ladderFeeSettingContext.findByProjectId4Page(currentPage,pageSize);
	}

	@Override
	public LadderFeeSettingVo findLadderFeeSettingById(String id) {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext.loadById(id);
		return ladderFeeSettingContext.findById();
	}

	@Override
	public void updateLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
				.build(ladderFeeSettingVo);
		ladderFeeSettingContext.update();
	}
	
}
