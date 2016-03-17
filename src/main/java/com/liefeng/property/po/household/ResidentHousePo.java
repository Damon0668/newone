package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户房屋信息值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月15日
 */
@Entity
@Table(name = "t_resident_house")
public class ResidentHousePo extends BaseValue {

	private static final long serialVersionUID = -253411193095610414L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 住户ID
	 */
	@Column(name = "resident_id")
	private String residentId;

	/**
	 * 项目ID（小区ID）
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 房子ID
	 */
	@Column(name = "house_id")
	private String houseId;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;
	
	/**
	 * 业主ID
	 */
	@Column(name = "proprietor_id")
	private String proprietorId;
	
	/**
	 * 住户类型 1：常住；2：暂住
	 */
	@Column(name = "resident_type")
	private String residentType;
	
	/**
	 * 与业主关系
	 */
	@Column(name = "resident_relation")
	private String residentRelation;
	
	/**
	 * 入住时间
	 */
	@Column(name = "checkin_date")
	private Date checkinDate;
	
	/**
	 * 入住截止时间
	 */
	@Column(name = "checkup_date")
	private Date checkupDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getResidentType() {
		return residentType;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckupDate() {
		return checkupDate;
	}

	public void setCheckupDate(Date checkupDate) {
		this.checkupDate = checkupDate;
	}

}
