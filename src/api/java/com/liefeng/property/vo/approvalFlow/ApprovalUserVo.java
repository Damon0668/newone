package com.liefeng.property.vo.approvalFlow;

import io.swagger.annotations.ApiModelProperty;

import com.liefeng.core.entity.BaseValue;

public class ApprovalUserVo extends BaseValue{
	
	private static final long serialVersionUID = -6052779044098541798L;
	
	@ApiModelProperty(value="员工id")
	private String id;
	
	@ApiModelProperty(value="员工姓名")
	private String name;
	
	@ApiModelProperty(value="是否是默认【0/null 不是，1：是】")
	private String isDefault;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
