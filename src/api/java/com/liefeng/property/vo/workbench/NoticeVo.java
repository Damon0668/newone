package com.liefeng.property.vo.workbench;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 通知值对象
 * @author xhw
 * @date 2016年2月26日下午3:29:17
 */
@ApiModel(value="通知信息")
@JsonInclude(Include.NON_EMPTY)
public class NoticeVo extends BaseValue{

	private static final long serialVersionUID = 7490040678039560355L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;
	
	/**
	 * 通知主题
	 */
	@ApiModelProperty(value="通知主题")
	private String title;
	
	/**
	 * 通知内容
	 */
	@ApiModelProperty(value="通知内容")
	private String content;
	
	/**
	 * 通知状态。1：待审核；2：审核不通过；3：待发布；4：待归档；5：已归档
	 */
	@ApiModelProperty(value="通知状态【1：待审核；2：审核不通过；3：待发布；4：待归档；5：已归档】")
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要
	 */
	@ApiModelProperty(value="紧急、重要程度【高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要】")
	private String priority = "00";
	
	/**
	 * 接收终端。0：全部；1：电视；2：电脑；3：移动设备
	 */
	@ApiModelProperty(value="接收终端【0：全部；1：电视；2：电脑；3：移动设备】")
	private String terminal;
	
	/**
	 * 类型。1：社区通告；2：温馨提醒；3：通知；4：社区动态
	 */
	@ApiModelProperty(value="通知类型【1：社区通告；2：温馨提醒；3：通知；4：社区动态】")
	private String type;
	
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value="开始时间")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value="结束时间")
	private Date endTime;
	
	/**
	 * 驳回原因
	 */
	@ApiModelProperty(value="驳回原因")
	private String reason;
	
	
	/**
	 * 创建人ID
	 */
	@ApiModelProperty(value="创建人ID")
	private String creatorId;
	
	/**
	 * 审核人ID
	 */
	@ApiModelProperty(value="审核人ID")
	private String reviewerId;
	
	/**
	 * 发布人ID。0表示系统发布
	 */
	@ApiModelProperty(value="发布人ID【0表示系统发布】")
	private String publisherId ;
	
	/**
	 * 归档人ID
	 */
	@ApiModelProperty(value="归档人ID")
	private String archiverId;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 审核时间
	 */
	@ApiModelProperty(value="审核时间")
	private Date reviewTime;
	
	/**
	 * 发布时间
	 */
	@ApiModelProperty(value="发布时间")
	private Date publishTime;
	
	/**
	 * 归档时间
	 */
	@ApiModelProperty(value="归档时间")
	private Date archiveTime;
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
	private String oemCode;
	
	/**
	 * 接收通知的员工信息
	 */
	@ApiModelProperty(value="接收通知的员工信息")
	private String staffMessage;
	
	/**
	 * 接收通知的业主信息
	 */
	@ApiModelProperty(value="接收通知的员工信息")
	private String proprietorMessage;
	
	/**
	 * 创建人名称
	 */
	@ApiModelProperty(value="创建人名称")
	private String creatorName;
	
	/**
	 * 通知发布范围
	 */
	@ApiModelProperty(value="通知发布范围")
	private String privilegeString;
	
	/**
	 * 接收端名称
	 */
	@ApiModelProperty(value="接收端名称")
	private String terminalNameString;
	
	/**
	 * 类型名称
	 */
	@ApiModelProperty(value="类型名称")
	private String typeName;
	
	/**
	 * 审核人姓名
	 */
	@ApiModelProperty(value="审核人姓名")
	private String reviewerName;
	
	/**
	 * 归档姓名
	 */
	@ApiModelProperty(value="归档姓名")
	private String archiverName;
	
	/**
	 * 发布人姓名
	 */
	@ApiModelProperty(value="发布人姓名")
	private String publisherName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getArchiverId() {
		return archiverId;
	}

	public void setArchiverId(String archiverId) {
		this.archiverId = archiverId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

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

	public String getStaffMessage() {
		return staffMessage;
	}

	public void setStaffMessage(String staffMessage) {
		this.staffMessage = staffMessage;
	}

	public String getProprietorMessage() {
		return proprietorMessage;
	}

	public void setProprietorMessage(String proprietorMessage) {
		this.proprietorMessage = proprietorMessage;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getPrivilegeString() {
		return privilegeString;
	}

	public void setPrivilegeString(String privilegeString) {
		this.privilegeString = privilegeString;
	}

	public String getTerminalNameString() {
		return terminalNameString;
	}

	public void setTerminalNameString(String terminalNameString) {
		this.terminalNameString = terminalNameString;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getArchiverName() {
		return archiverName;
	}

	public void setArchiverName(String archiverName) {
		this.archiverName = archiverName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
}
