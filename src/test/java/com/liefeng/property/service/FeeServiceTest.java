package com.liefeng.property.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.po.fee.MeterSettingPo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;

/**
 * 
 * <pre>      
 * Title:
 * Description:费用抄表服务测试类
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  吴志敬        
 * @version 1.0      
 * @created 2016年2月18日上午9:09:06
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FeeServiceTest {
	
	@Autowired
	private IFeeService feeService;

	/**
	 * 保存抄表
	 */
	@Test
	public void saveMeterRecord(){

		MeterRecordVo meterRecordVo=new MeterRecordVo();
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String oemCode = ContextManager.getInstance().getOemCode();
		meterRecordVo.setBuildingId("1");
		meterRecordVo.setCurrNum(12.00);
		try {
			meterRecordVo.setStartDate(sdf.parse(sdf.format(new Date(System.currentTimeMillis()))));
			meterRecordVo.setEndDate(sdf.parse(sdf.format(new Date(System.currentTimeMillis()))));
			meterRecordVo.setReadDate(sdf.parse(sdf.format(new Date(System.currentTimeMillis()))));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		
		meterRecordVo.setFloorId("2");
		meterRecordVo.setFrom("1");
		meterRecordVo.setHouseNum("A0101");
		meterRecordVo.setId(UUIDGenerator.generate());
		meterRecordVo.setMeterOwner("1");
		meterRecordVo.setMeterType("1");
		meterRecordVo.setOemCode(oemCode);
		meterRecordVo.setCurrNum(100.00);
		meterRecordVo.setProjectId("1");
		meterRecordVo.setPropertyId("1");
		meterRecordVo.setProprietorName("张三");
	
		meterRecordVo.setStaffId("1123");
		
		feeService.saveMeterRecord(meterRecordVo);
	}
	
	/**
	 * 保存抄表设置
	 */
	@Test
	public void saveMeterSetting(){
		ContextManager.getInstance().setOemCode("1");
		MeterSettingVo meterSettingVo=new MeterSettingVo();
		meterSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		meterSettingVo.setChargeable("1");
		meterSettingVo.setLastingDay(3);
		meterSettingVo.setModNum(100);
		meterSettingVo.setProjectId("1");
		meterSettingVo.setStaffId("1");
		meterSettingVo.setStartDay(1);
		meterSettingVo.setType("1");
		feeService.saveMeterSetting(meterSettingVo);
	}
	
	/**
	 * 获取抄表列表
	 */
	@Test
	public void findAllByProjectId(){
		DataPageValue<MeterSettingVo> page=feeService.findMeterSetting4Page("1",1,1);
		System.out.println(page);
	}
	
	/**
	 * 获取单个仪表设置
	 */
	@Test
	public void findMeterRecordById(){
		
		System.out.println(feeService.findMeterRecordById("4028895e52f3acb80152f3acb8520000"));
	}
	
	/**
	 * 修改仪表设置
	 */
	@Test
	public void updateMeterRecord(){
		ContextManager.getInstance().setOemCode("1");
		MeterSettingVo meterSettingVo=new MeterSettingVo();
		meterSettingVo.setId("4028895e52f3acb80152f3acb8520000");
		meterSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		meterSettingVo.setChargeable("2");
		meterSettingVo.setLastingDay(3);
		meterSettingVo.setModNum(100);
		meterSettingVo.setProjectId("2");
		meterSettingVo.setStaffId("2");
		meterSettingVo.setStartDay(2);
		meterSettingVo.setType("2");
		
		
		feeService.updateMeterRecord(meterSettingVo);
	}
}
