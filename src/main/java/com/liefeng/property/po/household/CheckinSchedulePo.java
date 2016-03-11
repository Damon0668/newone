package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 入住时间安排持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Entity
@Table(name = "t_checkin_schedule")
public class CheckinSchedulePo extends BaseValue {

	private static final long serialVersionUID = 7452550081999778246L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	@Column(name = "building_id")
	private String buildingId;
	
	/**
	 * 开始时间
	 */
	@Column(name = "start_date")
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	@Column(name = "end_date")
	private Date endDate;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
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
