package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 报事工单处理 数据列表费用请求参数类
 * @author wuzhijing
 * @author ZhenTingJun
 * @date 2016-3-24 17:03:25
 */
public class EventReportDataPageRo {
	
	@ApiModelProperty(value="列表类型【0：待签收，1：抢单，2：待办理，3：流转中，4：已完成】", required=true)
	@NotNull
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
