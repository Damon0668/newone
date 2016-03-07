package com.liefeng.property.bo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 报事
 * @author wuzhijing
 *
 */
public class EventReportBo  extends BaseValue {

	private static final long serialVersionUID = -8406440967733667320L;

	private String projectId;
	
	private String eventType;
	
	private String location;
	
	private Date createDate;
	
	private String orderNum;
	
	private String status;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
