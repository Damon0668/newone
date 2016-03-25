package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 获取头部数量统计数据查询参数类
 * @author ZhenTingJun
 * @date 2016-03-25
 */
@ApiModel
public class CountsToHeadRo {
	@ApiModelProperty(value="当前登陆人ID", required=true)
	@NotNull
	private String actor;

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
}
