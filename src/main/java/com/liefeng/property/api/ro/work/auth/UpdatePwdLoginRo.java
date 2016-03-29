package com.liefeng.property.api.ro.work.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdatePwdLoginRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 729226010558913350L;
	
	@ApiModelProperty(value="员工ID",required=true)
	@NotNull
	private String staffId;
	
	@ApiModelProperty(value="旧密码",required=true)
	@NotNull
	private String oldpassword;
	
	@ApiModelProperty(value="新密码",required=true)
	@NotNull
	private String newpassword;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

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
}
