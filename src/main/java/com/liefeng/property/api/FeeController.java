package com.liefeng.property.api;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IFeeService;
import com.liefeng.property.vo.fee.FeeItemVo;
import com.liefeng.property.vo.fee.MeterRecordVo;

/**
 * 客户端费用管理模块API
 * 
 * @author FAN.Y
 * @date 2016年2月29日
 */
@RestController
@RequestMapping(value = "/api/fee")
public class FeeController {
	private static Logger logger = LoggerFactory.getLogger(FeeController.class);

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
	@RequestMapping("create")
	@ResponseBody
	public ReturnValue create(MeterRecordVo meterRecordVo) throws Exception {
		ContextManager.getInstance().setOemCode("liefeng");
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
	@RequestMapping("getFeeItemByFeedate")
	@ResponseBody
	public <T> DataValue<List<FeeItemVo>> getFeeItemByFeedate(String projectId,
			String houseNum, String feeType,String startDate, String endDate)
			throws Exception {
		logger.info("projectId="+projectId+" houseNum="+houseNum+" startDate ="+startDate+"  endDate="+endDate);
		Date startDate1 = TimeUtil.format(startDate, TimeUtil.PATTERN_1);
		Date endDate1 = TimeUtil.format(endDate, TimeUtil.PATTERN_1);
		List<FeeItemVo> feeItemVoList = this.feeService.getFeeItemByFeedate(
				projectId, houseNum, feeType, startDate1, endDate1);
		logger.info("feeItemVoList ="+feeItemVoList);

		return DataValue.success(feeItemVoList);
	}

}
