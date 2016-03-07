package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 报事持久化对象
 * @author Huangama
 * @date 2016-3-3
 */
@Entity
@Table(name="t_event_report")
public class EventReportPo extends BaseValue {

	private static final long serialVersionUID = -503816334942552780L;

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
	 * 房间号
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 业主姓名
	 */
	@Column(name = "proprietor_name")
	private String proprietorName;

	/**
	 * 报事人姓名
	 */
	@Column(name = "reporter_name")
	private String reporterName;

	/**
	 * 报事人电话
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 报事时间
	 */
	@Column(name = "report_time")
	private Date reportTime;

	/**
	 * 报事方式。1：来电；2：APP客户端；3：上门。
	 */
	@Column(name = "report_mode")
	private String reportMode;

	/**
	 * 事件类型
	 */
	@Column(name = "event_type")
	private String eventType;

	/**
	 * 事件具体类别
	 */
	@Column(name = "category")
	private String category;

	/**
	 * 位置
	 */
	@Column(name = "location")
	private String location;

	/**
	 * 事件主题
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 事件内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 状态。0：未处理；1：已派工；2：已反馈。
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要。
	 */
	@Column(name = "priority")
	private String priority;
	
	/**
	 * 服务类型。0：免费；1：收费
	 */
	@Column(name = "service_type")
	private String serviceType;
	
	/**
	 * 照片URL
	 */
	@Column(name = "pic_url")
	private String picUrl;
	
	/**
	 * 预约时间
	 */
	@Column(name = "order_time")
	private Date orderTime;
	
	/**
	 * 处理结果
	 */
	@Column(name = "result")
	private String result;
	
	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 受理人ID
	 */
	@Column(name = "accepter_id")
	private String accepterId;
	
	/**
	 * 受理时间
	 */
	@Column(name = "accept_time")
	private Date acceptTime;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code", updatable = false)
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

}
