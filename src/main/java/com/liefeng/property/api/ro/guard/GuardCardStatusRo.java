package com.liefeng.property.api.ro.guard;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GuardCardStatusRo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7195818209895587740L;

	@ApiModelProperty(value="磁卡ID", required=true)
	@NotNull
	private String cardId;
	
	@ApiModelProperty(value="磁卡状态[0-注销；1-正常；2-挂失]", required=true)
	@NotNull
	private String status;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
