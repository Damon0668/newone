package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 删除流程测试类
 * @author wuzhijing
 *
 */
public class DeleteOrderRo extends BaseValue{
	
	private static final long serialVersionUID = -861486152309107859L;

	@ApiModelProperty(value="流程定义id", required=true)
	@NotNull
	private String processId;
	
	@ApiModelProperty(value="流程实例id", required=true)
	@NotNull
	private String orderId;
	
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@ApiModelProperty(value="当前处理人id", required=true)
	@NotNull
	private String staffId;

	public String getOrderId() {
		return orderId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
