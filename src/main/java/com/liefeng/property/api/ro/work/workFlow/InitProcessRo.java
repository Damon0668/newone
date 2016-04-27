package com.liefeng.property.api.ro.work.workFlow;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

public class InitProcessRo extends BaseValue{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="流程名称", required=true)
	@NotNull
	private String processName;
	
	@ApiModelProperty(value="oemCode", required=true)
	@NotNull
	private String oemCode;
	
	public String getProcessName() {
		return processName;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
