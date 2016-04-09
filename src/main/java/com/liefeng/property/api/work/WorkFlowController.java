package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.model.FieldModel;
import org.snaker.engine.model.TaskModel;
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
import com.liefeng.intf.property.IApprovalFlowService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.intf.service.workflow.IWorkflowService;
import com.liefeng.property.api.ro.ExecuteEventReportRo;
import com.liefeng.property.api.ro.common.EventAccepterEvalRo;
import com.liefeng.property.api.ro.common.PhoneRo;
import com.liefeng.property.api.ro.id.EventIdRo;
import com.liefeng.property.api.ro.id.ProjectIdRo;
import com.liefeng.property.api.ro.work.event.CountsToHeadRo;
import com.liefeng.property.api.ro.work.event.DefaultAccepterRo;
import com.liefeng.property.api.ro.work.event.DispatchingWorkerRo;
import com.liefeng.property.api.ro.work.event.EventReportDataPageRo;
import com.liefeng.property.api.ro.work.event.EventReportDetailRo;
import com.liefeng.property.api.ro.work.event.EventReportFlowWorkRo;
import com.liefeng.property.api.ro.work.event.EventReportListByTypeRo;
import com.liefeng.property.api.ro.work.event.EventReportRo;
import com.liefeng.property.api.ro.work.workFlow.GetDefaultUserRo;
import com.liefeng.property.api.ro.work.workFlow.GetFieldsRo;
import com.liefeng.property.api.ro.work.workFlow.GetUserRo;
import com.liefeng.property.api.ro.work.workFlow.OrderIdRo;
import com.liefeng.property.api.ro.work.workFlow.StartOrExecuteRo;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.bo.workbench.EventReportBo;
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.domain.workbench.EventReportContext;
import com.liefeng.property.vo.ApprovalFlow.ProcessVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffContactVo;
import com.liefeng.property.vo.workbench.EventAccepterEvalVo;
import com.liefeng.property.vo.workbench.EventProcessVo;
import com.liefeng.property.vo.workbench.EventReportVo;
import com.liefeng.property.vo.workbench.HeadCountVo;

/**
 * 工作流
 * @author wuzhijing
 * @date 2016-4-9 15:12:03
 */
@Api(value="工作流模块")
@RestController("workFlowController")
@RequestMapping(value = "/api/work/workFlow")
public class WorkFlowController {

	private static Logger logger = LoggerFactory.getLogger(WorkFlowController.class);
	
	@Autowired
	private IApprovalFlowService approvalFlowService;
	
	@Autowired
	private IWorkflowService workflowService;
	
	@ApiOperation(value="获取流程表单的信息")
	@RequestMapping(value="/getFields", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<FieldModel> getFields(@Valid @ModelAttribute GetFieldsRo getFieldsRo){
		List<FieldModel> fieldModels = approvalFlowService.getFields(getFieldsRo.getProcessId(),getFieldsRo.getTaskName());
		return DataListValue.success(fieldModels);
	}
	
	@ApiOperation(value="获取流程表单的值")
	@RequestMapping(value="/getValue", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Map<String, Object>> getValue(@Valid @ModelAttribute OrderIdRo orderIdRo){
		Map<String, Object> variableMap = workflowService.findOrderById(orderIdRo.getOrderId()).getVariableMap();
		return DataValue.success(variableMap);
	}
	
	@ApiOperation(value="获取所有审批流程定义")
	@RequestMapping(value="/getProcessList", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<ProcessVo> getProcessList(){
		List<ProcessVo> processVos = approvalFlowService.getProcessList();
		return DataListValue.success(processVos);
	}
	
	@ApiOperation(value="获取下一个步骤")
	@RequestMapping(value="/getNextTask", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<TaskModel> getNextTask(@Valid @ModelAttribute GetFieldsRo getFieldsRo){
		List<TaskModel> taskModels = approvalFlowService.getNextTask(getFieldsRo.getProcessId(),getFieldsRo.getTaskName());
		return DataListValue.success(taskModels);
	}
	
	@ApiOperation(value="获取办理人列表")
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<PropertyStaffVo> getUser(@Valid @ModelAttribute GetUserRo getUserRo){
		List<PropertyStaffVo> propertyStaffVos = approvalFlowService.getUser(getUserRo.getOrderId(), getUserRo.getAssignee());
		return DataListValue.success(propertyStaffVos);
	}
	
	@ApiOperation(value="获取默认办理人")
	@RequestMapping(value="/getDefaultUser", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<PropertyStaffVo> getDefaultUser(@Valid @ModelAttribute GetDefaultUserRo getUserRo){
		PropertyStaffVo propertyStaffVo = approvalFlowService.getDefaultUsers(getUserRo.getOrderId(), getUserRo.getTaskName());
		return DataValue.success(propertyStaffVo);
	}
	
	@ApiOperation(value="开始或执行流程")
	@RequestMapping(value="/startOrExecute", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue startOrExecute(@Valid @ModelAttribute StartOrExecuteRo startOrExecuteRo){
		ApprovalFlowBo approvalFlowBo = MyBeanUtil.createBean(startOrExecuteRo, ApprovalFlowBo.class);
		approvalFlowService.startOrExecute(approvalFlowBo);
		return ReturnValue.success();
	}
	
	
	
}
