package com.liefeng.property.api.ro.sms;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SmsSendRo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2198637575067797897L;
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	public String phoneNum;
	
	@ApiModelProperty(value="相关事件", required=true)
	@NotNull
	public String action;
	
	@ApiModelProperty(value="模板数据(JSON)")
	public String paramString;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParamString() {
		return paramString;
	}

	public void setParamString(String paramString) {
		this.paramString = paramString;
	}
}
