package com.liefeng.intf.property;

import java.util.Date;
import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.fee.MeterRecordBo;
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
 * @author  吴志敬        
 * @version 1.0      
 * @created 2016年2月17日下午6:51:14
 * </pre>
 */
public interface IFeeService {

	/**
	 * 保存抄表
	 */
	public void createMeterRecord(MeterRecordVo meterRecordVo);

	/**
	 * 抄表列表 所有房产及抄表数据集结合(业主房产为主表)
	 * @param meterRecordVo
	 */
	public DataPageValue<MeterRecordVo> findMeterRecord4Page(MeterRecordBo meterRecordBo,Integer currentPage,Integer pageSize);

	/**
	 * 根据Id获取抄表记录
	 * @param id
	 * @return
	 */
	public MeterRecordVo findMeterRecordById(String id);
	
	/**
	 * 更新抄表记录
	 */
	public void updateMeterRecord(MeterRecordVo meterRecordVo);
	
	/**
	 * 创建仪表设置
	 * @param meterSettingPo
	 */
	public void createMeterSetting(MeterSettingVo meterSettingVo) throws LiefengException;

	/**
	 * 公摊抄表列表 (楼栋为主表)
	 * @param meterRecordVo
	 */
	public DataPageValue<MeterRecordVo> findPublicMeterRecord4Page(MeterRecordBo meterRecordBo,Integer currentPage,Integer pageSize);

	
	/**
	 * 获取仪表设置列表
	 * @param string
	 * @param pageSize
	 * @param currentPage
	 */
	public DataPageValue<MeterSettingVo> findMeterSetting4Page(String string,Integer pageSize,Integer currentPage);

	/**
	 * 根据id获取仪表设置
	 * @param id
	 * @return
	 */
	public MeterSettingVo findMeterSettingById(String id);

	/**
	 * 修改仪表设置
	 */
	public void updateMeterSetting(MeterSettingVo meterSettingVo) throws LiefengException;

	/**
	 * 删除仪表设置
	 * @param id
	 */
	public void deleteMeterSetting(String id) throws LiefengException;

	/**
	 * 保存费用设置
	 * @param feeSettingVo
	 * @throws LiefengException
	 */
	public void createFeeSetting(FeeSettingVo feeSettingVo);

	/**
	 * 删除费设置
	 * @param id
	 * @throws LiefengException
	 */
	public void deleteFeeSetting(String id) throws LiefengException;

	/**
	 * 根据项目id查询费用设置 分页
	 * @param projectId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public DataPageValue<FeeSettingVo> findFeeSetting4Page(String projectId,
			Integer currentPage,Integer pageSize);

	/**
	 * 根据id 获取费用设置
	 * @param id
	 * @return
	 */
	public FeeSettingVo findFeeSettingById(String id);

	/**
	 * 更新费用设置
	 * @param feeSettingVo
	 * @throws LiefengException
	 */
	public void updateFeeSetting(FeeSettingVo feeSettingVo) throws LiefengException;

	/**
	 * 创建阶梯收费
	 * @param ladderFeeSettingVo
	 * @throws LiefengException
	 */
	public void createLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException;

	/**
	 * 获取项目下所有要抄的表
	 * @return
	 */
	public List<MeterSettingVo> findByProjectIdAndChargeableYes();
	
	/**
	 * 删除阶梯收费
	 * @param id
	 * @throws LiefengException
	 */
	public void deleteLadderFeeSetting(String id) throws LiefengException;

	/**
	 * 阶梯收费列表分页
	 * @param projectId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public DataPageValue<LadderFeeSettingVo> findLadderFeeSetting4Page(
			String projectId,Integer currentPage,Integer pageSize);

	/**
	 * 通过Id获取阶梯收费
	 * @param id
	 * @return
	 */
	public LadderFeeSettingVo findLadderFeeSettingById(String id);

	public void updateLadderFeeSetting(LadderFeeSettingVo ladderFeeSettingVo)
			throws LiefengException;

	/**
	 * 获取上期读数
	 * @param date 本期日期(根据这时间来获取上期日期)
	 * @return
	 */
	public MeterRecordVo getPreMeterRecord(MeterRecordVo meterRecordVo,Date date);

	/**
	 * 获取项目下要收取的仪表
	 * @param projecId 项目id
	 * @return
	 */
	public List<MeterSettingVo> findByProjectIdAndChargeableYes(String projecId);

	/**
	 * 获取项目下有权限的仪表及
	 * @param meterSettingVo
	 * @return
	 */
	public List<MeterSettingVo> getMeterAuth(String projectId,String meterOwner);

	/**
	 * 创建抄表记录(公摊)
	 * @param meterRecordVo
	 */
	public void createPublicMeterRecord(MeterRecordVo meterRecordVo);



	
}
