package com.liefeng.property.api.finger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
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
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.api.ro.common.EventAccepterEvalRo;
import com.liefeng.property.api.ro.finger.event.EventReportRo;
import com.liefeng.property.api.ro.finger.household.ProjectIdHouseNumPhoneRo;
import com.liefeng.property.api.ro.id.EventIdRo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.workbench.EventAccepterEvalVo;
import com.liefeng.property.vo.workbench.EventProcessVo;
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
	@ApiOperation(value="创建报事", notes="用户[业主|住户]创建报事")
	@RequestMapping(value="/createEventReport", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventReport(@Valid @ModelAttribute EventReportRo eventReportRo) {
		EventReportBo bo = MyBeanUtil.createBean(eventReportRo, EventReportBo.class);
		workbenchService.createAppEventReport(bo);
		
		return ReturnValue.success();
	}
	
	/**
	 * 获取用户的历史报事
	 * @param projectId 项目id
	 * @param houseNum 房间号
	 * @param phone 手机号码
	 * @return 
	 * @author xhw
	 * @date 2016年3月20日 上午9:58:29
	 */
	@ApiOperation(value="获取用户的历史报事", notes="用户[业主|住户]获取历史报事")
	@RequestMapping(value="/getEventReportList", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<EventReportVo> getEventReportList(@Valid @ModelAttribute ProjectIdHouseNumPhoneRo projectIdHouseNumPhoneRo) {
		List<EventReportVo> eventReportVoList = workbenchService.getEventReportList(projectIdHouseNumPhoneRo.getProjectId(), projectIdHouseNumPhoneRo.getHouseNum(), projectIdHouseNumPhoneRo.getPhone());
		return DataListValue.success(eventReportVoList);
	}
	
	/**
	 * 报事评价
	 * @param eventAccepterEvalRo
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午2:28:29
	 */
	@ApiOperation(value="获取报事办理人", notes="用户[业主|住户]对报事进行评价时，获取报事的办理人")
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
	@ApiOperation(value="报事评价", notes="用户[业主|住户]对报事进行评价")
	@RequestMapping(value="/createEventAccepterEval", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue createEventAccepterEval(@Valid @ModelAttribute EventAccepterEvalRo eventAccepterEvalRo) {
	
		
		EventProcessVo eventProcessVo = workbenchService.getActiveEventProcess(eventAccepterEvalRo.getWfOrderId(), "");
		eventProcessVo.setRevisitMode(WorkbenchConstants.ReturnVisitType.CUSTOMER);
		eventProcessVo.setTimeliness(eventAccepterEvalRo.getTimeliness());
		eventProcessVo.setLevel(eventAccepterEvalRo.getLevel());
		eventProcessVo.setAttitude(eventAccepterEvalRo.getAttitude());
		eventProcessVo.setResult(eventAccepterEvalRo.getResult());
		
		EventReportVo eventReportVo = new EventReportVo();
		eventReportVo.setId(eventAccepterEvalRo.getEventId());
		workbenchService.executeEventReportFlow(eventReportVo, eventProcessVo, WorkbenchConstants.EventReport.RETURNVISIT_OWNER, "");
		
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
			//workbenchService.createEventAccepterEval(eventAccepterEvalVo);
		}
		workbenchService.createEventAccpterEvalList(eventAccepterEvalVoList);
		return ReturnValue.success();
	}
}
