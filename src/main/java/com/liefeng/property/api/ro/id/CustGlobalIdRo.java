package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 关联客户全局唯一标识Id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class CustGlobalIdRo {
	
	@ApiModelProperty(value="关联客户全局唯一标识Id", required=true)
	@NotNull
	private String custGlobalId;

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	
}
