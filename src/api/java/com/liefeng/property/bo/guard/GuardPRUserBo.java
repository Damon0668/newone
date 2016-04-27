package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁模块
 * 住户查询参数封装
 * @author 蔡少东
 * @date 2016年3月7日
 */
public class GuardPRUserBo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4919861177563696726L;

	public String projectId;
	
	public String buildingId;
	
	public String houseNum;
	
	public String name;
	
	public String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
