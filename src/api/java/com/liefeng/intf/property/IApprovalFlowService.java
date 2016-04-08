package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.property.bo.approvalFlow.ApprovalFlowBo;
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
	public List<PropertyStaffVo> getUser(String assignee);
	
	/**
	 * 获取任务ID
	 * @param taskId
	 * @return
	 */
	public String getTaskActor(String taskId);
}
