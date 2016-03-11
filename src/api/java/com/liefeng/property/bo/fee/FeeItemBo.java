package com.liefeng.property.bo.fee;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

public class FeeItemBo extends BaseValue {

	private static final long serialVersionUID = 1686086443428551319L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

	/**
	 * 房号
	 */
	private String houseNum;
	
	/**
	 * 类型
	 */
	private String feeType;
	
	/**
	 * 缴费状态 
	 * 0：未交费；1：已正常缴费；2：过期缴费。
	 */
	private String status;
	
	/**
	 * 业主名称
	 */
	private String proprietorName;
	
	/**
	 * 开始时间
	 * @return
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 是否需要总和
	 * 空 为 算出总和
	 */
	private Boolean isSum;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
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

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}
}
