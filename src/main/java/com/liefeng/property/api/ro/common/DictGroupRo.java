package com.liefeng.property.api.ro.common;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DictGroupRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7948103879643053978L;
	
	@ApiModelProperty(value="字典组编码", required=true)
	@NotNull
	private String groupCode;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
