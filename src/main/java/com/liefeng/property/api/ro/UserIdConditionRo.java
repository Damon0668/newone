package com.liefeng.property.api.ro;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户id、过滤条件
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class UserIdConditionRo {
	
	@ApiModelProperty(value="用户id", required=true)
	@NotNull
	private String userId;

	@ApiModelProperty(value="过滤条件", required=true)
	@NotNull
	private String conditon;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConditon() {
		return conditon;
	}

	public void setConditon(String conditon) {
		this.conditon = conditon;
	}

}
