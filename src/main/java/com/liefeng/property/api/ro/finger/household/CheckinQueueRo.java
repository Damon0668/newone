package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 入住办理请求参数
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
@ApiModel
public class CheckinQueueRo extends BaseValue {

	private static final long serialVersionUID = 8481418348917012824L;

	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="房间id", required=true)
	@NotNull
	private String houseId;
	
	@ApiModelProperty(value="手机端用户id", required=true)
	@NotNull
	private String userId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
