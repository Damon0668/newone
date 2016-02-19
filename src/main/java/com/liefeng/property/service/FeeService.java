package com.liefeng.property.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.domain.fee.MeterRecordContext;
import com.liefeng.property.domain.fee.MeterSettingContext;
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
public class FeeService implements IFeeService{

	private static Logger logger = LoggerFactory.getLogger(FeeService.class);

	@Override
	public void saveMeterRecord(MeterRecordVo meterRecordVo){

		MeterRecordContext meterRecordContext = MeterRecordContext.build(meterRecordVo);
		meterRecordContext.create();
	}

	@Override
	public void listMeterRecord(MeterRecordVo meterRecordVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMeterSetting(MeterSettingVo meterSettingVo) {
		MeterSettingContext meterSettingContext=MeterSettingContext.build(meterSettingVo);
		meterSettingContext.save();
	}
	
	@Override
	public void deleteMeterSetting(String id){
		MeterSettingContext meterSettingContext=MeterSettingContext.loadById(id);
		meterSettingContext.delete();
	}

	@Override
	public DataPageValue<MeterSettingVo> findMeterSetting4Page(String projectId,Integer pageSize,Integer currentPage){
		MeterSettingContext meterSettingContext=MeterSettingContext.loadByProjectId(projectId);
		return meterSettingContext.findByProjectId(pageSize, currentPage);
	}

	@Override
	public MeterSettingVo findMeterSettingById(String id) {
		MeterSettingContext meterSettingContext=MeterSettingContext.loadById(id);
		return meterSettingContext.findById();
	}

	@Override
	public void updateMeterSetting(MeterSettingVo meterSettingVo) {
		MeterSettingContext meterSettingContext=MeterSettingContext.build(meterSettingVo);
		meterSettingContext.update();
	}

}
