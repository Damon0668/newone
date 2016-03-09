package com.liefeng.property.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.TimeUtil;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IHouseholdService;
import com.liefeng.property.constant.HouseholdConstants;
import com.liefeng.property.vo.household.CheckinQueueVo;
import com.liefeng.property.vo.household.ProprietorVo;

/**
 * 入住办理公共服务类（app） 
 * @author xhw
 * @date 2016年3月8日 下午1:45:25
 */
@RestController
@RequestMapping(value = "/household")
public class HouseholdController {

	@Autowired
	private IHouseholdService householdService;
	
	/**
	 * 通过扫二维码，获取排队号
	 * @param projectId 项目id
	 * @param buildingId 楼栋id
	 * @param houseId 房间id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月8日 下午5:12:08
	 */
	@RequestMapping("createCheckinQueue")
	@ResponseBody
	public DataValue<CheckinQueueVo> createCheckinQueue(String projectId, String houseId, String userId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		CheckinQueueVo queueVo = householdService.createCheckinQueue(projectId, houseId, userId);
		
		return DataValue.success(queueVo);
	}
	
	/**
	 * 获取排队情况中的头部信息
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @param userId 手机端用户id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午11:00:23
	 */
	@RequestMapping("getCheckinQueue")
	@ResponseBody
	public DataValue<CheckinQueueVo> getCheckinQueue(String projectId, String houseId, String userId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		CheckinQueueVo queueVo = householdService.getCheckinQueue(projectId, houseId, userId);
		
		return DataValue.success(queueVo);
	}
	
	/**
	 * 获取排队情况详情
	 * @param projectId 项目id
	 * @param page 第几页
	 * @param size 每页条数
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 上午11:19:50
	 */
	@RequestMapping("getCheckinQueueList")
	@ResponseBody
	public DataPageValue<CheckinQueueVo> getCheckinQueueList(String projectId, Integer page, Integer size) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		DataPageValue<CheckinQueueVo> queDataPageValue = householdService.getCheckinQueueOfNotStatus(projectId, HouseholdConstants.CheckinQueueStatus.UNTREATED, TimeUtil.format(new Date(), "yyyy-MM-dd"), page, size);
		
		return queDataPageValue;
	}
	
	/**
	 * 检测业主登记的状态
	 * @param proprietorId 业主id
	 * @param userId 手机端用户id
	 * @param projectId 项目id
	 * @param houseId 房间id
	 * @return 
	 * @author xhw
	 * @date 2016年3月9日 下午4:53:34
	 */
	@RequestMapping("checkProrietorStatus")
	@ResponseBody
	public ReturnValue checkProrietorStatus(String proprietorId, String userId, String projectId, String houseId) {
		ContextManager.getInstance().setOemCode("property"); //TODO
		
		householdService.checkProrietorStatus(proprietorId, userId, projectId, houseId);
		
		return ReturnValue.success();
	}
}
