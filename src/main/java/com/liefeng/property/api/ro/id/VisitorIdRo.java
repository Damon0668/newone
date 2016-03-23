package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 访客id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class VisitorIdRo {
	
	@ApiModelProperty(value="访客id", required=true)
	@NotNull
	private String visitorId;

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}



}
