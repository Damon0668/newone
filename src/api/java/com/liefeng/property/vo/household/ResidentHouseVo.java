package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户房屋信息值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月15日
 */
public class ResidentHouseVo extends BaseValue {

	private static final long serialVersionUID = 4167344981477126367L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 住户ID
	 */
	private String residentId;

	/**
	 * 项目ID（小区ID）
	 */
	private String projectId;

	/**
	 * 房子ID
	 */
	private String houseId;
	
	/**
	 * 房间号
	 */
	private String houseNum;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 业主ID
	 */
	private String proprietorId;
	
	/**
	 * 住户类型 1：常住；2：暂住
	 */
	private String residentType;
	
	/**
	 * 与业主关系
	 */
	private String residentRelation;
	
	/**
	 * 入住时间
	 */
	private Date checkinDate;
	
	/**
	 * 入住截止时间
	 */
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
