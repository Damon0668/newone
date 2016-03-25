package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目id、房间号、手机号码
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class ProjectIdHouseNumPhoneRo {
	
	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="房间号", required=true)
	@NotNull
	private String houseNum;
	
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	private String phone;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
