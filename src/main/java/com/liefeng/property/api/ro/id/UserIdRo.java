package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class UserIdRo extends BaseValue {

	private static final long serialVersionUID = -8828334500023971126L;
	
	@ApiModelProperty(value="用户ID", required=true)
	@NotNull
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
