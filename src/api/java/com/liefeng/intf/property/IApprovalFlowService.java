package com.liefeng.intf.property;

import java.util.List;

import org.snaker.engine.model.FieldModel;
import org.snaker.engine.model.TaskModel;

import com.liefeng.core.entity.DataListValue;
import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.vo.approvalFlow.HistoryTaskVo;
import com.liefeng.property.vo.approvalFlow.ProcessVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 审批流程接口
 * @author wuzhijing
 *
 */
public interface IApprovalFlowService {
	
	/**
	 * 开始或执行流程  orderId和taskId 则重新开始任务
	 */
	public void startOrExecute(ApprovalFlowBo approvalFlowBo);
	
	/**
	 * 查询用户
	 * @param assignee
	 * @return
	 */
	public List<PropertyStaffVo> getUser(String orderId,String assignee,String staffId);
	
	/**
	 * 获取某个步骤的执行人
	 * @param orderId
	 * @param taskName
	 * @return
	 */
	public PropertyStaffVo getDefaultUsers(String orderId, String taskName);
	
	/**
	 * 获取任务ID
	 * @param taskId
	 * @return
	 */
	public String getTaskActor(String taskId);
	
	/**
	 * 获取流程节点的变量（表单信息）
	 * @param processId 流程定义id
	 * @param taskName	流程节点名称 空则默认获取第一个
	 * @return
	 */
	public List<FieldModel> getFields(String processId, String taskName,String staffId);
	
	/**
	 * 获取审批流程的所有流程定义
	 * @return
	 */
	public List<ProcessVo> getProcessList();
	
	/**
	 * 获取下一步步骤
	 * @param processId
	 * @param taskName
	 * @return
	 */
	public List<TaskModel> getNextTask(String processId, String taskName);
	
	/**
	 * 获取某个任务的历史
	 * @param orderId
	 * @return
	 */
	public DataListValue<HistoryTaskVo> getHistTaskByOrderId(String orderId);
	
	/**
	 * 删除流程
	 * @param processId 流程定义id
	 * @param orderId 流程实例id
	 * @param loginId 当前登录人id
	 */
	public void deleteOrder(String processId, String orderId, String loginId);
	
	/**
	 * 撤回/收回
	 * @param taskId 当前人id
	 * @param loginId 当前登录人id
	 */
	public void backTask(String taskId, String loginId);
}
