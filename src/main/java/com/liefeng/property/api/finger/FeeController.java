package com.liefeng.property.api.finger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.api.ro.finger.fee.FeeItemByDateRo;
import com.liefeng.property.api.ro.finger.fee.MeterRecordRo;
import com.liefeng.property.vo.fee.FeeItemVo;
import com.liefeng.property.vo.fee.MeterRecordVo;

/**
 * 客户端费用管理模块API
 * 
 * @author FAN.Y
 * @date 2016年2月29日
 */
@Api(value="费用模块")
@RestController
@RequestMapping(value = "/api/finger/fee")
public class FeeController {

	@Autowired
	private IFeeService feeService;

	/**
	 * 抄表接口
	 * 
	 * @param meterRecordVo
	 *            1：水表；2：电表；3：燃气表
	 * @return true:抄表成功
	 * @throws Exception
	 */
	@ApiOperation(value="抄表")
	@RequestMapping(value="/create" , method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue create(@Valid @ModelAttribute MeterRecordRo meterRecordRo) throws Exception {
		MeterRecordVo meterRecordVo = MyBeanUtil.createBean(meterRecordRo, MeterRecordVo.class);
		feeService.createMeterRecord(meterRecordVo);
		return ReturnValue.success();
	}

	/**
	 * 
	 * 获取该房号的所属时间段的费用数据
	 * 
	 * @param projectId
	 *            项目ID
	 * @param houseNum
	 *            房号
	 * @param feeType
	 *            费用类型：如果等于null或者''，查询所有的费用
	 * @param startDate
	 *            费用所属开始日期：日期格式2016-01-01
	 * @param endDate
	 *            费用所属结束日期：日期格式2016-01-01
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="获取该房号的所属时间段的费用数据")
	@RequestMapping(value="/getFeeItemByFeedate" , method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<FeeItemVo> getFeeItemByFeedate(@Valid @ModelAttribute FeeItemByDateRo feeItemByDateRo){
		Date startDate = TimeUtil.format(feeItemByDateRo.getStartDate(), TimeUtil.PATTERN_1);
		Date endDate = TimeUtil.format(feeItemByDateRo.getEndDate(), TimeUtil.PATTERN_1);
		List<FeeItemVo> feeItemVoList = this.feeService.getFeeItemByFeedate(
				feeItemByDateRo.getProjectId(), feeItemByDateRo.getHouseNum(), feeItemByDateRo.getFeeType(), startDate, endDate);
		return DataListValue.success(feeItemVoList);
	}

}
