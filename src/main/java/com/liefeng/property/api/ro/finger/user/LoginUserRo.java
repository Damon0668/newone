package com.liefeng.property.api.ro.finger.user;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取登陆用户信息请求
 * @author 蔡少东
 * @date 2016年4月18日
 */
@ApiModel
public class LoginUserRo extends BaseValue {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2635750835163454406L;
	
	@ApiModelProperty(value="[业主|住户]ID", required=true)
	@NotNull
	private String loginId;
	
	@ApiModelProperty(value="用户类型[1：业主，2：住户]", required=true)
	@NotNull
	private String householdType;
	
	@ApiModelProperty(value="OEM编码", required=true)
	@NotNull
	private String oemCode;

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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
