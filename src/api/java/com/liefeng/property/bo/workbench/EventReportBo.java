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

	/**
	 * 项目id
	 */
	private String projectId;
	
	/**
	 * 事件类型
	 */
	private String eventType;
	
	/**
	 * 办理时间
	 */
	private String location;
	
	/**
	 * 报事时间
	 */
	private Date createDate;
	
	/**
	 * 工单号
	 */
	private String orderNo;
	
	/**
	 * 处理状态
	 */
	private String status;
	
	/**
	 * 流程id
	 */
	private String processId;
	
	/**
	 * 参与者id
	 */
	private String actor;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 步骤名称
	 */
	private String taskName;
	
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
