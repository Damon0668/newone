package com.liefeng.property.api.ro.work.workFlow;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取默认办理人请求参数类
 * @author wuzhijing
 *
 */
@ApiModel
public class GetDefaultUserRo extends BaseValue{

	private static final long serialVersionUID = -1674293274837679831L;
	
	@ApiModelProperty(value="流程实例Id", required=true)
	@NotNull
	private String orderId;
	
	@ApiModelProperty(value="当前步骤名称")
	@NotNull
	private String taskName;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
