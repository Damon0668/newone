package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目id、房间号
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class ProjectIdHouseNumRo extends BaseValue {

	private static final long serialVersionUID = -3029864022095788461L;

	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="房间号", required=true)
	@NotNull
	private String houseNum;
	

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

}
