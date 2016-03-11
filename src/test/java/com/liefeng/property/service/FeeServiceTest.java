package com.liefeng.property.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liefeng.Application;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.bo.fee.FeeItemBo;
import com.liefeng.property.bo.fee.MeterRecordBo;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.exception.FeeException;
import com.liefeng.property.vo.fee.FeeItemVo;
import com.liefeng.property.vo.fee.FeeSettingVo;
import com.liefeng.property.vo.fee.LadderFeeSettingVo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;
import com.liefeng.property.vo.project.ProjectVo;

/**
 * 
 * <pre>
 * Title:
 * Description:费用抄表服务测试类
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing
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
	public void createMeterRecord() {

		MeterRecordVo meterRecordVo = new MeterRecordVo();

		ContextManager.getInstance().setOemCode("1");
		meterRecordVo.setBuildingId("1");
		meterRecordVo.setCurrNum(12.00);
		
		meterRecordVo.setStartDate(TimeUtil.getDate(new Date()));
		meterRecordVo.setEndDate(TimeUtil.getDate(new Date()));
		meterRecordVo.setReadDate(TimeUtil.getDate(new Date()));
		
		meterRecordVo.setFloorId("2");
		meterRecordVo.setFrom("1");
		meterRecordVo.setHouseNum("A0101");
		meterRecordVo.setId(UUIDGenerator.generate());
		meterRecordVo.setMeterOwner("1");
		meterRecordVo.setMeterType("1");
		meterRecordVo.setCurrNum(40.00);
		meterRecordVo.setProjectId("1");
		meterRecordVo.setPropertyId("1");
		meterRecordVo.setProprietorName("张三");

		meterRecordVo.setStaffId("1123");
		try {
			feeService.createMeterRecord(meterRecordVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void listMeterRecord(){
		MeterRecordBo meterRecordBo= new MeterRecordBo();
		meterRecordBo.setStartDate(TimeUtil.getNowTime("yyyy-MM-dd"));
		meterRecordBo.setFrom("1");
		System.out.println(feeService.findMeterRecord4Page(meterRecordBo, 1, 30));
	}
	
	@Test
	public void listPublicMeterRecord(){
		try{
		MeterRecordBo meterRecordBo= new MeterRecordBo();
		meterRecordBo.setStartDate(TimeUtil.getNowTime("yyyy-MM-dd"));
		//meterRecordBo.setFrom("1");
		System.out.println(feeService.findPublicMeterRecord4Page(meterRecordBo, 1, 30));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void findMeterRecordById(){
		MeterRecordVo meterRecordVo = feeService.findMeterRecordById("4028895e531356950153137769220010");
		System.out.println(meterRecordVo);
	}
	

	
	/**
	 * 保存抄表
	 */
	@Test
	public void createPublicMeterRecord() {

		MeterRecordVo meterRecordVo = new MeterRecordVo();

		ContextManager.getInstance().setOemCode("1");
		meterRecordVo.setBuildingId("1123456789");
		meterRecordVo.setCurrNum(12.00);
		
		meterRecordVo.setStartDate(TimeUtil.getDate(new Date()));
		meterRecordVo.setEndDate(TimeUtil.getDate(new Date()));
		meterRecordVo.setReadDate(TimeUtil.getDate(new Date()));
		
		meterRecordVo.setFloorId("2");
		meterRecordVo.setFrom("1");
		meterRecordVo.setHouseNum("A0101");
		meterRecordVo.setId(UUIDGenerator.generate());
		meterRecordVo.setMeterOwner("2");
		meterRecordVo.setMeterType("4");
		meterRecordVo.setCurrNum(40.00);
		meterRecordVo.setProjectId("0000000052a7943f0152a7943fc00000");
		meterRecordVo.setPropertyId("1");
		meterRecordVo.setProprietorName("张三");

		meterRecordVo.setStaffId("1123");
		try {
			feeService.createPublicMeterRecord(meterRecordVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 保存抄表设置
	 */
	@Test
	public void createMeterSetting() {
		ContextManager.getInstance().setOemCode("1");
		MeterSettingVo meterSettingVo = new MeterSettingVo();
		meterSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		meterSettingVo.setChargeable("1");
		meterSettingVo.setLastingDay(3);
		meterSettingVo.setModNum(100);
		meterSettingVo.setProjectId("1");
		meterSettingVo.setStaffId("1");
		meterSettingVo.setStartDay(1);
		meterSettingVo.setType("1");
		try {
			feeService.createMeterSetting(meterSettingVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void getMeterAuth(){
		System.out.println(feeService.getMeterAuth("0000000052a7943f0152a7943fc00000","1"));
	}

	/**
	 * 获取抄表列表
	 */
	@Test
	public void findAllByProjectId() {
		DataPageValue<MeterSettingVo> page = feeService.findMeterSetting4Page(
				"1", 1, 1);
		System.out.println(page);
	}

	/**
	 * 获取单个仪表设置
	 */
	@Test
	public void findMeterSettingById() {

		System.out.println(feeService
				.findMeterSettingById("4028895e52f3acb80152f3acb8520000"));
	}

	/**
	 * 修改仪表设置
	 */
	@Test
	public void updateMeterSetting() {
		ContextManager.getInstance().setOemCode("1");
		MeterSettingVo meterSettingVo = new MeterSettingVo();
		meterSettingVo.setId("4028895e52f3acb80152f3acb8520000");
		meterSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		meterSettingVo.setChargeable("2");
		meterSettingVo.setLastingDay(3);
		meterSettingVo.setModNum(100);
		meterSettingVo.setProjectId("2");
		meterSettingVo.setStaffId("2");
		meterSettingVo.setStartDay(2);
		meterSettingVo.setType("2");
		try{
			feeService.updateMeterSetting(meterSettingVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deleteMeterSetting() {
		try{
			feeService.deleteMeterSetting("4028895e52f3ed7c0152f3ed7cf80000");
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}
//TODO 费用设置测试
	@Test
	public void createFeeSetting() {
		ContextManager.getInstance().setOemCode("1");
		FeeSettingVo feeSettingVo = new FeeSettingVo();
		feeSettingVo.setChargeable("1");
		feeSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		feeSettingVo.setFeeType("1");
		feeSettingVo.setPaymentPeriod(3);
		feeSettingVo.setPeriod("3");
		feeSettingVo.setPrice(10.00);
		feeSettingVo.setProjectId("1");
		feeSettingVo.setStaffId("1");
		feeSettingVo.setStartDay(1);
		feeSettingVo.setStartMonth(1);
		feeSettingVo.setUnit("度");
		feeSettingVo.setUseType("1");
		try{
			feeService.createFeeSetting(feeSettingVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deleteFeeSetting() {
		try{
			feeService.deleteFeeSetting("4028895e52fd95fe0152fd95fe7c0000");
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void findFeeSetting4Page() {
		System.out.println(feeService.findFeeSetting4Page("1", 1, 30));
	}

	@Test
	public void findFeeSettingById() {
		System.out.println(feeService.findFeeSettingById("4028895e52fda38d0152fda38d350000"));
	}

	@Test
	public void updateFeeSetting() {
		ContextManager.getInstance().setOemCode("1");
		FeeSettingVo feeSettingVo = new FeeSettingVo();
		feeSettingVo.setId("4028895e52fda38d0152fda38d350000");
		feeSettingVo.setChargeable("2");
		feeSettingVo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		feeSettingVo.setFeeType("2");
		feeSettingVo.setPaymentPeriod(3);
		feeSettingVo.setPeriod("2");
		feeSettingVo.setPrice(10.00);
		feeSettingVo.setProjectId("2");
		feeSettingVo.setStaffId("2");
		feeSettingVo.setStartDay(1);
		feeSettingVo.setStartMonth(2);
		feeSettingVo.setUnit("度");
		feeSettingVo.setUseType("1");
		feeService.updateFeeSetting(feeSettingVo);
	}
	
	//TODO 阶梯收费设置
	@Test
	public void createLadderFeeSetting() {
		ContextManager.getInstance().setOemCode("1");
		LadderFeeSettingVo ladderFeeSettingVo=new LadderFeeSettingVo();
		ladderFeeSettingVo.setFeeType("1");
		ladderFeeSettingVo.setLadder1(1);
		ladderFeeSettingVo.setLadder1Price(10.00);
		ladderFeeSettingVo.setLadder2(10);
		ladderFeeSettingVo.setLadder2Price(20.00);
		ladderFeeSettingVo.setLadder3(15);
		ladderFeeSettingVo.setLadder3Price(30.00);
		ladderFeeSettingVo.setProjectId("1");
		ladderFeeSettingVo.setStaffId("1");
		ladderFeeSettingVo.setUseType("1");
		try{
			feeService.createLadderFeeSetting(ladderFeeSettingVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void deleteLadderFeeSetting() {
		try{
			feeService.deleteLadderFeeSetting("4028895e5306c04d015306c04dc80000");
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void findLadderFeeSetting4Page() {
		DataPageValue<LadderFeeSettingVo> dataPageValue = feeService.findLadderFeeSetting4Page("2",1,100);
		System.out.println(dataPageValue);
	}

	@Test
	public void findLadderFeeSettingById() {
		System.out.println(feeService.findLadderFeeSettingById("4028895e5306c04d015306c04dc80000"));
	}

	@Test
	public void updateLadderFeeSetting() {
		LadderFeeSettingVo ladderFeeSettingVo=new LadderFeeSettingVo();
		ladderFeeSettingVo.setId("4028895e5306c5f2015306c5f2710000");
		ladderFeeSettingVo.setFeeType("2");
		ladderFeeSettingVo.setLadder1(2);
		ladderFeeSettingVo.setLadder1Price(20.00);
		ladderFeeSettingVo.setLadder2(30);
		ladderFeeSettingVo.setLadder2Price(30.00);
		ladderFeeSettingVo.setLadder3(25);
		ladderFeeSettingVo.setLadder3Price(40.00);
		ladderFeeSettingVo.setProjectId("2");
		ladderFeeSettingVo.setStaffId("2");
		ladderFeeSettingVo.setUseType("2");
		try{
			feeService.updateLadderFeeSetting(ladderFeeSettingVo);
		} catch (FeeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//TODO 费用
	@Test
	public void createOwnerMerterFee(){
		ContextManager.getInstance().setOemCode("2");
		feeService.createOwnerMerterFee("0000000052a7943f0152a7943fc00000", "2");
	}
	
	@Test
	public void collect(){
		feeService.collect("4028895e531cef3a01531cef3a2e0000");
	}
	
	@Autowired
	private ProjectService projectService;
	
	@Test
	public void listProjects(){
		ContextManager.getInstance().setOemCode("liefeng");
		System.out.println(projectService.listProjects());
	}

	@Test
	public void createFee(){
		List<ProjectVo> projectDataPageValue = projectService.findAll();
		
		for (ProjectVo projectvo : projectDataPageValue) {
			ContextManager.getInstance().setOemCode(projectvo.getOemCode());
			feeService.createPropertyManageFee(projectvo.getId());
			feeService.createPolluFee(projectvo.getId());
			feeService.createGarbageFee(projectvo.getId());
			feeService.createMaintenanceFee(projectvo.getId());
			feeService.createParkingFee(projectvo.getId());
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_WATER);
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_ELECTRICITY);
			feeService.createOwnerMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_GAS);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_GAS);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_ELECTRICITY);
			feeService.createPublicMerterFee(projectvo.getId(),FeeConstants.FeeSetting.FEE_PUBLIC_ELECTRICITY);

		}
	}
	
	@Test 
	public void findFeeItem(){
		ContextManager.getInstance().setOemCode("1");
		FeeItemBo feeItemBo = new FeeItemBo();
		feeItemBo.setProjectId("0000000052a7943f0152a7943fc00000");
		feeItemBo.setHouseNum("A0188");
		feeItemBo.setFeeType("1");
		FeeItemVo feeItemVo = feeService.findFeeItem(feeItemBo);
	    System.out.println(feeItemVo);	
	}
	
	@Test 
	public void findAllFeeItem(){
		ContextManager.getInstance().setOemCode("1");
		FeeItemBo feeItemBo = new FeeItemBo();
		feeItemBo.setProjectId("0000000052a7943f0152a7943fc00000");
		DataPageValue<FeeItemVo> feeItemVo = feeService.findAllFeeItem(feeItemBo,1,30);
	    System.out.println(feeItemVo);	
	}
	
	@Test
	public void findPersonal(){
		ContextManager.getInstance().setOemCode("1");
		FeeItemBo feeItemBo = new FeeItemBo();
		feeItemBo.setProjectId("0000000052a7943f0152a7943fc00000");
		feeItemBo.setHouseNum("A0288");
		feeItemBo.setStatus("0");
		DataPageValue<FeeItemVo> feeItemVo = feeService.findPersonal(feeItemBo,1,30);
	    System.out.println(feeItemVo);	
	}
}
