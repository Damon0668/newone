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
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.TaskActor;
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
import com.liefeng.mq.type.MessageEvent;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.constant.ApprovalFlowConstants;
import com.liefeng.property.constant.SysConstants;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.vo.approvalFlow.HistoryTaskVo;
import com.liefeng.property.vo.approvalFlow.ProcessVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.service.constant.PushActionConstants;
import com.liefeng.service.constant.PushMsgConstants;
import com.liefeng.service.constant.WorkflowConstants;
import com.liefeng.service.vo.PushMsgTemplateVo;
import com.liefeng.service.vo.TaskAttachVo;
import com.liefeng.service.vo.msg.ListUserMsg;
import com.liefeng.service.vo.msg.SingleUserMsg;

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
	
	@Override
	public void startOrExecute(ApprovalFlowBo approvalFlowBo) {
		Process process = workflowService.findByProcessId(approvalFlowBo.getProcessId());
		PropertyStaffVo propertyStaffVo = propertyStaffService.findPropertyStaffById(approvalFlowBo.getStaffId());
		
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
			String taskId = workflowService.getHistoryTasks(new QueryFilter().setOrderId(order.getId())).get(0).getId();
			approvalFlowBo.setTaskId(taskId);
		} else if(approvalFlowBo.getTaskName().equals(ApprovalFlowConstants.NODE_END)) { //结束流程
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getAssignee(), addUserPreixes(approvalFlowBo.getNextOperator()));
			approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_STATUS, ApprovalFlowConstants.ORDER_COMPLETE);
			workflowService.complete(approvalFlowBo.getOrderId(),approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams());
			String staffId = workflowService.findHisOrderById(approvalFlowBo.getOrderId()).getCreator();
			//获取推送消息模板
			PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.APPROVAL_FINISHED);
			if(pushMsgTemplateVo != null){
				SingleUserMsg message = new SingleUserMsg();
				message.setAction(PushActionConstants.APPROVAL_FINISHED);
				message.setMsgCode(pushMsgTemplateVo.getMsgCode());
				message.setTitle(pushMsgTemplateVo.getTitle());
				message.setContent(pushMsgTemplateVo.getContent());
				message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
				message.setReceiveUserId(staffId);
				pushMsgService.push2Single(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
				logger.info("审批完成时单推消息{}", message);
			}
		} else { //继续执行下去
			System.out.println("继续执行下去:"+approvalFlowBo.getAssignee()+"||"+addUserPreixes(approvalFlowBo.getNextOperator()));
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getAssignee(), addUserPreixes(approvalFlowBo.getNextOperator()));
			workflowService.executeAndJumpTask(approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams(), approvalFlowBo.getTaskName());
			
			if(ValidateHelper.isNotEmptyString(approvalFlowBo.getNextOperator())){
				String[] staffIdArray = approvalFlowBo.getNextOperator().split(",");
				
				List<String> staffIdList = new ArrayList<String>();
				for(int i = 0; i < staffIdArray.length; i++){
					staffIdList.add(staffIdArray[i]);
				}
				
				//获取推送消息模板
				PushMsgTemplateVo pushMsgTemplateVo = pushMsgService.getPushMsgByTpl(PushActionConstants.APPROVAL_NEW);
				if(pushMsgTemplateVo != null){
					ListUserMsg message = new ListUserMsg();
					message.setAction(PushActionConstants.APPROVAL_NEW);
					message.setMsgCode(pushMsgTemplateVo.getMsgCode());
					message.setTitle(pushMsgTemplateVo.getTitle());
					message.setContent(pushMsgTemplateVo.getContent());
					message.setSendUserId(SysConstants.DEFAULT_SYSTEM_SENDUSER);
					message.setReceiveUserIdList(staffIdList);
					
					pushMsgService.push2List(MessageEvent.PUSH_TO_PROPERTY_STAFF, PushMsgConstants.TerminalType.MOBILE_PROPERTY_WORKBENCH, message);
					logger.info("有新审批时群推消息{}", message);
				}
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
				PropertyStaffVo userStaffVo = propertyStaffService.findPropertyStaffById(id);
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
					PropertyStaffVo propertyStaffVo = propertyStaffService.findPropertyStaffById(staffId);
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
	public List<FieldModel> getFields(String processId, String taskName) {
		NodeModel nodeModel = null;
		Process process = workflowService.findByProcessId(processId);
		if(ValidateHelper.isNotEmptyString(taskName)) {	//空则取第一个节点
			nodeModel  = process.getModel().getNode(taskName);
		}else {
			nodeModel  = process.getModel().getTaskModels().get(0);
		}
		return ((TaskModel)nodeModel).getFields();
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
			nodeModel = processModel.getTaskModels().get(0);
		}

		//获取下一个步骤的task
		List<TaskModel> nextTasks = ((TaskModel)nodeModel).getNextTaskModels();
		List<TransitionModel> endModel = nodeModel.getOutputs();
		//添加结束节点
		if(endModel !=null && endModel.size()>0){
			for (int i = 0; i < endModel.size(); i++) {
				if(endModel.get(i).getTarget().getName().equals(ApprovalFlowConstants.NODE_END)){
					TaskModel taskModel = new TaskModel();
					taskModel.setName(ApprovalFlowConstants.NODE_END);
					taskModel.setDisplayName("结束并归档");
					nextTasks.add(taskModel);
				}
			} 
				
		}

		return nextTasks;
	}

	@Override
	public DataListValue<HistoryTaskVo> getHistTaskByOrderId(String orderId) {
		List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setOrderId(orderId));
		DataListValue<HistoryTaskVo> dataListValue = new DataListValue<HistoryTaskVo>();
		dataListValue.setDataList(MyBeanUtil.createList(historyTasks, HistoryTaskVo.class));
		return dataListValue;
	}
	
}
