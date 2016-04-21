package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 流程实例id
 * @author john
 *
 */
public class OrderIdRo extends BaseValue{
	
	private static final long serialVersionUID = -3275171243723201717L;
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value="流程实例id", required=true)
	@NotNull
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
