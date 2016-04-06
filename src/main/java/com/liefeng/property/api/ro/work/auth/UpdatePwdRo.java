package com.liefeng.property.api.ro.work.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdatePwdRo extends BaseValue{

	private static final long serialVersionUID = 729226010558913350L;
	
	@ApiModelProperty(value="员工账号",required=true)
	@NotNull
	private String account;
	
	@ApiModelProperty(value="手机号码",required=true)
	@NotNull
	private String mobile;

	@ApiModelProperty(value="密码",required=true)
	@NotNull
	private String password;
	
	@ApiModelProperty(value="验证码",required=true)
	@NotNull
	private String code;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
