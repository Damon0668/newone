package com.liefeng.property.vo.approvalFlow;

import io.swagger.annotations.ApiModelProperty;

import com.liefeng.core.entity.BaseValue;

public class TaskModelVo  extends BaseValue{

	private static final long serialVersionUID = 8078485936265799254L;
	
	@ApiModelProperty(value="处理人")
	private String assignee;
	
	@ApiModelProperty(value="步骤名称(taskName)")
	private String name;
	
	public String getAssignee() {
		return assignee;
	}

	public String getName() {
		return name;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setName(String name) {
		this.name = name;
	}
}
