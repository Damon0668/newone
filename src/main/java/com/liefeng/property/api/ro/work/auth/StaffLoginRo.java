package com.liefeng.property.api.ro.work.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class StaffLoginRo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3934438909810608625L;


	@ApiModelProperty(value="员工账户", required=true)
	@NotNull
	private String account;
	

	@ApiModelProperty(value="密码", required=true)
	@NotNull
	private String password;


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
