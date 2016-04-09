package com.liefeng.property.bo.approvalFlow;

import java.util.Map;

import com.liefeng.core.entity.BaseValue;

/**
 * 审批流程业务类
 * @author wuzhijing
 *
 */
public class ApprovalFlowBo  extends BaseValue{
	
	private static final long serialVersionUID = -8406440967733667322L;
	
	/**
	 * 流程定义id
	 */
	private String processId;
	
	/**
	 * 流程实例id
	 */
	private String orderId;
	
	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 步骤名称
	 */
	private String taskName;
	
	/**
	 * 处理的角色
	 */
	private String assignee;
	
	/**
	 * 当前处理人id
	 */
	private String staffId;
	
	/**
	 * 下一步处理人
	 */
	private String nextOperator;
	
	/**
	 * 流程实例表变量（表单数据)
	 */
	private Map<String, Object> params;
	
	/**
	 * 抄送人id
	 */
	private String copyToPeopleId;

	public String getProcessId() {
		return processId;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getNextOperator() {
		return nextOperator;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setNextOperator(String nextOperator) {
		this.nextOperator = nextOperator;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getCopyToPeopleId() {
		return copyToPeopleId;
	}

	public void setCopyToPeopleId(String copyToPeopleId) {
		this.copyToPeopleId = copyToPeopleId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

}
