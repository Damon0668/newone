package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 通知持久化对象
 * @author xhw
 * @date 2016年2月26日下午3:18:34
 */
@Entity
@Table(name="t_notice")
public class NoticePo extends BaseValue{

	private static final long serialVersionUID = -2999044688323017318L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 通知主题
	 */
	@Column(name="title")
	private String title;
	
	/**
	 * 通知内容
	 */
	@Column(name="content")
	private String content;
	
	/**
	 * 通知状态。1：待审核；2：审核不通过；3：待发布；4：待归档；5：已归档
	 */
	@Column(name="status")
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要
	 */
	@Column(name="priority")
	private String priority;
	
	/**
	 * 接收终端。0：全部；1：电视；2：电脑；3：移动设备
	 */
	@Column(name="terminal")
	private String terminal;
	
	/**
	 * 类型。1：社区通告；2：温馨提醒；3：通知；4：社区动态
	 */
	@Column(name="type")
	private String type;
	
	/**
	 * 开始时间
	 */
	@Column(name="start_time")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@Column(name="end_time")
	private Date endTime;
	
	/**
	 * 驳回原因
	 */
	@Column(name="reason")
	private String reason;
	
	
	/**
	 * 创建人ID
	 */
	@Column(name="creator_id")
	private String creatorId;
	
	/**
	 * 审核人ID
	 */
	@Column(name="reviewer_id")
	private String reviewerId;
	
	/**
	 * 发布人ID。0表示系统发布
	 */
	@Column(name="publisher_id ")
	private String publisherId ;
	
	/**
	 * 归档人ID
	 */
	@Column(name="archiver_id")
	private String archiverId;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 审核时间
	 */
	@Column(name="review_time")
	private Date reviewTime;
	
	/**
	 * 发布时间
	 */
	@Column(name="publish_time")
	private Date publishTime;
	
	/**
	 * 归档时间
	 */
	@Column(name="archive_time")
	private Date archiveTime;
	
	
	
	@Column(name="oem_code")
	private String oemCode;



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

	
}
