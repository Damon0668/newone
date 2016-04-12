package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡查询参数类
 * 
 * @author ZhenTingJun
 * @date 2016年4月12日
 */
public class GuardCardBo extends BaseValue {

	private static final long serialVersionUID = 6924034923647296185L;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目ID串
	 */
	private String projectIds;

	/**
	 * 持卡人名字
	 */
	private String userName;

	/**
	 * 设备状态
	 */
	private String status;

	/**
	 * 结束时间查询类型
	 */
	private String searchType;

	/**
	 * 系统标识
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
