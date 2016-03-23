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
	private String oldpaswword;
	
	@ApiModelProperty(value="新密码",required=true)
	@NotNull
	private String newpaswword;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOldpaswword() {
		return oldpaswword;
	}

	public void setOldpaswword(String oldpaswword) {
		this.oldpaswword = oldpaswword;
	}

	public String getNewpaswword() {
		return newpaswword;
	}

	public void setNewpaswword(String newpaswword) {
		this.newpaswword = newpaswword;
	}

}
