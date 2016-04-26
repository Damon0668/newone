package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户迁出业务类
 * @author wuzhijng
 *
 */
public class MovedOutResidentBo extends BaseValue {

	private static final long serialVersionUID = 4955401686568255378L;
	
	/**
	 * 项目id
	 */
	private String projectId;
	
	/**
	 * 业主名称
	 */
	private String name;
	
	/**
	 * 房号
	 */
	private String housenum;
	
	/**
	 * 开始时间
	 */
	private String startDate;
	
	/**
	 * 结束时间
	 */
	private String endDate;
	
	/**
	 * 业务类型 1：删除 2：迁出 3：迁入
	 */
	private String busitype;

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

	public String getBusitype() {
		return busitype;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
	}
}
