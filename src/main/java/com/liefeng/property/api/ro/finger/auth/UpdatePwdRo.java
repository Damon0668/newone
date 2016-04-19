package com.liefeng.property.api.ro.finger.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdatePwdRo extends BaseValue{

	private static final long serialVersionUID = 729226010558913350L;

	@ApiModelProperty(value="手机号码",required=true)
	@NotNull
	private String mobile;

	@ApiModelProperty(value="密码",required=true)
	@NotNull
	private String password;
	
	@ApiModelProperty(value="验证码",required=true)
	@NotNull
	private String code;
	
	@ApiModelProperty(value="应用编码[property]",required=true)
	@NotNull
	private String appCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
}
