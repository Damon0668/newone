package com.liefeng.property.api.ro;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建用户反馈参数封装类
 * @author xhw
 * @date 2016年3月21日 下午8:44:07
 */
@ApiModel
public class ResidentFeedbackRo {
	@ApiModelProperty(value="房屋id", required=true)
	@NotNull
	private String houseId;
	
	@ApiModelProperty(value="住户/业主id", required=true)
	@NotNull
	private String residentId;
	
	@ApiModelProperty(value="是否为业主【0：否；1：是】", required=true)
	@NotNull
	private String isProprietor;

	@ApiModelProperty(value="内容", required=true)
	@NotNull
	private String content;

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

	public String getIsProprietor() {
		return isProprietor;
	}

	public void setIsProprietor(String isProprietor) {
		this.isProprietor = isProprietor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
