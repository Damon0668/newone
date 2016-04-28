package com.liefeng.property.api.work;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.helper.JsonHelper;
import org.snaker.engine.model.FieldModel;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.DataValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.intf.property.IApprovalFlowService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.service.workflow.IWorkflowService;
import com.liefeng.property.api.ro.id.StaffIdRo;
import com.liefeng.property.api.ro.work.workFlow.BackTaskRo;
import com.liefeng.property.api.ro.work.workFlow.DeleteOrderRo;
import com.liefeng.property.api.ro.work.workFlow.GetActiveTaskRo;
import com.liefeng.property.api.ro.work.workFlow.GetDefaultUserRo;
import com.liefeng.property.api.ro.work.workFlow.GetFieldsRo;
import com.liefeng.property.api.ro.work.workFlow.GetListDataRo;
import com.liefeng.property.api.ro.work.workFlow.GetUserRo;
import com.liefeng.property.api.ro.work.workFlow.InitProcessRo;
import com.liefeng.property.api.ro.work.workFlow.OrderIdRo;
import com.liefeng.property.api.ro.work.workFlow.StartOrExecuteRo;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.constant.ApprovalFlowConstants;
import com.liefeng.property.vo.approvalFlow.ApprovalUserVo;
import com.liefeng.property.vo.approvalFlow.HistoryTaskVo;
import com.liefeng.property.vo.approvalFlow.ProcessVo;
import com.liefeng.property.vo.approvalFlow.TaskModelVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
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
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@ApiOperation(value="获取流程表单的信息")
	@RequestMapping(value="/getFields", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<FieldModel> getFields(@Valid @ModelAttribute GetFieldsRo getFieldsRo){
		List<FieldModel> fieldModels = approvalFlowService.getFields(getFieldsRo.getProcessId(),getFieldsRo.getTaskName(),getFieldsRo.getStaffId());
		return DataListValue.success(fieldModels);
	}
	
	@ApiOperation(value="获取流程表单的值")
	@RequestMapping(value="/getValue", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Map<String, Object>> getValue(@Valid @ModelAttribute OrderIdRo orderIdRo){
		Map<String, Object> variableMap = workflowService.findHisOrderById(orderIdRo.getOrderId()).getVariableMap();
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
	public DataListValue<TaskModelVo> getNextTask(@Valid @ModelAttribute GetFieldsRo getFieldsRo){
		List<TaskModel> taskModels = approvalFlowService.getNextTask(getFieldsRo.getProcessId(),getFieldsRo.getTaskName());
		List<TaskModelVo>  taskModelVos = new ArrayList<TaskModelVo>();
		
		//这行注释的代码 中间会出现null 不知道为什么 ，只能自己手动赋值
		//List<TaskModelVo>  taskModelVos = MyBeanUtil.createList(taskModels, TaskModelVo.class);
		
		for (TaskModel taskModelVo : taskModels) {
			TaskModelVo modelVo = new TaskModelVo();
			modelVo.setAssignee(taskModelVo.getAssignee());
			modelVo.setDisplayName(taskModelVo.getDisplayName());
			modelVo.setName(taskModelVo.getName());
			taskModelVos.add(modelVo);
		}
		return DataListValue.success(taskModelVos);
	}
	
	@ApiOperation(value="获取办理人列表")
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<ApprovalUserVo> getUser(@Valid @ModelAttribute GetUserRo getUserRo){
		List<PropertyStaffVo> users = approvalFlowService.getUser(getUserRo.getOrderId(), getUserRo.getAssignee(),getUserRo.getStaffId());
		PropertyStaffVo defaultUser = null;
		if(ValidateHelper.isNotEmptyString(getUserRo.getOrderId())){
			defaultUser = approvalFlowService.getDefaultUsers(getUserRo.getOrderId(), getUserRo.getTaskName());
		}
		
		if(defaultUser != null ){
			List<ApprovalUserVo> approvalUsers = new  ArrayList<ApprovalUserVo>();
			
			//用来判断是否存在
			Map<String, PropertyStaffVo> existMap = new HashMap<String, PropertyStaffVo>();
			
			//设置默认办理人
			ApprovalUserVo approvalUserVo =  MyBeanUtil.createBean(defaultUser, ApprovalUserVo.class);
			approvalUserVo.setIsDefault("1"); //默认
			approvalUsers.add(approvalUserVo);
			existMap.put(defaultUser.getId(),defaultUser);
			
			//循环去重
			for (PropertyStaffVo propertyStaffVo : users) {
				if(!existMap.containsKey(propertyStaffVo.getId())){
					existMap.put(propertyStaffVo.getId(),propertyStaffVo);
					ApprovalUserVo noApprovalUserVo = MyBeanUtil.createBean(propertyStaffVo, ApprovalUserVo.class);
					noApprovalUserVo.setIsDefault("0");//不是默认
					approvalUsers.add(noApprovalUserVo);
				}
			}
			
			return DataListValue.success(approvalUsers);
		}else{
			return DataListValue.success(MyBeanUtil.createList(users, ApprovalUserVo.class));
		}
		
		
	}
	
	@ApiOperation(value="获取默认办理人")
	@RequestMapping(value="/getDefaultUser", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<PropertyStaffVo> getDefaultUser(@Valid @ModelAttribute GetDefaultUserRo getUserRo){
		PropertyStaffVo propertyStaffVo = approvalFlowService.getDefaultUsers(getUserRo.getOrderId(), getUserRo.getTaskName());
		return DataValue.success(propertyStaffVo);
	}
	
	@ApiOperation(value="获取列表数据")
	@RequestMapping(value="/getListData", method=RequestMethod.GET)
	@ResponseBody
	public DataPageValue<WorkItem> getListData(@Valid @ModelAttribute  GetListDataRo getListDataRo){
		
		String operator = ApprovalFlowConstants.USER_PREFIXES+getListDataRo.getStaffId();
		DataPageValue<WorkItem> dataPageValue = null;
		switch (getListDataRo.getType()) {
		
		case "1"://待办理
			dataPageValue = workflowService.getWorkItems(new QueryFilter().setOperator(operator), getListDataRo.getPage(),  getListDataRo.getSize());
			List<WorkItem> workItems1 = dataPageValue.getDataList();
			for (WorkItem workItem : workItems1) {
				Map<String, Object> taskVariable = workItem.getTaskVariableMap();
				taskVariable.put("stayTime", getStaytime(workItem.getTaskCreateTime()));
				workItem.setTaskVariable(JsonHelper.toJson(taskVariable));
			}
			break;
		case "2"://已办结
			dataPageValue = workflowService.listFlowingOrder(new QueryFilter().setOperator(operator), getListDataRo.getPage(),  getListDataRo.getSize());
			break;
		case "3"://我发起的
			dataPageValue = workflowService.listMyCreate(new QueryFilter().setOperator(operator), getListDataRo.getPage(),  getListDataRo.getSize());
			List<WorkItem> workItems3 = dataPageValue.getDataList();
			for (WorkItem workItem : workItems3) {
				if(workItem.getTaskCreateTime() != null) {
					Map<String, Object> taskVariable = workItem.getTaskVariableMap();
					taskVariable.put("stayTime", getStaytime(workItem.getTaskCreateTime()));
					workItem.setTaskVariable(JsonHelper.toJson(taskVariable));
				}
				//设置办理人
				String[] actors = workflowService.getTaskActorsByTaskId(workItem.getTaskId());
				String actor="";
				if(actors != null)
				for (String string : actors) {
					actor += string.replace(ApprovalFlowConstants.USER_PREFIXES, "")+",";
				}
				if(actor.length() > 0)
				actor = actor.substring(0,actor.length()-1);
				
				String operators = "";
				List<PropertyStaffVo> propertyStaffVos = propertyStaffService.findPropertyStaffById4DPList(actor);
				if( propertyStaffVos != null)
				for (PropertyStaffVo propertyStaffVo : propertyStaffVos) {
					operators += propertyStaffVo.getName()+",";
				}
				if(operators.length() > 0)
				operators = operators.substring(0,operators.length()-1);
				
				workItem.setOperator(operators);
			}
			break;
		default:
			break;
		}
		
		return dataPageValue;
	}
	
	@ApiOperation(value="获取某个流程的历史任务")
	@RequestMapping(value="/getHistTask", method=RequestMethod.GET)
	@ResponseBody
	public DataListValue<HistoryTaskVo> getHistTask(@Valid @ModelAttribute OrderIdRo orderIdRo){
		return approvalFlowService.getHistTaskByOrderId(orderIdRo.getOrderId());
	}
	
	@ApiOperation(value="获取某个流程的当前活动任务,null为无法操作当前任务")
	@RequestMapping(value="/getActiveTask", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<Task> getActiveTask(@Valid @ModelAttribute GetActiveTaskRo getActiveTaskRo){
		String operator = ApprovalFlowConstants.USER_PREFIXES+getActiveTaskRo.getStaffId();
		List<Task> tasks = workflowService.getActiveTasks(new QueryFilter().setOrderId(getActiveTaskRo.getOrderId()).setOperator(operator));
		
		if(tasks != null && tasks.size() > 0){
			return DataValue.success(tasks.get(0));
		}
		return DataValue.success(null);
	}
	
	@ApiOperation(value="开始或执行流程")
	@RequestMapping(value="/startOrExecute", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue startOrExecute(@Valid @ModelAttribute StartOrExecuteRo startOrExecuteRo){
		ApprovalFlowBo approvalFlowBo = MyBeanUtil.createBean(startOrExecuteRo, ApprovalFlowBo.class);
		approvalFlowService.startOrExecute(approvalFlowBo);
		return ReturnValue.success();
	}
	
	@ApiOperation(value="初始化流程定义")
	@RequestMapping(value="/initProcess", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue initProcess(){
		workflowService.initFlows();
		return ReturnValue.success();
	}
	
	@ApiOperation(value="获取统计数据")
	@RequestMapping(value="/getCountsToHead", method=RequestMethod.GET)
	@ResponseBody
	public DataValue<HeadCountVo> getCountsToHead(@Valid @ModelAttribute StaffIdRo staffIdRo) {
		HeadCountVo headCount = new HeadCountVo();

		DataPageValue<WorkItem> pages = workflowService.getWorkItems(new QueryFilter().setOperator(ApprovalFlowConstants.USER_PREFIXES+staffIdRo.getStaffId()),1,1);
		headCount.setWorkFlowWaitCount(pages.getMaxCount());
		
		DataPageValue<WorkItem> flowing =	workflowService.listFlowingOrder(new QueryFilter().setOperator(ApprovalFlowConstants.USER_PREFIXES+staffIdRo.getStaffId()), 1, 1);
		headCount.setWorkFlowAlreadyCount(flowing.getMaxCount());
		
		return DataValue.success(headCount);
	}
	
	@ApiOperation(value="删除流程")
	@RequestMapping(value="/deleteOrder", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue deleteOrder(@Valid @ModelAttribute DeleteOrderRo deleteOrderRo){
		approvalFlowService.deleteOrder(deleteOrderRo.getProcessId(), deleteOrderRo.getOrderId(), deleteOrderRo.getStaffId());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="撤回/收回")
	@RequestMapping(value="/backTask", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue backTask(@Valid @ModelAttribute BackTaskRo backTaskRo){
		approvalFlowService.backTask(backTaskRo.getTaskId(), backTaskRo.getStaffId());
		return ReturnValue.success();
	}
	
	@ApiOperation(value="指定流程发布")
	@RequestMapping(value="/initProcess2", method=RequestMethod.POST)
	@ResponseBody
	public ReturnValue initProcess2(@Valid @ModelAttribute InitProcessRo initProcessRo){
		workflowService.initFlows(initProcessRo.getProcessName(),initProcessRo.getOemCode());
		return ReturnValue.success();
	}
	
	public static String getStaytime(String tranTime) {
    	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(ValidateHelper.isEmptyString(tranTime))
    		return "";
    	try {
			Date startTime = sdf.parse(tranTime);
			Date endTime = Calendar.getInstance().getTime();
			long diff = endTime.getTime() - startTime.getTime();
			long time = diff / (1000 * 60 * 60 );
			if(time<1){
				return diff / (1000 * 60 ) +"分钟"; 
			} else if(time>24) {
				long days = time/24;
				long hours = time%24;
				return days+"天"+hours+"小时"; 				
			} else {
				return time+"小时"; 				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;   	  	
    }

}
