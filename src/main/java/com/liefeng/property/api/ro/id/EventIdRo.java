package com.liefeng.property.api.ro.id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 报事id
 * @author xhw
 * @date 2016年3月25日 下午3:34:16
 */
@ApiModel
public class EventIdRo {
	
	@ApiModelProperty(value="报事id", required=true)
	@NotNull
	private String eventId;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
