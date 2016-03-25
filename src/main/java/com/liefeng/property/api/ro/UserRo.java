package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 修改用户资料参数封装类
 * @author xhw
 * @date 2016年3月21日 下午7:13:02
 */
@ApiModel
public class UserRo {
	
	@ApiModelProperty(value="关联客户全局唯一标识", required=true)
	@NotNull
	private String custGlobalId;
	
	@ApiModelProperty(value="昵称")
	private String nickName;
	
	@ApiModelProperty(value="头像")
	private String avatarUrl;
	
	@ApiModelProperty(value="邮箱地址")
	private String email;
	
	@ApiModelProperty(value="性别")
	private String sex;
	
	@ApiModelProperty(value="婚姻状况【0：未知；1：未婚；2：已婚】")
	private String maritalStatus;
	
	@ApiModelProperty(value="身高")
	private Double height;
	
	@ApiModelProperty(value="步长")
	private Double step;
	
	@ApiModelProperty(value="体重")
	private Double weight;
	
	@ApiModelProperty(value="出生日期")
	private String birthday;

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getStep() {
		return step;
	}

	public void setStep(Double step) {
		this.step = step;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
