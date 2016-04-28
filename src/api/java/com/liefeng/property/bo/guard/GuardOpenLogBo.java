package com.liefeng.property.bo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 开门日志查询参数类
 * 
 * @author ZhenTingJun
 * @date 2016年4月28日
 */
public class GuardOpenLogBo extends BaseValue {

	private static final long serialVersionUID = -4624548284622912480L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 项目ID串
	 */
	private String projectIds;
	
	/**
	 * 开门方式
	 */
	private String openType;

	/**
	 * 操作用户姓名
	 */
	private String operUserName;

	/**
	 * 设备类型
	 */
	private String guardDeviceType;

	/**
	 * 搜索开始时间
	 */
	private Date startDate;

	/**
	 * 搜索结束时间
	 */
	private Date endDate;

	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	public String getGuardDeviceType() {
		return guardDeviceType;
	}

	public void setGuardDeviceType(String guardDeviceType) {
		this.guardDeviceType = guardDeviceType;
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
