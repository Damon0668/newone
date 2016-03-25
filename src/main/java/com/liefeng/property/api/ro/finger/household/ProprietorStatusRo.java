package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 检测业主登记状态的参数
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
@ApiModel
public class ProprietorStatusRo {
	@ApiModelProperty(value="业主id", required=true)
	@NotNull
	private String proprietorId;
	
	@ApiModelProperty(value="手机端用户id", required=true)
	@NotNull
	private String userId;
	
	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="房间id", required=true)
	@NotNull
	private String houseId;

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
}
