package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 获取当前活动人参数类
 * @author wuzhijing
 *
 */
public class GetActiveTaskRo extends BaseValue{
	
	@ApiModelProperty(value="流程实例id", required=true)
	@NotNull
	private String orderId;
	
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
