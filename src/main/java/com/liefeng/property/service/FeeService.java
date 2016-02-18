package com.liefeng.property.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.domain.fee.MeterRecordContext;
import com.liefeng.property.domain.fee.MeterSettingContext;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;

/**
 * 
 * <pre>      
 * Title:抄表费用相关表接口类
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  吴志敬        
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
	public DataPageValue<MeterSettingPo> findMeterSetting4Page(String projectId,Integer pageSize,Integer currentPage){
		MeterSettingContext meterSettingContext=MeterSettingContext.build();
		return meterSettingContext.findByProjectId(projectId, pageSize, currentPage);
	}

	@Override
	public MeterRecordVo findMeterRecordById(String id) {
		MeterSettingContext meterSettingContext=MeterSettingContext.build();
		return meterSettingContext.findMeterSettingById(id);
	}

	@Override
	public void updateMeterRecord(MeterSettingVo meterSettingVo) {
		MeterSettingContext meterSettingContext=MeterSettingContext.build(meterSettingVo);
		meterSettingContext.updateMeterSetting(meterSettingVo);
	}

	

}
