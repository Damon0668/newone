package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
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
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.ExecuteEventReportRo;
import com.liefeng.property.api.ro.common.EventAccepterEvalRo;
import com.liefeng.property.api.ro.common.PhoneRo;
import com.liefeng.property.api.ro.id.EventIdRo;
import com.liefeng.property.api.ro.id.ProjectIdRo;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.event.CountsToHeadRo;
import com.liefeng.property.api.ro.work.event.DefaultAccepterRo;
import com.liefeng.property.api.ro.work.event.DispatchingWorkerRo;
import com.liefeng.property.api.ro.work.event.EventReportDataPageRo;
import com.liefeng.property.api.ro.work.event.EventReportDetailRo;
import com.liefeng.property.api.ro.work.event.EventReportFlowWorkRo;
import com.liefeng.property.api.ro.work.event.EventReportListByTypeRo;
import com.liefeng.property.api.ro.work.event.EventReportRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.domain.workbench.EventReportContext;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffContactVo;
import com.liefeng.property.vo.workbench.EventAccepterEvalVo;
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
		workbenchService.eventReportSignfor(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="根据类型获取对应工单列表")
	@RequestMapping(value="/getEventReportListByType", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<EventReportVo> getEventReportListByType(@Valid @ModelAttribute EventReportListByTypeRo params) {
		DataPageValue<EventReportVo> dataPage = new DataPageValue<EventReportVo>();
		
		String type = params.getType(); // 查询类型
		Integer page = params.getPage(); // 分页当前页
		Integer size = params.getSize(); // 分页大小
		EventReportBo eventReportBo = MyBeanUtil.createBean(params, EventReportBo.class);
		
		if(WorkbenchConstants.EventListType.WAIT_DEAL.equals(type)) { 
			// 待处理
			dataPage= workbenchService.getWaitingForEventReportList(eventReportBo, page, size);
		} else if(WorkbenchConstants.EventListType.FLOWING.equals(type)) {
			// 流转中
			dataPage= workbenchService.getFlowingEventReportList(eventReportBo, page, size);
		} else if(WorkbenchConstants.EventListType.COMPLETED.equals(type)) {
			 // 已完成
			dataPage= workbenchService.getCompleteEventReportList(eventReportBo, page, size);
		}
		
		return dataPage;
	}
	
	@ApiOperation(value="获取工单详情")
	@RequestMapping(value="/getEventReportDetail", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<EventReportVo> getEventReportDetail(@Valid @ModelAttribute EventReportDetailRo params) {
		String orderId = params.getOrderId();
		String staffId = params.getStaffId();
		logger.info("查询工单详情，orderId={}，staffId={}", orderId, staffId);
		
		EventReportVo eventReport =  workbenchService.findEventReportByWfOrderId(orderId); // 工单详情信息
		
		List<EventProcessVo> dataList = new ArrayList<EventProcessVo>();
		if(eventReport != null) {
			dataList = workbenchService.getHisEventProcess(params.getOrderId()); // 工单处理历史信息
		}
		
		EventProcessVo currentEventProcess = workbenchService.getActiveEventProcess(orderId, staffId); // 工单当前任务信息
		if(currentEventProcess != null)
		dataList.add(currentEventProcess);
		
		eventReport.setEventProcessList(dataList);
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
	
	/**
	 * 创建报事
	 * @param bo
	 * @return 
	 * @author xhw
	 * @date 2016年3月15日 下午6:03:49
	 */
	@ApiOperation(value="创建报事", notes="员工创建报事")
	@RequestMapping(value="/createEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventReport(@Valid @ModelAttribute EventReportRo eventReportRo) {
		logger.info("【debug】"+eventReportRo.toString());
		EventReportBo bo = MyBeanUtil.createBean(eventReportRo, EventReportBo.class);
		workbenchService.createAppEventReport(bo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取员工的所有报事
	 * @param phoneRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午7:41:04
	 */
	@ApiOperation(value="获取员工的历史报事", notes="员工获取员工的历史报事")
	@RequestMapping(value="/getEventReportList", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<EventReportVo> getEventReportList(@Valid @ModelAttribute PhoneRo phoneRo) {
		List<EventReportVo> eventReportVoList = workbenchService.getEventReportList("", "", phoneRo.getPhone());
		return DataListValue.success(eventReportVoList);
	}
	
	/**
	 * 获取报事办理人
	 * @param eventAccepterEvalRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午2:28:29
	 */
	@ApiOperation(value="获取报事办理人", notes="获取报事办理人（移动办公）")
	@RequestMapping(value="/getEventStaffList", method=RequestMethod.POST)
	@ResponseBody
	public DataListValue<PropertyStaffVo> getEventStaffList(@Valid @ModelAttribute EventIdRo eventIdRo) {
		List<PropertyStaffVo> staffVoList = workbenchService.getStaffList(eventIdRo.getEventId());
		
		return DataListValue.success(staffVoList);
	}
	
	/**
	 * 报事评价
	 * @param eventAccepterEvalRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午2:28:29
	 */
	@ApiOperation(value="报事评价", notes="报事评价（移动办公）")
	@RequestMapping(value="/createEventAccepterEval", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventAccepterEval(@Valid @ModelAttribute EventAccepterEvalRo eventAccepterEvalRo) {
	
		
		EventProcessVo eventProcessVo = workbenchService.getActiveEventProcess(eventAccepterEvalRo.getWfOrderId(), eventAccepterEvalRo.getUserId());
		eventProcessVo.setRevisitMode(WorkbenchConstants.ReturnVisitType.STAFF); 
		eventProcessVo.setTimeliness(eventAccepterEvalRo.getTimeliness());
		eventProcessVo.setLevel(eventAccepterEvalRo.getLevel());
		eventProcessVo.setAttitude(eventAccepterEvalRo.getAttitude());
		eventProcessVo.setResult(eventAccepterEvalRo.getResult());
		
		EventReportVo eventReportVo = new EventReportVo();
		eventReportVo.setId(eventAccepterEvalRo.getEventId());
		workbenchService.executeEventReportFlow(eventReportVo, eventProcessVo, eventAccepterEvalRo.getUserId(), "");
		
		String[] accpterLikes = eventAccepterEvalRo.getAccepterLikes().split(",");
		
		List<EventAccepterEvalVo> eventAccepterEvalVoList = new ArrayList<EventAccepterEvalVo>();
		for(int i = 0; i < accpterLikes.length; i++){
			String[] likes = accpterLikes[i].split("-");
			EventAccepterEvalVo eventAccepterEvalVo = new EventAccepterEvalVo();
			eventAccepterEvalVo.setId(UUIDGenerator.generate());
			eventAccepterEvalVo.setCreateTime(new Date());
			eventAccepterEvalVo.setAccepterId(likes[0]);
			eventAccepterEvalVo.setLikes(likes[1]);
			eventAccepterEvalVo.setEventId(eventAccepterEvalRo.getEventId());
			
			eventAccepterEvalVoList.add(eventAccepterEvalVo);
		}
		workbenchService.createEventAccpterEvalList(eventAccepterEvalVoList);
		return ReturnValue.success();
	}
	
	@ApiOperation(value="退回")
	@RequestMapping(value="/sendBackEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue eventReportSendBack(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReportSendBack(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="撤回")
	@RequestMapping(value="/withdrawEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue eventReportWithdraw(@Valid @ModelAttribute EventReportFlowWorkRo reportFlowWorkRo){
		workbenchService.eventReporWithdraw(reportFlowWorkRo.getWfTaskId(), reportFlowWorkRo.getStaffid());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="获取待签收列表")
	@RequestMapping(value="/getSignForEventReportList", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<EventReportVo> getSignForEventReportList(@Valid @ModelAttribute EventReportDataPageRo eventReportDataPageRo){
		EventReportBo eventReportBo = MyBeanUtil.createBean(eventReportDataPageRo, EventReportBo.class);
		DataPageValue<EventReportVo> DataPageValue = workbenchService.getSignForEventReportList(eventReportBo, eventReportDataPageRo.getPage(),eventReportDataPageRo.getSize());
		return DataPageValue;
	}
	
	@ApiOperation(value="获取抢单列表")
	@RequestMapping(value="/getGrabEventReportList", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<EventReportVo> getGrabEventReportList(@Valid @ModelAttribute EventReportDataPageRo eventReportDataPageRo){
		EventReportBo eventReportBo = MyBeanUtil.createBean(eventReportDataPageRo, EventReportBo.class);
		DataPageValue<EventReportVo> DataPageValue = workbenchService.getGrabEventReportList(eventReportBo, eventReportDataPageRo.getPage(),eventReportDataPageRo.getSize());
		return DataPageValue;
	}
	
	@ApiOperation(value="执行报事流程/领导审核通过/领导审核不通过/办理/派工")
	@RequestMapping(value="/executeEventReportFlow", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue executeEventReportFlow(@Valid @ModelAttribute ExecuteEventReportRo executeEventReportRo){
		EventReportVo eventReportVo = new EventReportVo();
		eventReportVo.setId(executeEventReportRo.getEventId());
		
		EventProcessVo eventProcessVo = MyBeanUtil.createBean(executeEventReportRo, EventProcessVo.class);
		
		workbenchService.executeEventReportFlow(eventReportVo, eventProcessVo,eventProcessVo.getCurrAccepterId(),eventProcessVo.getNextAccepterId());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="获取派工人员列表")
	@RequestMapping(value="/getDispatchingWorker", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<StaffContactVo> getDispatchingWorker(@Valid @ModelAttribute DispatchingWorkerRo dispatchingWorkerRo){
		List<StaffContactVo> contactVos = workbenchService.findDispatchingWorker(dispatchingWorkerRo.getProjectId(),dispatchingWorkerRo.getStaffId());
		return  DataListValue.success(contactVos);
	}
	
	@ApiOperation(value="获取默认办理人")
	@RequestMapping(value="/getDefaultAcceptor", method=RequestMethod.GET)
	@ResponseBody
	public PropertyStaffVo getDefaultAcceptor(@Valid @ModelAttribute DefaultAccepterRo defaultAccepterRo ){
		PropertyStaffVo propertyStaffVo = workbenchService.getTaskAccepter(defaultAccepterRo.getEventId(), defaultAccepterRo.getTaskName());
		return  propertyStaffVo;
	}
	
	@ApiOperation(value="获取所有部门领导")
	@RequestMapping(value="/getDepartmentDirectorList", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<StaffContactVo> getDepartmentDirectorList(@Valid @ModelAttribute ProjectIdRo projectIdRo ){
		List<PropertyStaffVo> propertyStaffVos = workbenchService.getDepartmentDirectorList(projectIdRo.getProjectId(),null);
		List<StaffContactVo> contactVos = new ArrayList<StaffContactVo>();
		for (PropertyStaffVo propertyStaffVo : propertyStaffVos) {
			StaffContactVo contactVo = new StaffContactVo();
			
			contactVo.setDepartmentId(propertyStaffVo.getDepartmentId());
			contactVo.setDepartmentName(propertyStaffVo.getName());
			List<PropertyStaffVo> staffContactVos = new ArrayList<PropertyStaffVo>();
			staffContactVos.add(propertyStaffVo);
			contactVo.setStaffList(staffContactVos);
			
			contactVos.add(contactVo);
		}
		return DataListValue.success(contactVos);
	}
	
	@ApiOperation(value="获取某个步骤的执行人的部门所有人")
	@RequestMapping(value="/getDepartmentWorker", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<StaffContactVo> getDepartmentWorker(@Valid @ModelAttribute DefaultAccepterRo defaultAccepterRo){
		PropertyStaffVo propertyStaffVo = workbenchService.getTaskAccepter(defaultAccepterRo.getEventId(), defaultAccepterRo.getTaskName());
		EventReportVo eventReportVo = workbenchService.getEventReport(defaultAccepterRo.getEventId());
		List<StaffContactVo> contactVos = workbenchService.findDispatchingWorker(eventReportVo.getProjectId(),propertyStaffVo.getId());
		return  DataListValue.success(contactVos);
	}
	
	/**
	 * 获取今日点赞
	 * @param dispatchingWorkerRo
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:59:43
	 */
	@ApiOperation(value="获取今日点赞")
	@RequestMapping(value="/getLikesOfToday", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Long> getLikesOfToday(@Valid @ModelAttribute StaffIdRo staffIdRo){
		long likes = workbenchService.getLikesOfToday(staffIdRo.getStaffId());
		return  DataValue.success(likes);
	}
	
	/**
	 * 获取历史点赞
	 * @param dispatchingWorkerRo
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:59:43
	 */
	@ApiOperation(value="获取历史点赞")
	@RequestMapping(value="/getAllLikes", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Long> getAllLikes(@Valid @ModelAttribute StaffIdRo staffIdRo){
		long likes = workbenchService.getAllLikes(staffIdRo.getStaffId());
		return  DataValue.success(likes);
	}
	
}
