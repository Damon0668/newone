package com.liefeng.property.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.TaskActor;
import org.snaker.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.intf.property.IApprovalFlowService;
import com.liefeng.intf.property.IPropertyStaffService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.intf.service.workflow.IWorkflowService;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.constant.ApprovalFlowConstants;
import com.liefeng.property.domain.staff.PropertyStaffContext;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.workbench.EventProcessVo;

/**
 * 审批流程接口
 * @author wuzhijing
 *
 */
@Service
public class ApprovalFlowService implements IApprovalFlowService{
	
	@Autowired
	private IWorkflowService workflowService;
	
	@Autowired
	private IPropertyStaffService propertyStaffService;
	
	@Autowired
	private IWorkbenchService workbenchService;
	
	@Override
	public void startOrExecute(ApprovalFlowBo approvalFlowBo) {
		Process process = workflowService.findByProcessId(approvalFlowBo.getProcessId());
		PropertyStaffVo propertyStaffVo = propertyStaffService.findPropertyStaffById(approvalFlowBo.getStaffId());
		approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_STATUS, ApprovalFlowConstants.ORDER_STATUS_WAIT_SIGN);
		//开始并执行流程
		if(StringUtils.isEmpty(approvalFlowBo.getOrderId()) && StringUtils.isEmpty(approvalFlowBo.getTaskId())){
			ProcessModel processModel = process.getModel();
			
			List<HistoryTask> historyTasks = workflowService.getHistoryTasks(new QueryFilter().setProcessId(approvalFlowBo.getProcessId()));
			
			// 设置流程订单号
			approvalFlowBo.getParams().put(SnakerEngine.ID, historyTasks.size()+100+"");
			approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_CREATE_NAME, propertyStaffVo.getName());
			approvalFlowBo.getParams().put(processModel.getTaskModels().get(0).getAssignee(), addUserPreixes(approvalFlowBo.getStaffId()) );
			
			Map<String, Object> arg = MyBeanUtil.createBean(approvalFlowBo.getParams(), Map.class);
			arg.put(approvalFlowBo.getRole(), addUserPreixes(approvalFlowBo.getNextOperator()));
			Order order = workflowService.startAndExecute(approvalFlowBo.getProcessId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams(),arg);
			
			approvalFlowBo.setOrderId(order.getId());
		} else if(approvalFlowBo.getTaskName().equals(ApprovalFlowConstants.NODE_END)) { //结束流程
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getRole(), addUserPreixes(approvalFlowBo.getNextOperator()));
			approvalFlowBo.getParams().put(ApprovalFlowConstants.ORDER_STATUS, ApprovalFlowConstants.ORDER_COMPLETE);
			workflowService.execute(approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams());
		}else{ //继续执行下去
			System.out.println("继续执行下去:"+approvalFlowBo.getRole()+"||"+addUserPreixes(approvalFlowBo.getNextOperator()));
			workflowService.updateOrderVariableMap(approvalFlowBo.getOrderId(), approvalFlowBo.getParams());
			approvalFlowBo.getParams().put(approvalFlowBo.getRole(), addUserPreixes(approvalFlowBo.getNextOperator()));
			workflowService.executeAndJumpTask(approvalFlowBo.getTaskId(), addUserPreixes(approvalFlowBo.getStaffId()), approvalFlowBo.getParams(), approvalFlowBo.getTaskName());
		}
		
		// 保存抄送实例ID
		if(ValidateHelper.isNotEmptyString(approvalFlowBo.getCopyToPeopleId())) {
			String copyToPeopleId = approvalFlowBo.getCopyToPeopleId();
			copyToPeopleId = addUserPreixes(copyToPeopleId);
			String[] idsArr = copyToPeopleId.split(",");
			workflowService.createCCOrder(approvalFlowBo.getOrderId(), approvalFlowBo.getStaffId(), idsArr);
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
	 * @param assignee  类型_id
	 * 			user_123 指定用户
	 * 			dept_123 该部门所有人
	 * 			role_123 改角色所有人
	 * 			director_123 部门负责人
	 * 			taskname_task1 该步骤办理人
	 * @return
	 */
	@Override
	public List<PropertyStaffVo> getUser(String orderId,String assignee){
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
				PropertyStaffVo directorStaffVo = propertyStaffService.getDepartmentDirector(id);
				if(directorStaffVo != null && !ids.containsKey(directorStaffVo.getId())) {
					ids.put(directorStaffVo.getId(), directorStaffVo.getId());
					returnPropertyStaffVos.add(directorStaffVo);
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
	
}
