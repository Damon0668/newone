package com.liefeng.property.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.helper.JsonHelper;
import org.snaker.engine.model.FieldModel;
import org.snaker.engine.model.NodeModel;
import org.snaker.engine.model.ProcessModel;
import org.snaker.engine.model.TaskModel;
import org.snaker.engine.model.TransitionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.DataListValue;
import com.liefeng.intf.property.IApprovalFlowService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.service.msg.IPushMsgService;
import com.liefeng.intf.service.workflow.IWorkflowService;
import com.liefeng.property.api.ro.work.workFlow.OrderIdRo;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.constant.ApprovalFlowConstants;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.error.ApprovalFlowErrorCode;
import com.liefeng.property.exception.ApprovalFlowException;
import com.liefeng.property.vo.approvalFlow.HistoryTaskVo;
import com.liefeng.property.vo.approvalFlow.ProcessVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffWorkFlowUseVo;
import com.liefeng.service.constant.PushActionConstants;
import com.liefeng.service.constant.WorkflowConstants;
import com.liefeng.service.vo.TaskAttachVo;


/**
 * 审批流程接口
 * @author wuzhijing
 *
 */
@Service
public class ApprovalFlowService implements IApprovalFlowService{
	private static Logger logger = LoggerFactory
			.getLogger(WorkbenchService.class);
	
	@Autowired
	private IWorkflowService workflowService;
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private IPushMsgService pushMsgService;
	
	@Autowired
	private PropertyPushMsgService propertyPushMsgService;
	
	@Override
	public void startOrExecute(ApprovalFlowBo approvalFlowBo) {
		Process process = workflowService.findByProcessId(approvalFlowBo.getProcessId());
		PropertyStaffVo propertyStaffVo = propertyStaffService.findPropertyStaffById4DP(approvalFlowBo.getStaffId());
		
		if(approvalFlowBo.getParams() == null) approvalFlowBo.setParams(new HashMap<String, Object>());
		
		approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_STATUS, ApprovalFlowConstants.ORDER_STATUS_WAIT_SIGN);
		//开始并执行流程
		if(StringUtils.isEmpty(approvalFlowBo.getOrderId()) && StringUtils.isEmpty(approvalFlowBo.getTaskId())){
			ProcessModel processModel = process.getModel();
			
			List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setProcessId(approvalFlowBo.getProcessId()));
			
