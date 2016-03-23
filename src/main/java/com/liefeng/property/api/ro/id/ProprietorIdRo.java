package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业主id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class ProprietorIdRo {
	
	@ApiModelProperty(value="业主主键【id】", required=true)
	@NotNull
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
