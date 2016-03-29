package com.liefeng.property.vo.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.liefeng.core.entity.BaseValue;

/**
 * 用户房产信息
 * 
 * @author ZhenTingJun
 * @date 2016-03-28
 */
@ApiModel(value = "用户房产信息", parent = BaseValue.class)
public class UserHouseVo extends BaseValue {

	private static final long serialVersionUID = 6077014962625027337L;

	@ApiModelProperty(value = "项目ID")
	private String projectId;

	@ApiModelProperty(value = "项目名称")
	private String projectName;

	@ApiModelProperty(value = "房子ID")
	private String houseId;

	@ApiModelProperty(value = "房号")
	private String houseNum;
	
	@ApiModelProperty(value = "OEM编码")
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
