package com.liefeng.property.api.ro.finger.event;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 报事工单处理 数据列表费用请求参数类
 * @author wuzhijing
 * @date 2016-3-24 17:03:25
 */
public class EventReportDataPageRo extends BaseValue {

	private static final long serialVersionUID = -515804387276602679L;

	@ApiModelProperty(value="当前登陆人id", required=true)
	@NotNull
	private String actor;
	
	@ApiModelProperty(value="当前页【最小值为1】", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页面大小【最小值为0】", required=true)
	@NotNull
	private Integer size;
	
	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	
}
