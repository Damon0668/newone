package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 工单详情接口查询入参类
 * @author ZhenTingJun
 * @date 2016-03-25
 */
@ApiModel
public class EventReportDetailRo{
	@ApiModelProperty(value="流程实例ID", required=true)
	@NotNull
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
