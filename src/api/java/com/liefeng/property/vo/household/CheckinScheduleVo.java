package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 入住时间安排值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
public class CheckinScheduleVo extends BaseValue {

	private static final long serialVersionUID = -1845725456569409343L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