			// 设置流程订单号
			approvalFlowBo.getParams().put(SnakerEngine.ID, historyTasks.size()+100+"");
			approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_CREATE_NAME, propertyStaffVo.getName());
			approvalFlowBo.getParams().put(processModel.getTaskModels().get(0).getAssignee(), addUserPreixes(approvalFlowBo.getStaffId()) );
			
			Map<String, Object> arg = MyBeanUtil.createBean(approvalFlowBo.getParams(), HashMap.class);
			arg.put(approvalFlowBo.getAssignee(), addUserPreixes(approvalFlowBo.getNextOperator()));
			
			//启动流程
			Order order = workflowService.startInstanceById(approvalFlowBo.getProcessId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams());
			
			String activeTaskId = workflowService.getActiveTasks(new QueryFilter().setOrderId(order.getId())).get(0).getId();
			workflowService.executeAndJumpTask(activeTaskId, addUserPreixes(approvalFlowBo.getStaffId()), arg, approvalFlowBo.getTaskName());
			
			approvalFlowBo.setOrderId(order.getId());
			// 设置taskId
			HistoryTask historyTask = workflowService.getHistoryTasks(new QueryFilter().setOrderId(order.getId())).get(0);
			approvalFlowBo.setTaskId(historyTask.getId());
		
			if(ValidateHelper.isNotEmptyString(approvalFlowBo.getNextOperator())){
				String[] staffIdArray = approvalFlowBo.getNextOperator().split(",");
				
				List<String> staffIdList = new ArrayList<String>();
				for(int i = 0; i < staffIdArray.length; i++){
					staffIdList.add(staffIdArray[i]);
				}
				
				Map<String,String> data = new HashMap<String,String>();
				data.put("processId", order.getProcessId());
				data.put("orderId", historyTask.getOrderId());
				data.put("taskName", historyTask.getTaskName());
				
				//有新审批时群推消息
				propertyPushMsgService.pushMsgToStaffListOfMap(PushActionConstants.APPROVAL_NEW, staffIdList, data);
			}
		} else if(approvalFlowBo.getTaskName().equals(ApprovalFlowConstants.NODE_END)) { //结束流程
			
			//判断当前任务存不存在
			Task currTask = workflowService.findTaskById(approvalFlowBo.getTaskId());
			if(currTask == null){
				throw new ApprovalFlowException(ApprovalFlowErrorCode.CURR_TASK_NULL);
			}
			
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getAssignee(), addUserPreixes(approvalFlowBo.getNextOperator()));
			approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_STATUS, ApprovalFlowConstants.ORDER_COMPLETE);
			workflowService.complete(approvalFlowBo.getOrderId(),approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams());
			String staffId = workflowService.findHisOrderById(approvalFlowBo.getOrderId()).getCreator().replace( ApprovalFlowConstants.USER_PREFIXES, "");
			
			HistoryOrder order = workflowService.findHisOrderById(approvalFlowBo.getOrderId());
			
			Map<String,String> data = new HashMap<String,String>();
			data.put("processId", order.getProcessId());
			data.put("orderId", approvalFlowBo.getOrderId());
			data.put("taskName", approvalFlowBo.getTaskName());
			
			//审批完成时单推消息
			propertyPushMsgService.pushMsgToStaffOfMap(PushActionConstants.APPROVAL_FINISHED, staffId, data);
			
		} else { //继续执行下去
			
			//判断当前任务存不存在
			Task currTask = workflowService.findTaskById(approvalFlowBo.getTaskId());
			if(currTask == null){
				throw new ApprovalFlowException(ApprovalFlowErrorCode.CURR_TASK_NULL);
			}
			
			System.out.println("继续执行下去:"+approvalFlowBo.getAssignee()+"||"+addUserPreixes(approvalFlowBo.getNextOperator()));
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getAssignee(), addUserPreixes(approvalFlowBo.getNextOperator()));
			List<Task> tasks = workflowService.executeAndJumpTask(approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams(), approvalFlowBo.getTaskName());
			
			if(ValidateHelper.isNotEmptyString(approvalFlowBo.getNextOperator())){
				String[] staffIdArray = approvalFlowBo.getNextOperator().split(",");
				
				List<String> staffIdList = new ArrayList<String>();
				for(int i = 0; i < staffIdArray.length; i++){
					staffIdList.add(staffIdArray[i]);
				}
				
				Order order = workflowService.findOrderById(tasks.get(0).getOrderId());
				
				Map<String,String> data = new HashMap<String,String>();
				data.put("processId", order.getProcessId());
				data.put("orderId", tasks.get(0).getOrderId());
				data.put("taskName", tasks.get(0).getTaskName());
				
				//有新审批时群推消息
				propertyPushMsgService.pushMsgToStaffListOfMap(PushActionConstants.APPROVAL_NEW, staffIdList, data);
			}
		}
		
		// 保存抄送实例ID
		if(ValidateHelper.isNotEmptyString(approvalFlowBo.getCopyToPeopleId())) {
			String copyToPeopleId = approvalFlowBo.getCopyToPeopleId();
			copyToPeopleId = addUserPreixes(copyToPeopleId);
			String[] idsArr = copyToPeopleId.split(",");
			workflowService.createCCOrder(approvalFlowBo.getOrderId(), approvalFlowBo.getStaffId(), idsArr);
		}
		
		// 保存流程附件
		if(ValidateHelper.isNotEmptyString(approvalFlowBo.getTaskAttachsJson())) {
			String orderId = approvalFlowBo.getOrderId();
			List<TaskAttachVo> taskAttachVos = MyBeanUtil.str2List(approvalFlowBo.getTaskAttachsJson(), TaskAttachVo.class);
			if(taskAttachVos != null && ValidateHelper.isNotEmptyCollection(taskAttachVos)) {
				String taskId = approvalFlowBo.getTaskId();
				for(TaskAttachVo taskAttachVo : taskAttachVos) { // 设置附件所属流程实例ID和任务ID
					String fileName = taskAttachVo.getFileName();
					// 修复名称中文乱码问题
					try { 
						fileName = URLDecoder.decode(fileName, "UTF-8");
						taskAttachVo.setFileName(fileName);
					} catch (UnsupportedEncodingException e) {
						logger.error("字符串解码失败");
					}
					
					if(ValidateHelper.isEmptyString(taskAttachVo.getOrderId())) {
						taskAttachVo.setOrderId(orderId);
					}
					
					if(ValidateHelper.isEmptyString(taskAttachVo.getTaskId())) {
						taskAttachVo.setTaskId(taskId);
					}
				}
			}
			
			// 保存附件，保存时会先删除旧附件，再保存新的附件;
			// 如果新的附件列表为空，表示把所有的附件删除
			workflowService.saveTaskAttachs(orderId, taskAttachVos);
		}
	}
	
	/**
	 * 处理人加前缀操作
	 */
	private String addUserPreixes(String userIds){
		String id = "";
		for (String userId : userIds.split(",")) {
			id += ApprovalFlowConstants.USER_PREFIXES+userId+",";
		}
		return id.substring(0,id.length()-1);
	}
	
	/**
	 * 获取用户列表
	 * @deprecated 可在流程定义上的assignee加入更多的定义已“：”隔开
	 * @param assignee  类型_id
	 * 			user_123 指定用户
	 * 			dept_123 该部门所有人
	 * 			role_123 改角色所有人
	 * 			director_123 部门负责人
	 * 			director_self 我的部门负责人
	 * 			taskname_task1 该步骤的办理人
	 * @return
	 */
	@Override
	public List<PropertyStaffVo> getUser(String orderId,String assignee,String staffId){
		List<PropertyStaffVo> returnPropertyStaffVos = new ArrayList<PropertyStaffVo>();
		Map<String, String> ids = new HashMap<String, String>();
		for (String item : assignee.split(":")) {
			String[] split=item.split("_");
			if(split.length!=2)continue;
			
			String type=split[0];
			String id=split[1];
			
			switch (type) {
			case ApprovalFlowConstants.AssigneeType.USER://直接用户类型
				PropertyStaffVo userStaffVo = propertyStaffService.findPropertyStaffById4DP(id);
				if(userStaffVo != null && !ids.containsKey(userStaffVo.getId())) {
					ids.put(userStaffVo.getId(), userStaffVo.getId());
					returnPropertyStaffVos.add(userStaffVo);
				}
				break;
			case ApprovalFlowConstants.AssigneeType.ROLE: //角色 该角色下的所有人
				//是否是字符串判断
				try {
					Integer.parseInt(id);
				} catch (NumberFormatException e) {
					break;
				}
				
				List<PropertyStaffVo> roleStaffVos = propertyStaffService.findPropertyStaffByRoleId(Long.parseLong(id));
			
				if(ValidateHelper.isNotEmptyCollection(roleStaffVos))
				for (PropertyStaffVo roleStaffVo : roleStaffVos) {
					if(roleStaffVo != null && !ids.containsKey(roleStaffVo.getId())) {
						ids.put(roleStaffVo.getId(), roleStaffVo.getId());
						returnPropertyStaffVos.add(roleStaffVo);
					}
				}
				break;
			case ApprovalFlowConstants.AssigneeType.DEPT: //部门 该部门下所有人
				List<PropertyStaffVo> deptStaffVos = propertyStaffService.findPropertyStaff(id);
				
				if(ValidateHelper.isNotEmptyCollection(deptStaffVos))
				for (PropertyStaffVo deptStaffVo : deptStaffVos) {
					if(deptStaffVo != null && !ids.containsKey(deptStaffVo.getId())) {
						ids.put(deptStaffVo.getId(), deptStaffVo.getId());
						returnPropertyStaffVos.add(deptStaffVo);
					}
				}
				break;
			case ApprovalFlowConstants.AssigneeType.DIRECTOR: //部门领导人
				switch (id) {
				case ApprovalFlowConstants.AssigneeType.ID_DIRECTORALL: //id 为all 则获取所有部门负责人
					break;
				case ApprovalFlowConstants.AssigneeType.ID_DIRECTORSELF: //id 为self 自己本部门的负责人
					PropertyStaffVo propertyStaffVo = propertyStaffService.findPropertyStaffById4DP(staffId);
					PropertyStaffVo propertyStaff = propertyStaffService.getDepartmentDirector(propertyStaffVo.getDepartmentId());
					if(propertyStaff != null && !ids.containsKey(propertyStaff.getId())) {
						ids.put(propertyStaff.getId(), propertyStaff.getId());
						returnPropertyStaffVos.add(propertyStaff);
					}
					break;
				default: //默认直接部门id
					PropertyStaffVo directorStaffVo = propertyStaffService.getDepartmentDirector(id);
					if(directorStaffVo != null && !ids.containsKey(directorStaffVo.getId())) {
						ids.put(directorStaffVo.getId(), directorStaffVo.getId());
						returnPropertyStaffVos.add(directorStaffVo);
					}
					break;
				}
				break;
			case ApprovalFlowConstants.AssigneeType.POSTS: //岗位 某岗位下的所有员工
				List<PropertyStaffVo> postsStaffVos = propertyStaffService.findPropertyStaffByPosition(id);
				
				if(ValidateHelper.isNotEmptyCollection(postsStaffVos))
				for (PropertyStaffVo postsStaffVo : postsStaffVos) {
					if(postsStaffVo != null && !ids.containsKey(postsStaffVo.getId())) {
						ids.put(postsStaffVo.getId(), postsStaffVo.getId());
						returnPropertyStaffVos.add(postsStaffVo);
					}
				}
				break;
			case ApprovalFlowConstants.AssigneeType.TASKNAME: // 某步骤的办理人
				PropertyStaffVo tasknameStaffVo = getDefaultUsers(orderId, id);
				if(tasknameStaffVo != null && !ids.containsKey(tasknameStaffVo.getId())) {
					ids.put(tasknameStaffVo.getId(), tasknameStaffVo.getId());
					returnPropertyStaffVos.add(tasknameStaffVo);
				}
				break;
			default:
				break;
			}
		}
		
		return returnPropertyStaffVos;
	}
	
	@Override
	public PropertyStaffVo getDefaultUsers(String orderId,String taskName){
		QueryFilter queryFilter = new QueryFilter().setOrderId(orderId).setName(taskName);
		List<HistoryTask> historyTasks = workflowService.getHistoryTasks(queryFilter);
		if( historyTasks != null && historyTasks.size() > 0 ){
			String staffId = historyTasks.get(0).getOperator().replace(ApprovalFlowConstants.USER_PREFIXES, "");
			return propertyStaffService.findPropertyStaffById4DP(staffId);
		}
		return null;
	}

	@Override
	public String getTaskActor(String taskId) {
		StringBuilder stringBuilder = new StringBuilder("");
		List<TaskActor> taskActors = workflowService.listTaskActors(taskId);
		for(TaskActor taskActor : taskActors) {
			String staffId = taskActor.getActorId().split("_")[1];
			PropertyStaffContext propertyStaffContext = PropertyStaffContext.loadById(staffId);
			PropertyStaffVo propertyStaff = propertyStaffContext.get();
			stringBuilder.append(propertyStaff.getName()).append(",");
		}
		
		String actorsStr = stringBuilder.toString();
		actorsStr = actorsStr.substring(0, (actorsStr.length()-1));
		
		return actorsStr;
	}

	
	@Override
	public List<FieldModel> getFields(String processId, String taskName,String staffId) {
		NodeModel nodeModel = null;
		List<FieldModel> fieldModels = new ArrayList<FieldModel>();
		Process process = workflowService.findByProcessId(processId);
		if(ValidateHelper.isNotEmptyString(taskName)) {	//空则取第一个节点
			nodeModel  = process.getModel().getNode(taskName);
			fieldModels = ((TaskModel)nodeModel).getFields();
		}else { 
			nodeModel  = process.getModel().getTaskModels().get(0);
			Map<String, Object> staffMap = null;
			fieldModels = ((TaskModel)nodeModel).getFields();
			for (FieldModel fieldModel : fieldModels) {
				//是否需要程序来设置默认值
				if(fieldModel.getAttrMap().containsKey(ApprovalFlowConstants.AttrKey.DEFAULT_VALUE_TYPE)){
					
					String defaultValueType =fieldModel.getAttrMap().get(ApprovalFlowConstants.AttrKey.DEFAULT_VALUE_TYPE);
					String type = defaultValueType.split("_")[0];
					String id = defaultValueType.split("_")[1];
					//默认值为创建人的信息
					if(type.equals(ApprovalFlowConstants.AttrKeySprit.CREATOR_INFO)){
						//null 就读取创建人信息 并转成map 以便直接用key获取
						if(ValidateHelper.isEmptyMap(staffMap)){
							StaffWorkFlowUseVo propertyStaffVo = propertyStaffService.getStaffWorkFlowUseVo(staffId);
							staffMap  = MyBeanUtil.createBean(propertyStaffVo, Map.class);
						}
						if(staffMap.containsKey(id))
						fieldModel.getAttrMap().put(ApprovalFlowConstants.AttrKey.DEFAULT_VALUE, staffMap.get(id).toString());
					}
				}
			}
			
		}
		return fieldModels;
	}
	
	@Override
	public List<ProcessVo> getProcessList(){
		List<Process> processes = workflowService.getProcesss(new QueryFilter().setProcessType(WorkflowConstants.PROCESSTYPE.APPROVE));
		return MyBeanUtil.createList(processes, ProcessVo.class);
	}

	@Override
	public List<TaskModel> getNextTask(String processId, String taskName) {

		//获取流程
		Process process = workflowService.findByProcessId(processId);
		ProcessModel processModel = process.getModel();
		NodeModel nodeModel = null;

		//判断是否是第一个任务
		if (StringUtils.isNotEmpty(taskName)) {
			nodeModel = processModel.getNode(taskName);
		} else {
			logger.info("添加页面，默认从第一个节点往下");
			nodeModel = processModel.getTaskModels().get(0);
		}

		//获取下一个步骤的task
		List<TaskModel> nextTasks = ((TaskModel)nodeModel).getNextTaskModels();
		List<TransitionModel> endModel = nodeModel.getOutputs();
		//添加结束节点
		if (StringUtils.isNotEmpty(taskName)) //添加不显示结束步骤
		if(endModel !=null && endModel.size()>0){
			for (int i = 0; i < endModel.size(); i++) {
				if(endModel.get(i).getTarget().getName().equals(ApprovalFlowConstants.NODE_END)){
					TaskModel taskModel = new TaskModel();
					taskModel.setName(ApprovalFlowConstants.NODE_END);
					taskModel.setDisplayName("结束并归档");
					taskModel.setAssignee(ApprovalFlowConstants.NODE_END);
					nextTasks.add(taskModel);
				}
			} 
				
		}

		return nextTasks;
	}

	@Override
	public DataListValue<HistoryTaskVo> getHistTaskByOrderId(String orderId) {
		List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setOrderId(orderId));
		List<HistoryTask> returnTasks = new ArrayList<HistoryTask>();

		for(HistoryTask hTask : historyTasks) {
			String operator = hTask.getOperator();
			// 设置名字
			String staffid = operator.split("_")[1];
			PropertyStaffVo propertyStaff = propertyStaffService.findPropertyStaffById4DP(staffid);
			if(propertyStaff != null)
			hTask.setOperator(propertyStaff.getName());
			returnTasks.add(hTask);
		}
		
		// 增加当前处理的步骤
		List<Task> activeTasks = workflowService.getActiveTasks(new QueryFilter().setOrderId(orderId));
		if(ValidateHelper.isNotEmptyCollection(activeTasks)) {
			Task task = activeTasks.get(0);
			String actorsStr = getTaskActor(task.getId());
			task.setOperator(actorsStr);
			HistoryTask historyTask = MyBeanUtil.createBean(task, HistoryTask.class);
			returnTasks.add(historyTask);
		}
		
		return DataListValue.success(MyBeanUtil.createList(historyTasks, HistoryTaskVo.class));
	}
	
	@Override
	public void deleteOrder(String processId,String orderId,String loginId){
		// 获取流程第一个任务节点
		Process process = workflowService.findByProcessId(processId);
		NodeModel nodeModel = process.getModel().getTaskModels().get(0);

		// 获取流程最后一个执行节点
		List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setOrderId(orderId));
		HistoryTask historyTask = historyTasks.get(0);
		HistoryOrder historyOrder = workflowService.findHisOrderById(orderId);
		if(nodeModel.getDisplayName().equals(historyTask.getDisplayName())) {
			
			loginId = ApprovalFlowConstants.USER_PREFIXES + loginId;
			if(loginId.equals(historyOrder.getCreator())) {
				try {
					workflowService.cascadeRemove(orderId);
				} catch (Exception e) {
					throw new  ApprovalFlowException(ApprovalFlowErrorCode.DELETE_FAILED);
				}
			}
		} else {
			throw new  ApprovalFlowException(ApprovalFlowErrorCode.CANNOT_DELETE);
		}
	}
	
	@Override
	public void backTask(String taskId ,String loginId) {
		List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setTaskId(taskId));
		if(historyTasks == null || historyTasks.size() <= 0){
			throw new ApprovalFlowException(ApprovalFlowErrorCode.HISTASK_NULL);
		}
		Task task = workflowService.withdrawTask(taskId, ApprovalFlowConstants.USER_PREFIXES + loginId);

		// 更新task某些字段
		Map<String, Object> params = task.getVariableMap();
		params.put("status", ApprovalFlowConstants.ORDER_STATUS_WAIT_SIGN);
		task.setVariable(JsonHelper.toJson(params));
		workflowService.updateTask(task);
	}
	
}
