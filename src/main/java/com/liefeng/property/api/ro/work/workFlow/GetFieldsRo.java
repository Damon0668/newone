package com.liefeng.property.api.ro.work.workFlow;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取表单请求参数类
 * @author wuzhjing
 *
 */
@ApiModel
public class GetFieldsRo extends BaseValue{

	private static final long serialVersionUID = -1674293274837679831L;
	
	@ApiModelProperty(value="流程定义Id", required=true)
	@NotNull
	private String processId;
	
	@ApiModelProperty(value="当前步骤名称，空则默认获取第一个")
	private String taskName;
	
	@ApiModelProperty(value="当前登录人id", required=true)
	@NotNull
	private String staffId;

	public String getProcessId() {
		return processId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
}
