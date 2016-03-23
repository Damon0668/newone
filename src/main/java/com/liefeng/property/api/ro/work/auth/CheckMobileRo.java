package com.liefeng.property.api.ro.work.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CheckMobileRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 549441756127090178L;
	
	@ApiModelProperty(value="员工手机号码", required=true)
	@NotNull
	private String mobile;
	
	@ApiModelProperty(value="员工账号", required=true)
	@NotNull
	private String account;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
