package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房间id、住户id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class ResidentIdHouseIdRo {
	
	@ApiModelProperty(value="房间id", required=true)
	@NotNull
	private String houseId;

	@ApiModelProperty(value="住户id", required=true)
	@NotNull
	private String residentId;
	
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}
	
}
