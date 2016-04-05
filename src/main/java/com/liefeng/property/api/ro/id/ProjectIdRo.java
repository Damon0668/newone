package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProjectIdRo extends BaseValue {

	private static final long serialVersionUID = 4645423875267725969L;

	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
