package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class StaffIdRo extends BaseValue{
	
	private static final long serialVersionUID = 1900313102263172759L;
	
	@ApiModelProperty(value="员工ID", required=true)
	@NotNull
	private String staffId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
