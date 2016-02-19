package com.liefeng.intf.property;

import com.liefeng.core.entity.DataPageValue;
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
	public void saveMeterRecord(MeterRecordVo meterRecordVo);

	/**
	 * 抄表列表 所有房产及抄表数据集结合(全部房产为主)
	 * @param meterRecordVo
	 */
	public void listMeterRecord(MeterRecordVo meterRecordVo);

	/**
	 * 创建仪表设置
	 * @param meterSettingPo
	 */
	public void saveMeterSetting(MeterSettingVo meterSettingVo);

	/**
	 * 获取仪表设置列表
	 * @param string
	 * @param i
	 * @param j
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
	public void updateMeterSetting(MeterSettingVo meterSettingVo);

	/**
	 * 删除仪表设置
	 * @param id
	 */
	public void deleteMeterSetting(String id);
}
