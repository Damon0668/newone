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
	
	@ApiModelProperty(value = "[业主|住户]ID")
	private String id;
	
	@ApiModelProperty(value = "[1:业主|2:住户]")
	private String type;
	
	@ApiModelProperty(value="与业主关系[[字典]RESIDENT_RELATION]")
	private String residentRelation;

	@ApiModelProperty(value = "项目ID")
	private String projectId;

	@ApiModelProperty(value = "项目名称")
	private String projectName;
	
	@ApiModelProperty(value = "楼栋ID")
	private String buildingId;
	
	@ApiModelProperty(value = "楼栋名称")
	private String buildingName;
	
	@ApiModelProperty(value = "楼层名称")
	private String floorName;

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

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
}
