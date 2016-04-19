package com.liefeng.property.api.ro.finger.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdatePwdLoginRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8217696074457161183L;

	@ApiModelProperty(value="用户ID",required=true)
	@NotNull
	private String userId;
	
	@ApiModelProperty(value="旧密码",required=true)
	@NotNull
	private String oldpassword;
	
	@ApiModelProperty(value="新密码",required=true)
	@NotNull
	private String newpassword;

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
