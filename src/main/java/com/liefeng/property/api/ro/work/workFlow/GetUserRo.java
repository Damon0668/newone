package com.liefeng.property.api.ro.work.workFlow;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取用户请求参数类
 * @author wuzhijing
 *
 */
@ApiModel
public class GetUserRo extends BaseValue{

	private static final long serialVersionUID = -1674293274837679831L;
	
	@ApiModelProperty(value="流程实例Id", required=true)
	@NotNull
	private String orderId;
	
	@ApiModelProperty(value="处理人，从选择步骤中获取")
	@NotNull
	private String assignee;

	public String getOrderId() {
		return orderId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

}
