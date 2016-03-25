package com.liefeng.property.api.ro.common;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 手机号码
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class PhoneRo {
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
