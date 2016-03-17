package com.liefeng.property.controller.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TestRo {
	
	@ApiModelProperty(value="主键",required=true)
	private String id;
	
	@ApiModelProperty(value="姓名")
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
