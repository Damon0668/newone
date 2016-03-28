package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取登陆用户房产列表接口参数类
 * 
 * @author ZhenTingJun
 * @date 2016-03-28
 */
@ApiModel
public class GetUserHousesRo extends BaseValue {

	private static final long serialVersionUID = 337588558078193173L;

	@ApiModelProperty(value = "登陆用户ID", required = true)
	@NotNull
	private String loginId;

	@ApiModelProperty(value = "用户类型，【1：业主，2：住户】", required = true)
	@NotNull
	private String householdType;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

}
