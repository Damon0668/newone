package com.liefeng.property.api.ro.work.event;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 根据类型查询功能列表参数类
 * @author ZhenTingJun
 * @date 2016-03-28
 */
public class EventReportListByTypeRo extends BaseValue {

	private static final long serialVersionUID = -8892081064693811642L;

	@ApiModelProperty(value="列表类型【1：待办理，2：流转中，3：已完成】", required=true)
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
