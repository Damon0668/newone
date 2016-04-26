package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户迁出业务类
 * @author wuzhijng
 *
 */
public class MovedOutResidentBo extends BaseValue {

	private static final long serialVersionUID = 4955401686568255378L;
	
	private String projectId;
	
	private String name;
	
	private String housenum;
	
	private String startDate;
	
	private String endDate;

	public String getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public String getHousenum() {
		return housenum;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHousenum(String housenum) {
		this.housenum = housenum;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
