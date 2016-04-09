package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import javax.validation.constraints.NotNull;

/**
 * 执行流程请求参数类
 * @author wuzhijing
 *
 */
@ApiModel
public class StartOrExecuteRo {
	
	/**
	 * 流程定义id
	 */
	@ApiModelProperty(value="流程定义Id", required=true)
	@NotNull
	private String processId;
	
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value="流程实例Id，空则表示是开启流程")
	private String orderId;
	
	/**
	 * 任务id
	 */
	@ApiModelProperty(value="步骤Id，空则表示是开启流程")
	private String taskId;
	
	/**
	 * 步骤名称
	 */
	@ApiModelProperty(value="步骤名称，除开启流程外都不能为空")
	private String taskName;
	
	/**
	 * 受理人
	 */
	@ApiModelProperty(value="受理人，从选择步骤那获取值", required=true)
	@NotNull
	private String assignee;
	
	/**
	 * 当前处理人id
	 */
	@ApiModelProperty(value="当前处理人id", required=true)
	@NotNull
	private String staffId;
	
	/**
	 * 下一步处理人
	 */
	@ApiModelProperty(value="下一步处理人")
	private String nextOperator;
	
	/**
	 * 流程实例表变量（表单数据)
	 */
	@ApiModelProperty(value="流程实例表变量（表单数据)")
	private Map<String, Object> params;
	
	/**
	 * 抄送人id
	 */
	@ApiModelProperty(value="抄送人id")
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

	public String getAssignee() {
		return assignee;
	}

	public String getStaffId() {
		return staffId;
	}

	public String getNextOperator() {
		return nextOperator;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public String getCopyToPeopleId() {
		return copyToPeopleId;
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

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public void setNextOperator(String nextOperator) {
		this.nextOperator = nextOperator;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setCopyToPeopleId(String copyToPeopleId) {
		this.copyToPeopleId = copyToPeopleId;
	}
}
