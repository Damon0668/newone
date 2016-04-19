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

	@ApiModelProperty(value = "全局ID,cust_global_id", required = true)
	@NotNull
	private String globalId;

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
}
