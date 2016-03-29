package com.liefeng.property.api.ro.common;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 手机号码
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class PhoneRo extends BaseValue {
	
	private static final long serialVersionUID = -5476218567508124529L;
	
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
