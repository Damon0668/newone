package com.liefeng.property.vo.workbench;

import java.util.Date;

import javax.persistence.Column;

import com.liefeng.core.entity.BaseValue;

/**
 * 报事值对象
 * @author Huangama
 * @date 2016-3-3
 */
public class EventReportVo extends BaseValue {

	private static final long serialVersionUID = -2581787604165953419L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 流程实例id
	 */
	private String wfOrderId;
	
	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 房间号
	 */
	private String houseNum;

	/**
	 * 业主姓名
	 */
	private String proprietorName;

	/**
	 * 报事人姓名
	 */
	private String reporterName;

	/**
	 * 报事人电话
	 */
	private String phone;

	/**
	 * 报事时间
	 */
	private Date reportTime;

	/**
	 * 报事方式。1：来电；2：APP客户端；3：上门。
	 */
	private String reportMode;

	/**
	 * 事件类型
	 */
	private String eventType;

	/**
	 * 事件具体类别
	 */
	private String category;

	/**
	 * 位置
	 */
	private String location;

	/**
	 * 事件主题
	 */
	private String title;
	
	/**
	 * 事件内容
	 */
	private String content;
	
	/**
	 * 状态。0：未处理；1：已派工；2：已反馈。
	 */
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要。
	 */
	private String priority = "00";
	
	/**
	 * 服务类型。0：免费；1：收费
	 */
	private String serviceType;
	
	/**
	 * 照片URL
	 */
	private String picUrl;
	
	/**
	 * 预约时间
	 */
	private Date orderTime;
	
	/**
	 * 处理结果
	 */
	private String result;
	
	/**
	 * 录入员工ID
	 */
	private String staffId;
	
	/**
	 * 受理人ID
	 */
	private String accepterId;
	
	/**
	 * 受理时间
	 */
	private Date acceptTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 流程id
	 */
	private String processId;
	
	/**
	 * 工单id
	 */
	private String orderId;
	
	/**
	 * 任务显示名称
	 */
	private String taskDisplayName;
	
	/**
	 * 工单号
	 */
	private String orderNo;
	
	/**
	 *	当前任务
	 */
	private String taskName;
	
	/**
	 * 状态
	 */
	private String auditStatus;
	
	public Character getImportant() {
		return priority.charAt(1);
	}

	public void setImportant(Character important) {
		priority = Character.valueOf(priority.charAt(0)).toString() + important.toString();
	}

	public Character getEmergency() {
		return priority.charAt(0);
	}

	public void setEmergency(Character emergency) {
		priority = emergency.toString() + Character.valueOf(priority.charAt(1)).toString();
	}

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

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getWfOrderId() {
		return wfOrderId;
	}

	public void setWfOrderId(String wfOrderId) {
		this.wfOrderId = wfOrderId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTaskDisplayName() {
		return taskDisplayName;
	}

	public void setTaskDisplayName(String taskDisplayName) {
		this.taskDisplayName = taskDisplayName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
