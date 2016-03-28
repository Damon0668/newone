package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 访客id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class VisitorIdRo extends BaseValue {

	private static final long serialVersionUID = -8985486305223923581L;
	
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
