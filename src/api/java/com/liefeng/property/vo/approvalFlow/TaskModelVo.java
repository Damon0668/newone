package com.liefeng.property.vo.approvalFlow;

import io.swagger.annotations.ApiModelProperty;

import com.liefeng.core.entity.BaseValue;

public class TaskModelVo  extends BaseValue{

	private static final long serialVersionUID = 8078485936265799254L;
	
	@ApiModelProperty(value="处理人")
	private String assignee;
	
	@ApiModelProperty(value="步骤名称(taskName)")
	private String name;
	
	@ApiModelProperty(value="步骤显示名称")
	private String displayName;
	
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
