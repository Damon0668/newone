package com.liefeng.property.api.finger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.EventReportDataPageRo;
import com.liefeng.property.api.ro.EventReportFlowWorkRo;
import com.liefeng.property.api.ro.EventReportRo;
import com.liefeng.property.api.ro.ProjectIdHouseNumPhoneRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.vo.workbench.EventReportVo;

/**
 * 报事服务类（app）
 * @author xhw
 * @2016年3月7日 下午3:54:17
 */
@Api(value="报事模块")
@RestController
@RequestMapping(value = "/api/finger/event")
public class EventReportController {

	@Autowired
	private IWorkbenchService workbenchService;

	/**
	 * 创建报事
	 * @param bo
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午6:03:49
	 */
	@ApiOperation(value="创建报事")
	@RequestMapping(value="/createEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventReport(@Valid @ModelAttribute EventReportRo eventReportRo) {
		EventReportBo bo = MyBeanUtil.createBean(eventReportRo, EventReportBo.class);
		workbenchService.createAppEventReport(bo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取用户的历史宝石
	 * @param projectId 项目id
	 * @param houseNum 房间号
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月20日 上午9:58:29
	 */
	@ApiOperation(value="获取用户的历史报事")
	@RequestMapping(value="/getEventReportList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<EventReportVo> getEventReportList(@Valid @ModelAttribute ProjectIdHouseNumPhoneRo projectIdHouseNumPhoneRo) {
		List<EventReportVo> eventReportVoList = workbenchService.getEventReportList(projectIdHouseNumPhoneRo.getProjectId(), projectIdHouseNumPhoneRo.getHouseNum(), projectIdHouseNumPhoneRo.getPhone());
		return DataListValue.success(eventReportVoList);
	}
	
	
	@ApiOperation(value="签收或抢单")
	@RequestMapping(value="/signforEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue signforEventReport(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReporSignfor(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="退回")
	@RequestMapping(value="/sendBackEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue eventReporSendBack(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReporSendBack(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="撤回")
	@RequestMapping(value="/withdrawEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue eventReporWithdraw(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReporSendBack(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="根据类型获取对应工单列表")
	@RequestMapping(value="/getEventReporListByType", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<EventReportVo> getEventReporListByType(@Valid @ModelAttribute EventReportDataPageRo params) {
		DataPageValue<EventReportVo> dataPage = new DataPageValue<EventReportVo>();
		
		String type = params.getType(); // 查询类型
		Integer page = params.getPage(); // 分页当前页
		Integer size = params.getSize(); // 分页大小
		EventReportBo eventReportBo = MyBeanUtil.createBean(params, EventReportBo.class);
		
		if(WorkbenchConstants.EventListType.WAIT_SIGNIN.equals(type)) {
			 // 待签收
			dataPage= workbenchService.getSignForEventReporList(eventReportBo, page, size);
		} else if(WorkbenchConstants.EventListType.GRAB_SINGLE.equals(type)) { 
			// 抢单
			dataPage= workbenchService.getGrabEventReporList(eventReportBo, page, size);
		} else if(WorkbenchConstants.EventListType.WAIT_DEAL.equals(type)) { 
			// 待处理
			dataPage= workbenchService.getWaitingForEventReportList(eventReportBo, params.getPage(), params.getSize());
		} else if(WorkbenchConstants.EventListType.FLOWING.equals(type)) {
			// 待处理
			dataPage= workbenchService.getFlowingEventReporList(eventReportBo, params.getPage(), params.getSize());
		} else if(WorkbenchConstants.EventListType.COMPLETED.equals(type)) {
			 // 已完成
			dataPage= workbenchService.getCompleteEventReporList(eventReportBo, params.getPage(), params.getSize());
		}
		
		return dataPage;
	}
}
