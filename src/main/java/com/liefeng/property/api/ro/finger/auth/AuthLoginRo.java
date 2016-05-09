package com.liefeng.property.api.ro.finger.auth;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
	
	@ApiModelProperty(value="验证码")
	private String verifyCode;
	
	@ApiModelProperty(value="手机识别码",required=true)
	@NotNull
	private String mobileId;

	@ApiModelProperty(value="手机型号",required=true)
	@NotNull
	private String mobileModel;
	
	@ApiModelProperty(value="APP版本")
	@NotNull
	private String appVersion;
	
	@ApiModelProperty(value="个推客户端ID",required=true)
	@NotNull
	private String clientId;

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

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getMobileModel() {
		return mobileModel;
	}

	public void setMobileModel(String mobileModel) {
		this.mobileModel = mobileModel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
