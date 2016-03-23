package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房间id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class HouseIdRo {
	
	@ApiModelProperty(value="房间id", required=true)
	@NotNull
	private String houseId;

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
}
