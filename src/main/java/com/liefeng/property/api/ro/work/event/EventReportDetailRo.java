package com.liefeng.property.api.ro.work.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 工单详情接口查询入参类
 * @author ZhenTingJun
 * @date 2016-03-25
 */
@ApiModel
public class EventReportDetailRo extends BaseValue {
	
	private static final long serialVersionUID = 5821272812064338938L;
	
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
