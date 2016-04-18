package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 撤回测试类
 * @author wuzhijing
 *
 */
public class BackTaskRo extends BaseValue{
	
	private static final long serialVersionUID = -861486152309107859L;

	@ApiModelProperty(value="历史的某个任务id", required=true)
	@NotNull
	private String taskId;

	@ApiModelProperty(value="当前处理人id", required=true)
	@NotNull
	private String staffId;


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
