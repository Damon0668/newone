package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业主id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class ProprietorIdRo extends BaseValue {

	private static final long serialVersionUID = 8877149417961857916L;
	
	@ApiModelProperty(value="业主ID", required=true)
	@NotNull
	private String proprietorId;

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}
	
}
