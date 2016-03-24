package com.liefeng.property.api.ro.work.staff;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateStaffRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7212333808448616930L;
	
	@ApiModelProperty(value="员工ID", required=true)
	@NotNull
	private String staffId;
	
	@ApiModelProperty(value="头像")
	private String portraitUrl;
	
	@ApiModelProperty(value="名字")
	private String name;
	
	@ApiModelProperty(value="性别[0：未知；1：男；2：女]")
	private String sex;
	
	@ApiModelProperty(value="年龄")
	private Integer age;
	
	@ApiModelProperty(value="手机")
	private String phone;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
