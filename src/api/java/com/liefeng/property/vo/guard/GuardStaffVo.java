package com.liefeng.property.vo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 出入管理
 * 员工信息
 * @author 蔡少东
 * @date 2016年4月28日
 */
public class GuardStaffVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8474948325161019879L;

	private String id;
	
	private String projectName;
	
	private String deptName;
	
	private String positionName;
	
	private String staffName;
	
	private String workStatus;
	
	private String mobile;
	
	private String cardId;
	
	private String cardSn;
	
	private String cardStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardSn() {
		return cardSn;
	}

	public void setCardSn(String cardSn) {
		this.cardSn = cardSn;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
}
