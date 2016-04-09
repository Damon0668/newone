package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.service.vo.TaskAttachVo;

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
	public List<PropertyStaffVo> getUser(String orderId,String assignee);
	
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
	 * 根据流程实例ID获取附件
	 * @param orderId 流程实例ID
	 * @return 附件列表
	 */
	public List<TaskAttachVo> listTaskAttach(String orderId);
}
