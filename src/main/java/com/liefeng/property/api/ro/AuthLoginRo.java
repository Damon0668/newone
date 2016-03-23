package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 权限模块
 * 登陆请求参数
 * @author 蔡少东
 * @date 2016年3月18日
 */
@ApiModel
public class AuthLoginRo extends BaseValue{
	
	private static final long serialVersionUID = -8706002992060339936L;

	@ApiModelProperty(value="手机号码",required=true)
	@NotNull
	private String mobile;
	
	@ApiModelProperty(value="密码",required=true)
	@NotNull
	private String password;
	
	@ApiModelProperty(value="应用编码",required=true)
	@NotNull
	private String appCode;

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

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
}
