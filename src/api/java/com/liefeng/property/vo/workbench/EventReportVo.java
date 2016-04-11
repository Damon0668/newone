package com.liefeng.property.vo.workbench;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 报事值对象
 * @author Huangama
 * @date 2016-3-3
 */
@ApiModel(value="报事")
@JsonInclude(Include.NON_EMPTY)
public class EventReportVo extends BaseValue {

	private static final long serialVersionUID = -2581787604165953419L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;

	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value="流程实例id")
	private String wfOrderId;
	
	/**
	 * 项目ID
	 */
	@ApiModelProperty(value="项目ID")
	private String projectId;

	/**
	 * 房间号
	 */
	@ApiModelProperty(value="房间号")
	private String houseNum;

	/**
	 * 业主姓名
	 */
	@ApiModelProperty(value="业主姓名")
	private String proprietorName;

	/**
	 * 报事人姓名
	 */
	@ApiModelProperty(value="报事人姓名")
	private String reporterName;

	/**
	 * 报事人电话
	 */
	@ApiModelProperty(value="报事人电话")
	private String phone;

	/**
	 * 报事时间
	 */
	@ApiModelProperty(value="报事时间")
	private Date reportTime;

	/**
	 * 报事方式。1：来电；2：APP客户端；3：上门。
	 */
	@ApiModelProperty(value="报事方式【1：来电；2：APP客户端；3：上门】")
	private String reportMode;

	/**
	 * 事件类型
	 */
	@ApiModelProperty(value="事件类型")
	private String eventType;

	/**
	 * 事件具体类别
	 */
	@ApiModelProperty(value="事件具体类别")
	private String category;

	/**
	 * 位置
	 */
	@ApiModelProperty(value="位置")
	private String location;

	/**
	 * 事件主题
	 */
	@ApiModelProperty(value="事件主题")
	private String title;
	
	/**
	 * 事件内容
	 */
	@ApiModelProperty(value="事件内容")
	private String content;
	
	/**
	 * 状态。0：未处理；1：已派工；2：已反馈。【app：0：未处理，1：已派工，2：未评价，3：已完成】
	 */
	@ApiModelProperty(value="当前状态【0：未处理，1：已派工，2：未评价，3：已完成】")
	private String status;
	

	private String priority = "00";
	
	/**
	 * 服务类型。0：免费；1：收费
	 */
	@ApiModelProperty(value="服务类型【0：免费；1：收费】")
	private String serviceType;
	
	/**
	 * 照片URL
	 */
	@ApiModelProperty(value="照片URL")
	private String picUrl;
	
	/**
	 * 预约时间
	 */
	@ApiModelProperty(value="预约时间")
	private Date orderTime;
	
	/**
	 * 处理结果
	 */
	@ApiModelProperty(value="处理结果")
	private String result;
	
	/**
	 * 录入员工ID
	 */
	@ApiModelProperty(value="录入员工ID")
	private String staffId;
	
	/**
	 * 受理人ID
	 */
	@ApiModelProperty(value="受理人ID")
	private String accepterId;
	
	/**
	 * 受理时间
	 */
	@ApiModelProperty(value="受理时间")
	private Date acceptTime;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
	private String oemCode;
	
	/**
	 * 任务id
	 */
	@ApiModelProperty(value="任务id")
	private String taskId;
	
	/**
	 * 流程id
	 */
	@ApiModelProperty(value="流程id")
	private String processId;
	
	/**
	 * 工单id
	 */
	@ApiModelProperty(value="工单id")
	private String orderId;
	
	/**
	 * 任务显示名称
	 */
	@ApiModelProperty(value="任务显示名称")
	private String taskDisplayName;
	
	/**
	 * 工单号
	 */
	@ApiModelProperty(value="工单号")
	private String orderNo;
	
	/**
	 *	当前任务
	 */
	@ApiModelProperty(value="当前任务")
	private String taskName;
	
	/**
	 * 审核状态
	 */
	@ApiModelProperty(value="审核状态")
	private String auditStatus;
	
	/**
	 * 当前处理状态 0：未签收；1：待处理 2:退回 3：办结
	 */
	@ApiModelProperty(value="处理状态【 0：未签收；1：待处理 2:退回 3：办结】")
	private String processStatus;
	
	/**
	 * 是否是上一步骤处理人 0 不是     1 是
	 */
	@ApiModelProperty(value="是否是上一步骤处理人 0 不是     1 是")
	private String isBack;
	
	/**
	 * 负责人姓名
	 */
	@ApiModelProperty(value="负责人姓名")
	private String workerName;
	
	/**
	 * 负责人手机号码
	 */
	@ApiModelProperty(value="负责人手机号码")
	private String workerPhone;
	
	/**
	 * 派工时间
	 */
	@ApiModelProperty(value="派工时间")
	private Date workTime;
	
	/**
	 * 完成时间
	 */
	@ApiModelProperty(value="完成时间")
	private Date overTime;
	
	/**
	 * 结果描述
	 */
	@ApiModelProperty(value="结果描述")
	private String remark;
	
	/**
	 * 结果图片
	 */
	@ApiModelProperty(value="结果图片")
	private String rebackPic;
	
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value="项目名称")
	private String projectName;
	
	/**
	 * 服务及时性。0：不及时；1：及时
	 */
	@ApiModelProperty("服务及时性【0：不及时；1：及时】")
	private String timeliness;
	
	/**
	 * 服务水准。0：差；1：一般；2：好
	 */
	@ApiModelProperty("服务水准【0：差；1：一般；2：好】")
	private String level;
	
	/**
	 * 服务态度。0：差；1：一般；2：好
	 */
	@ApiModelProperty("服务态度【0：差；1：一般；2：好】")
	private String attitude;
	
	/**
	 * 回访方式。1：电话；2：上门
	 */
	@ApiModelProperty("回访方式【1：电话；2：上门】")
	private String revisitMode;

	/**
	 * 直接反馈
	 */
	@ApiModelProperty("直接反馈【1：是】")
	private String directFeekback;
	
	/**
	 * 报事处理过程列表
	 */
	@ApiModelProperty("报事处理过程列表")
	private List<EventProcessVo> eventProcessList; 
	
	@ApiModelProperty(value="重要程度【1：重要、0：非重要】")
	public Character getImportant() {
		return priority.charAt(1);
	}

	public void setImportant(Character important) {
		priority = Character.valueOf(priority.charAt(0)).toString() + important.toString();
	}

	@ApiModelProperty(value="紧急程度【1：紧急、0：非紧急】")
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

	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	
	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerPhone() {
		return workerPhone;
	}

	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRebackPic() {
		return rebackPic;
	}

	public void setRebackPic(String rebackPic) {
		this.rebackPic = rebackPic;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public String getRevisitMode() {
		return revisitMode;
	}

	public void setRevisitMode(String revisitMode) {
		this.revisitMode = revisitMode;
	}

	public List<EventProcessVo> getEventProcessList() {
		return eventProcessList;
	}

	public void setEventProcessList(List<EventProcessVo> eventProcessList) {
		this.eventProcessList = eventProcessList;
	}

	public String getDirectFeekback() {
		return directFeekback;
	}

	public void setDirectFeekback(String directFeekback) {
		this.directFeekback = directFeekback;
	}

}
