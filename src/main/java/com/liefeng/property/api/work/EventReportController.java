package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.CountsToHeadRo;
import com.liefeng.property.api.ro.EventReportDataPageRo;
import com.liefeng.property.api.ro.EventReportDetailRo;
import com.liefeng.property.api.ro.EventReportFlowWorkRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.domain.workbench.EventReportContext;
import com.liefeng.property.vo.workbench.EventProcessVo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.HeadCountVo;

/**
 * 报事服务类（app）
 * @author ZhenTingJun
 * @date 2016年3月7日 下午3:54:17
 */
@Api(value="报事模块")
@RestController("workEventReportController")
@RequestMapping(value = "/api/work/event")
public class EventReportController {

	private static Logger logger = LoggerFactory.getLogger(EventReportController.class);
	
	@Autowired
	private IWorkbenchService workbenchService;
	
	@ApiOperation(value="签收或抢单")
	@RequestMapping(value="/signforEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue signforEventReport(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReporSignfor(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
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
			// 流转中
			dataPage= workbenchService.getFlowingEventReporList(eventReportBo, params.getPage(), params.getSize());
		} else if(WorkbenchConstants.EventListType.COMPLETED.equals(type)) {
			 // 已完成
			dataPage= workbenchService.getCompleteEventReporList(eventReportBo, params.getPage(), params.getSize());
		}
		
		return dataPage;
	}
	
	@ApiOperation(value="获取工单详情")
	@RequestMapping(value="/getEventReportDetail", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<EventReportVo> getEventReportDetail(@Valid @ModelAttribute EventReportDetailRo params) {
		EventReportVo eventReport =  workbenchService.findEventReportByWfOrderId(params.getOrderId()); 
		
		if(eventReport != null) {
			List<EventProcessVo> dataList = workbenchService.getHisEventProcess(params.getOrderId());
			eventReport.setEventProcessList(dataList);
		}
		
		return DataValue.success(eventReport);
	}
	
	@ApiOperation(value="获取头部数量统计数据")
	@RequestMapping(value="/getCountsToHead", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<HeadCountVo> getCountsToHead(@Valid @ModelAttribute CountsToHeadRo params) {
		EventReportBo eventReportBo = MyBeanUtil.createBean(params, EventReportBo.class);
		EventReportContext reportContext = EventReportContext.build();
		HeadCountVo headCount = reportContext.getCountsToHead(eventReportBo);
		
		return DataValue.success(headCount);
	}
}
