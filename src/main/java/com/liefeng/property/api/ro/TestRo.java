package com.liefeng.property.api.ro;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 请求对象
 * @author 蔡少东
 * @date 2016年3月17日
 */
@ApiModel
public class TestRo {
	
	@ApiModelProperty(value="主键",required=true)
	@NotNull
	private String id;
	
	@ApiModelProperty(value="姓名")
	private String name;
	
	public Integer age;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}