package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 检测业主登记状态的参数
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
@ApiModel
public class ProprietorRo extends BaseValue {

	private static final long serialVersionUID = 4226522554600517287L;

	@ApiModelProperty(value="业主id", required=true)
	@NotNull
	private String proprietorId;
	
	@ApiModelProperty(value="头像url", required=true)
	@NotNull
	private String picUrl;
	
	@ApiModelProperty(value="性别", required=true)
	@NotNull
	private String sex;

	@ApiModelProperty(value="工作单位", required=true)
	@NotNull
	private String workUnit;

	@ApiModelProperty(value="紧急联系人", required=true)
	@NotNull
	private String emergencyContact;
	
	@ApiModelProperty(value="紧急联系人手机号码", required=true)
	@NotNull
	private String emergencyPhone;

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

}
