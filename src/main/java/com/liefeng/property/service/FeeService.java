package com.liefeng.property.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.bo.fee.FeeItemBo;
import com.liefeng.property.bo.fee.MeterRecordBo;
import com.liefeng.property.constant.FeeConstants;
import com.liefeng.property.domain.fee.FeeItemContext;
import com.liefeng.property.domain.fee.FeeRecordContext;
import com.liefeng.property.domain.fee.FeeSettingContext;
import com.liefeng.property.domain.fee.LadderFeeSettingContext;
import com.liefeng.property.domain.fee.MeterRecordContext;
import com.liefeng.property.domain.fee.MeterSettingContext;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.po.household.ProprietorPo;
import com.liefeng.property.util.DateUtil;
import com.liefeng.property.vo.fee.FeeItemVo;
import com.liefeng.property.vo.fee.FeeSettingVo;
import com.liefeng.property.vo.fee.LadderFeeSettingVo;
import com.liefeng.property.vo.fee.MeterRecordVo;
import com.liefeng.property.vo.fee.MeterSettingVo;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;

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

	private static Logger logger = LoggerFactory.getLogger(FeeService.class);

	@Override
	public void createMeterRecord(MeterRecordVo meterRecordVo) {

		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		meterRecordContext.create();
	}

	@Override
	public DataPageValue<MeterRecordVo> findMeterRecord4Page(
			MeterRecordBo meterRecordBo, Integer currentPage, Integer pageSize) {
		MeterRecordContext meterRecordContext = MeterRecordContext.build();
		return meterRecordContext.listMeterRecordVo4Page(meterRecordBo,
				currentPage, pageSize);
	}

	@Override
	public DataPageValue<MeterRecordVo> findPublicMeterRecord4Page(
			MeterRecordBo meterRecordBo, Integer currentPage, Integer pageSize) {
		MeterRecordContext meterRecordContext = MeterRecordContext.build();
		return meterRecordContext.listPublicMeterRecordVo4Page(meterRecordBo,
				currentPage, pageSize);
	}

	@Override
	public void createPublicMeterRecord(MeterRecordVo meterRecordVo) {

		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		meterRecordContext.createPublic();
	}

	@Override
	public MeterRecordVo getPreMeterRecord(MeterRecordVo meterRecordVo,
			Date date) {
		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		return meterRecordContext.getPre(date);
	}

	@Override
	public MeterRecordVo findMeterRecordById(String id) {
		MeterRecordContext meterRecordContext = MeterRecordContext.loadById(id);
		return meterRecordContext.get();
	}

	@Override
	public void updateMeterRecord(MeterRecordVo meterRecordVo) {
		MeterRecordContext meterRecordContext = MeterRecordContext
				.build(meterRecordVo);
		meterRecordContext.update();
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

	@Override
	public List<MeterSettingVo> findByProjectIdAndChargeableYes() {
		MeterSettingContext meterSettingContext = MeterSettingContext.build();
		return meterSettingContext.findByProjectIdAndChargeableYes();
	}

	@Override
	public List<MeterSettingVo> findByProjectIdAndChargeableYes(String projecId) {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.loadByProjectId(projecId);
		return meterSettingContext.findByProjectIdAndChargeableYes();
	}

	@Override
	public List<MeterSettingVo> getMeterAuth(String projectId, String meterOwner) {
		MeterSettingContext meterSettingContext = MeterSettingContext
				.loadByProjectId(projectId);
		return meterSettingContext.getMeterAuth(meterOwner);
	}

	// TODO 费用设置
	@Override
	public void createFeeSetting(FeeSettingVo feeSettingVo)
			throws LiefengException {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.build(feeSettingVo);
		feeSettingContext.create();
	}

	@Override
	public void deleteFeeSetting(String id) throws LiefengException {
		FeeSettingContext feeSettingContext = FeeSettingContext.loadById(id);
		feeSettingContext.delete();
	}

	@Override
	public DataPageValue<FeeSettingVo> findFeeSetting4Page(String projectId,
			Integer currentPage, Integer pageSize) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(projectId);
		return feeSettingContext.findByProjectId4Page(currentPage, pageSize);
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

	@Override
	public List<FeeSettingVo> findFeeSettingByProjectId(String projectId) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(projectId);
		return feeSettingContext.findByProjectId();
	}

	// TODO 阶梯收费设置
	@Override
	public void createLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
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
		return ladderFeeSettingContext.findByProjectId4Page(currentPage,
				pageSize);
	}

	@Override
	public LadderFeeSettingVo findLadderFeeSettingById(String id) {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
				.loadById(id);
		return ladderFeeSettingContext.findById();
	}

	@Override
	public void updateLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException {
		LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
				.build(ladderFeeSettingVo);
		ladderFeeSettingContext.update();
	}

	/**
	 * 物业管理费
	 */
	@Override
	public void createPropertyManageFee(String projectId) {
		try {

			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
					.findByProjectId(projectId);

			for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {
				FeeSettingContext feeSettingContext = FeeSettingContext
						.loadByProjectId(projectId);
				FeeSettingVo feeSettingVo = feeSettingContext.findChargeable(
						proprietorHouseVo.getUseType(),
						FeeConstants.FeeSetting.FEE_PROPERTYMANAGE);

				FeeItemContext feeItemContext = FeeItemContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				FeeItemVo feeItem = feeItemContext.getPreFeeItem(
						proprietorHouseVo.getHouseNum(),
						FeeConstants.FeeSetting.FEE_PROPERTYMANAGE);

				logger.info("开始计算生成房号为" + proprietorHouseVo.getHouseNum()
						+ "的物业管理费");

				if (feeItem != null) {
					logger.info("已经存在该费用项");
					return;
				}
				if (feeSettingVo == null) {
					logger.info("该费用不收费");
					return;
				}

				// 计费期
				Date[] dates = DateUtil.getCurrentDate(new Date(),
						Integer.parseInt(feeSettingVo.getPeriod()),
						feeSettingVo.getStartMonth());

				HouseContext houseContext = HouseContext
						.loadByProjectIdAndHouseNum(
								proprietorHouseVo.getProjectId(),
								proprietorHouseVo.getHouseNum());
				houseContext = HouseContext
						.loadById(houseContext.get().getId());
				ProprietorSingleHouseVo houseVo = houseContext.getSingleHouse();

				Double price = houseVo.getPropertyFee();
				Double sum = houseVo.getGrossArea() * price;
				logger.info("总金额：" + sum);
				FeeItemVo feeItemVo = new FeeItemVo();
				feeItemVo.setUpdateTime(new Date());
				feeItemVo.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
				feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
				feeItemVo
						.setFeeType(FeeConstants.FeeSetting.FEE_PROPERTYMANAGE);
				feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
				feeItemVo.setBuildingId(houseVo.getBuildingId());
				feeItemVo.setLateFeeRate(0.00);// 滞纳金
				feeItemVo.setProjectId(proprietorHouseVo.getProjectId());
				feeItemVo.setPropertyId(houseVo.getBuildingId());
				feeItemVo.setProprietorName(houseVo.getName());
				feeItemVo.setStaffId("-1");
				feeItemVo.setStatus("0");
				feeItemVo.setDiscount(1.00);
				feeItemVo.setTotalFee(sum);
				feeItemVo.setUnitPrice(price);
				feeItemVo.setUsageAmount(houseVo.getGrossArea());

				// 设置缴费期限 默认为下个月1号开始收费
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(new Date(dates[1].getTime()));
				deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
				deadline.add(Calendar.MONTH, 1);
				feeItemVo.setDeadline(deadline.getTime());

				feeItemContext = FeeItemContext.build(feeItemVo);
				feeItemContext.create();

			}
		} catch (Exception e) {
			logger.error("***费用{}生成失败******/n{}",FeeConstants.FeeSetting.FEE_PROPERTYMANAGE,e.getMessage());
			
		}
	}

	/**
	 * 本体维修基金
	 * 
	 */
	@Override
	public void createMaintenanceFee(String projectId) {
		try {
			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
					.findByProjectId(projectId);

			for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {
				FeeSettingContext feeSettingContext = FeeSettingContext
						.loadByProjectId(projectId);
				FeeSettingVo feeSettingVo = feeSettingContext.findChargeable(
						proprietorHouseVo.getUseType(),
						FeeConstants.FeeSetting.FEE_MAINTENANCE);

				FeeItemContext feeItemContext = FeeItemContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				FeeItemVo feeItem = feeItemContext.getPreFeeItem(
						proprietorHouseVo.getHouseNum(),
						FeeConstants.FeeSetting.FEE_MAINTENANCE);

				logger.info("开始计算生成房号为" + proprietorHouseVo.getHouseNum()
						+ "的本体维修基金");

				if (feeItem != null) {
					logger.info("已经存在该费用项");
					return;
				}
				if (feeSettingVo == null) {
					logger.info("该费用不收费");
					return;
				}

				// 计费期
				Date[] dates = DateUtil.getCurrentDate(new Date(),
						Integer.parseInt(feeSettingVo.getPeriod()),
						feeSettingVo.getStartMonth());

				HouseContext houseContext = HouseContext
						.loadByProjectIdAndHouseNum(
								proprietorHouseVo.getProjectId(),
								proprietorHouseVo.getHouseNum());
				houseContext = HouseContext
						.loadById(houseContext.get().getId());
				ProprietorSingleHouseVo houseVo = houseContext.getSingleHouse();

				Double price = feeSettingVo.getPrice(); // 单价
				Double sum = houseVo.getGrossArea() * price;
				logger.info("总金额：" + sum);
				FeeItemVo feeItemVo = new FeeItemVo();
				feeItemVo.setUsageAmount(houseVo.getGrossArea());
				feeItemVo.setCreateTime(new Date());
				feeItemVo.setUpdateTime(new Date());
				feeItemVo.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
				feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
				feeItemVo.setFeeType(FeeConstants.FeeSetting.FEE_MAINTENANCE);
				feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
				feeItemVo.setBuildingId(houseVo.getBuildingId());
				feeItemVo.setId(UUIDGenerator.generate());
				feeItemVo.setLateFeeRate(0.00);// 滞纳金
				feeItemVo.setOemCode(ContextManager.getInstance().getOemCode());
				feeItemVo.setProjectId(proprietorHouseVo.getProjectId());
				feeItemVo.setPropertyId(houseVo.getBuildingId());
				feeItemVo.setProprietorName(houseVo.getName());
				feeItemVo.setStaffId("-1");
				feeItemVo.setStatus("0");
				feeItemVo.setDiscount(1.00);
				feeItemVo.setTotalFee(sum);
				feeItemVo.setUnitPrice(price);

				// 设置缴费期限 默认为下个月1号开始收费
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(new Date(dates[1].getTime()));
				deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
				deadline.add(Calendar.MONTH, 1);
				feeItemVo.setDeadline(deadline.getTime());

				feeItemContext = FeeItemContext.build(feeItemVo);
				feeItemContext.create();
			}
		} catch (Exception e) {
			logger.info("***费用{}生成失败******",FeeConstants.FeeSetting.FEE_PROPERTYMANAGE);
			logger.error(e.getMessage());
		}
	}

	/**
	 * 垃圾处理费
	 * 
	 */
	@Override
	public void createGarbageFee(String projectId) {
		try {
			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
					.findByProjectId(projectId);

			for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {
				FeeSettingContext feeSettingContext = FeeSettingContext
						.loadByProjectId(projectId);
				FeeSettingVo feeSettingVo = feeSettingContext.findChargeable(
						proprietorHouseVo.getUseType(),
						FeeConstants.FeeSetting.FEE_GARBAGE);

				FeeItemContext feeItemContext = FeeItemContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				FeeItemVo feeItem = feeItemContext.getPreFeeItem(
						proprietorHouseVo.getHouseNum(),
						FeeConstants.FeeSetting.FEE_GARBAGE);

				logger.info("开始计算生成房号为" + proprietorHouseVo.getHouseNum()
						+ "的本体维修基金");

				if (feeItem != null) {
					logger.info("已经存在该费用项");
					return;
				}
				if (feeSettingVo == null) {
					logger.info("该费用不收费");
					return;
				}

				// 计费期
				Date[] dates = DateUtil.getCurrentDate(new Date(),
						Integer.parseInt(feeSettingVo.getPeriod()),
						feeSettingVo.getStartMonth());

				HouseContext houseContext = HouseContext
						.loadByProjectIdAndHouseNum(
								proprietorHouseVo.getProjectId(),
								proprietorHouseVo.getHouseNum());
				houseContext = HouseContext
						.loadById(houseContext.get().getId());
				ProprietorSingleHouseVo proprietorSingleHouseVo = houseContext
						.getSingleHouse();

				Double price = feeSettingVo.getPrice(); // 单价
				Double sum = feeSettingVo.getPrice();
				logger.info("总金额：" + sum);
				FeeItemVo feeItemVo = new FeeItemVo();
				feeItemVo.setCreateTime(new Date());
				feeItemVo.setUpdateTime(new Date());
				feeItemVo.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
				feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
				feeItemVo.setFeeType(FeeConstants.FeeSetting.FEE_GARBAGE);
				feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
				feeItemVo
						.setBuildingId(proprietorSingleHouseVo.getBuildingId());
				feeItemVo.setId(UUIDGenerator.generate());
				feeItemVo.setLateFeeRate(0.00);// 滞纳金
				feeItemVo.setOemCode(ContextManager.getInstance().getOemCode());
				feeItemVo.setProjectId(proprietorHouseVo.getProjectId());
				feeItemVo
						.setPropertyId(proprietorSingleHouseVo.getBuildingId());
				feeItemVo.setProprietorName(proprietorSingleHouseVo.getName());
				feeItemVo.setStaffId("-1");
				feeItemVo.setStatus("0");
				feeItemVo.setDiscount(1.00);
				feeItemVo.setTotalFee(sum);
				feeItemVo.setUnitPrice(price);

				// 设置缴费期限 默认为下个月1号开始收费
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(new Date(dates[1].getTime()));
				deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
				deadline.add(Calendar.MONTH, 1);
				feeItemVo.setDeadline(deadline.getTime());

				feeItemContext = FeeItemContext.build(feeItemVo);
				feeItemContext.create();
			}
		} catch (Exception e) {
			logger.info("***费用{}生成失败******",FeeConstants.FeeSetting.FEE_GARBAGE);
			logger.error(e.getMessage());
		}
	}

	/**
	 * 排污费
	 * 
	 */
	@Override
	public void createPolluFee(String projectId) {
		try {
			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);

			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
					.findByProjectId(projectId);

			for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {
				FeeSettingContext feeSettingContext = FeeSettingContext
						.loadByProjectId(projectId);
				FeeSettingVo feeSettingVo = feeSettingContext.findChargeable(
						proprietorHouseVo.getUseType(),
						FeeConstants.FeeSetting.FEE_GARBAGE);

				MeterRecordContext meterRecordContext = MeterRecordContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				MeterRecordVo meterRecordVo = meterRecordContext.get(
						proprietorHouseVo.getHouseNum(), null,
						FeeConstants.MeterRecord.METER_WATER,
						FeeConstants.MeterRecord.METEROWNER_YSE, preDate);

				logger.info("开始计算生成房号为" + proprietorHouseVo.getHouseNum()
						+ "的排污费");

				FeeItemContext feeItemContext = FeeItemContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				FeeItemVo feeItem = feeItemContext.getPreFeeItem(
						proprietorHouseVo.getHouseNum(),
						FeeConstants.FeeSetting.FEE_GARBAGE);
				if (feeItem != null) {
					logger.info("已经存在该费用项");
					return;
				}
				if (feeSettingVo == null) {
					logger.info("该费用不收费");
					return;
				}
				if (meterRecordVo == null) {
					logger.info("未有水表记录");
					return;
				}

				// 计费期
				Date[] dates = DateUtil.getCurrentDate(new Date(),
						Integer.parseInt(feeSettingVo.getPeriod()),
						feeSettingVo.getStartMonth());

				HouseContext houseContext = HouseContext
						.loadByProjectIdAndHouseNum(
								proprietorHouseVo.getProjectId(),
								proprietorHouseVo.getHouseNum());
				houseContext = HouseContext
						.loadById(houseContext.get().getId());
				ProprietorSingleHouseVo proprietorSingleHouseVo = houseContext
						.getSingleHouse();

				Double price = feeSettingVo.getPrice(); // 单价
				Double sum = price * meterRecordVo.getUseAmount();
				logger.info("总金额：" + sum);
				FeeItemVo feeItemVo = new FeeItemVo();
				feeItemVo.setCreateTime(new Date());
				feeItemVo.setUpdateTime(new Date());
				feeItemVo.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
				feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
				feeItemVo.setFeeType(FeeConstants.FeeSetting.FEE_POLLU);
				feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
				feeItemVo
						.setBuildingId(proprietorSingleHouseVo.getBuildingId());
				feeItemVo.setId(UUIDGenerator.generate());
				feeItemVo.setLateFeeRate(0.00);// 滞纳金
				feeItemVo.setOemCode(ContextManager.getInstance().getOemCode());
				feeItemVo.setProjectId(proprietorHouseVo.getProjectId());
				feeItemVo
						.setPropertyId(proprietorSingleHouseVo.getBuildingId());
				feeItemVo.setProprietorName(proprietorSingleHouseVo.getName());
				feeItemVo.setStaffId("-1");
				feeItemVo.setStatus("0");
				feeItemVo.setDiscount(1.00);
				feeItemVo.setTotalFee(sum);
				feeItemVo.setUnitPrice(price);

				// 设置缴费期限 默认为下个月1号开始收费
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(new Date(dates[1].getTime()));
				deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
				deadline.add(Calendar.MONTH, 1);
				feeItemVo.setDeadline(deadline.getTime());

				feeItemContext = FeeItemContext.build(feeItemVo);
				feeItemContext.create();
			}
		} catch (Exception e) {
			logger.info("***费用{}生成失败******",FeeConstants.FeeSetting.FEE_POLLU);
			logger.error(e.getMessage());
		}
	}

	/**
	 * 业主表费用
	 */
	@Override
	public void createOwnerMerterFee(String projectId, String feeType) {
		String meterType = "";
		switch (feeType) {
		case FeeConstants.FeeSetting.FEE_WATER:
			meterType = FeeConstants.MeterRecord.METER_WATER;
			break;
		case FeeConstants.FeeSetting.FEE_ELECTRICITY:
			meterType = FeeConstants.MeterRecord.METER_ELECTRICITY;
			break;
		case FeeConstants.FeeSetting.FEE_GAS:
			meterType = FeeConstants.MeterRecord.METER_GAS;
			break;
		}

		try {
			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
					.findByProjectId(projectId);

			for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {
				Double price = 0.00; // 单价
				Double sum = 0.00; // 总金额

				FeeSettingContext feeSettingContext = FeeSettingContext
						.loadByProjectId(projectId);
				FeeSettingVo feeSettingVo = feeSettingContext.findChargeable(
						proprietorHouseVo.getUseType(), feeType);

				// 阶梯收费
				LadderFeeSettingContext ladderFeeSettingContext = LadderFeeSettingContext
						.loadByProjectId(projectId);
				LadderFeeSettingVo LadderFeeSettingVo = ladderFeeSettingContext
						.get(feeType, proprietorHouseVo.getUseType());

				// 上个月仪表读数
				MeterRecordContext meterRecordContext = MeterRecordContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				MeterRecordVo meterRecordVo = meterRecordContext.get(
						proprietorHouseVo.getHouseNum(), null, meterType,
						FeeConstants.MeterRecord.METEROWNER_YSE, preDate);

				logger.info("开始计算生成房号为" + proprietorHouseVo.getHouseNum()
						+ ",仪表id为" + feeType + "的费用项");

				FeeItemContext feeItemContext = FeeItemContext
						.loadByProjectId(proprietorHouseVo.getProjectId());
				FeeItemVo feeItem = feeItemContext.getPreFeeItem(
						proprietorHouseVo.getHouseNum(), feeType);
				if (feeItem != null) {
					logger.info("已经存在该费用项");
					return;
				}
				if (meterRecordVo == null) {
					logger.info("未有抄表记录");
					return;
				}
				if (feeSettingVo == null) {
					logger.info("该费用不收费");
					return;
				}
				// 计费期
				Date[] dates = DateUtil.getCurrentDate(new Date(),
						Integer.parseInt(feeSettingVo.getPeriod()),
						feeSettingVo.getStartMonth());
				logger.info("用量为:" + meterRecordVo.getUseAmount());
				if (LadderFeeSettingVo != null) {
					logger.info("使用阶梯计算");
					price = LadderFeeSettingVo.getLadder1Price();
					// 第一阶
					if (meterRecordVo.getUseAmount() > LadderFeeSettingVo
							.getLadder1()) {
						sum += LadderFeeSettingVo.getLadder1()
								* LadderFeeSettingVo.getLadder1Price();
					} else {
						sum += meterRecordVo.getUseAmount()
								* LadderFeeSettingVo.getLadder1Price();
						;
					}

					// 第二阶
					if (meterRecordVo.getUseAmount() > LadderFeeSettingVo
							.getLadder2()) {
						sum += (LadderFeeSettingVo.getLadder2() - LadderFeeSettingVo
								.getLadder1())
								* LadderFeeSettingVo.getLadder2Price();
					} else if (meterRecordVo.getUseAmount() > LadderFeeSettingVo
							.getLadder1()) {
						sum += (meterRecordVo.getUseAmount() - LadderFeeSettingVo
								.getLadder1())
								* LadderFeeSettingVo.getLadder2Price();
					}

					// 第三阶
					if (meterRecordVo.getUseAmount() > LadderFeeSettingVo
							.getLadder2()) {
						sum += (meterRecordVo.getUseAmount() - LadderFeeSettingVo
								.getLadder2())
								* LadderFeeSettingVo.getLadder3Price();
					}
				} else {
					logger.info("使用标准费用计算");
					sum = meterRecordVo.getUseAmount()
							* feeSettingVo.getPrice();
					price = feeSettingVo.getPrice();
				}
				logger.info("总金额：" + sum);
				HouseContext houseContext = HouseContext
						.loadByProjectIdAndHouseNum(
								proprietorHouseVo.getProjectId(),
								proprietorHouseVo.getHouseNum());
				houseContext = HouseContext
						.loadById(houseContext.get().getId());
				ProprietorSingleHouseVo proprietorSingleHouseVo = houseContext
						.getSingleHouse();
				FeeItemVo feeItemVo = new FeeItemVo();
				feeItemVo.setCreateTime(new Date());
				feeItemVo.setUpdateTime(new Date());
				feeItemVo.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
				feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
				feeItemVo.setFeeType(feeType);
				feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
				feeItemVo
						.setBuildingId(proprietorSingleHouseVo.getBuildingId());
				feeItemVo.setId(UUIDGenerator.generate());
				feeItemVo.setLateFeeRate(0.00);// 滞纳金
				feeItemVo.setMeterRecordId(meterRecordVo.getId());
				feeItemVo.setOemCode(ContextManager.getInstance().getOemCode());
				feeItemVo.setProjectId(meterRecordVo.getProjectId());
				feeItemVo.setPropertyId(meterRecordVo.getBuildingId());
				feeItemVo.setProprietorName(proprietorSingleHouseVo.getName());
				feeItemVo.setStaffId("-1");
				feeItemVo.setStatus("0");
				feeItemVo.setDiscount(1.00);
				feeItemVo.setTotalFee(sum);
				feeItemVo.setUnitPrice(price);
				feeItemVo.setUsageAmount(meterRecordVo.getUseAmount());

				// 设置缴费期限 默认为下个月1号开始收费
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(new Date(dates[1].getTime()));
				deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
				deadline.add(Calendar.MONTH, 1);
				feeItemVo.setDeadline(deadline.getTime());

				feeItemContext = FeeItemContext.build(feeItemVo);
				feeItemContext.create();
			}
		} catch (Exception e) {
			logger.info("***费用{}生成失败******",feeType);
			logger.error(e.getMessage());
		}
	}

	/**
	 * 公摊费用生成
	 * 
	 * @param projectId
	 * @param feeType
	 */
	@Override
	public void createPublicMerterFee(String projectId, String feeType) {
		String meterType = "";
		switch (feeType) {
		case FeeConstants.FeeSetting.FEE_PUBLIC_WATER:
			meterType = FeeConstants.MeterRecord.METER_PUBLIC_WATER;
			break;
		case FeeConstants.FeeSetting.FEE_PUBLIC_ELECTRICITY:
			meterType = FeeConstants.MeterRecord.METER_PUBLIC_ELECTRICITY;
			break;
		case FeeConstants.FeeSetting.FEE_PUBLIC_GAS:
			meterType = FeeConstants.MeterRecord.METER_PUBLIC_GAS;
			break;
		}
		try {
			// 上个月日期
			Date preDate = TimeUtil.getDayBeforeByMonth(new Date(), 1);

			FeeSettingContext feeSettingContext = FeeSettingContext
					.loadByProjectId(projectId);
			FeeSettingVo feeSettingVo = feeSettingContext.findChargeable("1",
					feeType);

			ProjectBuildingContext buildingContext = ProjectBuildingContext
					.loadByProjectId(projectId);
			List<ProjectBuildingVo> projectBuildingVos = buildingContext
					.finByProjectId();

			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext
					.build();
			for (ProjectBuildingVo projectBuildingVo : projectBuildingVos) { // 所有楼栋
				List<ProprietorHouseVo> proprietorHouseVos = proprietorHouseContext
						.findByProjectIdAndBuildingId(projectId,
								projectBuildingVo.getId());
				for (ProprietorHouseVo proprietorHouseVo : proprietorHouseVos) {// 所有业主房产
					// 上个月仪表读数
					MeterRecordContext meterRecordContext = MeterRecordContext
							.loadByProjectId(proprietorHouseVo.getProjectId());
					MeterRecordVo meterRecordVo = meterRecordContext.get(
							proprietorHouseVo.getHouseNum(), null, meterType,
							FeeConstants.MeterRecord.METEROWNER_YSE, preDate);

					logger.info("开始计算生成楼栋号为" + projectBuildingVo.getName()
							+ ",仪表id为" + feeType + "的费用项");

					Double price = 0.00; // 单价
					Double sum = 0.00; // 总金额

					FeeItemContext feeItemContext = FeeItemContext
							.loadByProjectId(proprietorHouseVo.getProjectId());
					FeeItemVo feeItem = feeItemContext.getPreFeeItem(
							proprietorHouseVo.getHouseNum(), feeType);
					if (feeItem != null) {
						logger.info("已经存在该费用项");
						return;
					}
					if (meterRecordVo == null) {
						logger.info("未有抄表记录");
						return;
					}
					if (feeSettingVo == null) {
						logger.info("该费用不收费");
						return;
					}
					// 计费期
					Date[] dates = DateUtil.getCurrentDate(new Date(),
							Integer.parseInt(feeSettingVo.getPeriod()),
							feeSettingVo.getStartMonth());
					logger.info("用量为:" + meterRecordVo.getUseAmount());
					price = feeSettingVo.getPrice();
					sum = price * meterRecordVo.getUseAmount()
							/ proprietorHouseVos.size();
					HouseContext houseContext = HouseContext
							.loadByProjectIdAndHouseNum(
									proprietorHouseVo.getProjectId(),
									proprietorHouseVo.getHouseNum());
					houseContext = HouseContext.loadById(houseContext.get()
							.getId());
					ProprietorSingleHouseVo proprietorSingleHouseVo = houseContext
							.getSingleHouse();
					FeeItemVo feeItemVo = new FeeItemVo();
					feeItemVo.setCreateTime(new Date());
					feeItemVo.setUpdateTime(new Date());
					feeItemVo
							.setStartDate(TimeUtil.getFirstDayOfMonth(preDate));
					feeItemVo.setEndDate(TimeUtil.getLastDayOfMonth(preDate));
					feeItemVo.setFeeType(feeType);
					feeItemVo.setHouseNum(proprietorHouseVo.getHouseNum());
					feeItemVo.setBuildingId(proprietorSingleHouseVo
							.getBuildingId());
					feeItemVo.setId(UUIDGenerator.generate());
					feeItemVo.setLateFeeRate(0.00);// 滞纳金
					feeItemVo.setMeterRecordId(meterRecordVo.getId());
					feeItemVo.setOemCode(ContextManager.getInstance()
							.getOemCode());
					feeItemVo.setProjectId(meterRecordVo.getProjectId());
					feeItemVo.setPropertyId(meterRecordVo.getBuildingId());
					feeItemVo.setProprietorName(proprietorSingleHouseVo
							.getName());
					feeItemVo.setStaffId("-1");
					feeItemVo.setStatus("0");
					feeItemVo.setDiscount(1.00);
					feeItemVo.setTotalFee(sum);
					feeItemVo.setUnitPrice(price);
					feeItemVo.setUsageAmount(meterRecordVo.getUseAmount());

					// 设置缴费期限 默认为下个月1号开始收费
					Calendar deadline = Calendar.getInstance();
					deadline.setTime(new Date(dates[1].getTime()));
					deadline.set(Calendar.DATE, feeSettingVo.getPaymentPeriod());
					deadline.add(Calendar.MONTH, 1);
					feeItemVo.setDeadline(deadline.getTime());

					feeItemContext = FeeItemContext.build(feeItemVo);
					feeItemContext.create();
				}
			}
		} catch (Exception e) {
			logger.info("***费用{}生成失败******",feeType);
			logger.error(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void collect(String feeItemId) {
		FeeItemContext feeItemContext = FeeItemContext.loadById(feeItemId);
		FeeItemVo feeItemVo = feeItemContext.collect();

		FeeRecordContext feeRecordContext = FeeRecordContext.build();
		feeRecordContext.create(feeItemVo);
	}

	@Override
	public DataPageValue<FeeItemVo> getFeeItem(FeeItemBo feeItemBo,
			Integer currentPage, Integer pageSize) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(feeItemBo.getProjectId());
		List<FeeSettingVo> feeSettingVos = feeSettingContext.findByProjectId();
		// 所有费用项的收费日期一样，所以默认取第一个
		int cycle = Integer.parseInt(feeSettingVos.get(0).getPeriod());
		int startMonth = feeSettingVos.get(0).getStartMonth();
		// 计费期设置
		if (feeItemBo.getStartDate() == null) {
			Date[] dates = DateUtil.getCurrentDate(new Date(), cycle,
					startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		} else {
			Date[] dates = DateUtil.getCurrentDate(feeItemBo.getStartDate(),
					cycle, startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		}

		FeeItemContext feeItemContext = FeeItemContext.build();
		return feeItemContext.list(feeItemBo, currentPage, pageSize);
	}

	@Override
	public FeeItemVo findFeeItem(FeeItemBo feeItemBo) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(feeItemBo.getProjectId());
		List<FeeSettingVo> feeSettingVos = feeSettingContext.findByProjectId();
		// 所有费用项的收费日期一样，所以默认取第一个
		int cycle = Integer.parseInt(feeSettingVos.get(0).getPeriod());
		int startMonth = feeSettingVos.get(0).getStartMonth();
		// 计费期设置
		if (feeItemBo.getStartDate() == null) {
			Date[] dates = DateUtil.getCurrentDate(new Date(), cycle,
					startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		} else {
			Date[] dates = DateUtil.getCurrentDate(feeItemBo.getStartDate(),
					cycle, startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		}

		FeeItemContext feeItemContext = FeeItemContext.build();
		return feeItemContext.getOne(feeItemBo);
	}

	@Override
	public void updateFeeItem(FeeItemVo feeItemVo) {
		FeeItemContext feeItemContext = FeeItemContext.build(feeItemVo);
		feeItemContext.update();
	}

	@Override
	public DataPageValue<FeeItemVo> findAllFeeItem(FeeItemBo feeItemBo,
			Integer currentPage, Integer pageSize) {
		FeeSettingContext feeSettingContext = FeeSettingContext
				.loadByProjectId(feeItemBo.getProjectId());
		List<FeeSettingVo> feeSettingVos = feeSettingContext.findByProjectId();
		// 所有费用项的收费日期一样，所以默认取第一个
		int cycle = Integer.parseInt(feeSettingVos.get(0).getPeriod());
		int startMonth = feeSettingVos.get(0).getStartMonth();
		// 计费期设置
		if (feeItemBo.getStartDate() == null) {
			Date[] dates = DateUtil.getCurrentDate(new Date(), cycle,
					startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		} else {
			Date[] dates = DateUtil.getCurrentDate(feeItemBo.getStartDate(),
					cycle, startMonth);
			feeItemBo.setStartDate(TimeUtil.getFirstDayOfMonth(dates[0]));
			feeItemBo.setEndDate(TimeUtil.getLastDayOfMonth(dates[1]));
		}

		FeeItemContext feeItemContext = FeeItemContext.build();
		return feeItemContext.findAll(feeItemBo, currentPage, pageSize);
	}
}
