package com.liefeng.property.api.ro.common;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class VerifyCodeRo extends BaseValue{

	private static final long serialVersionUID = -7920390802582811449L;
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	public String phoneNum;
	
	@ApiModelProperty(value="相关事件[SD_REGISTER_MSG-注册;SD_UPDATAPWD_MSG-忘记密码;SD_LOGIN_MSG-登陆]", required=true)
	@NotNull
	public String action;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
