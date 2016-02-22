package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 任务信息持久化对象
 * @author XHW
 * @date 2016年2月19日下午4:57:01
 */
@Entity
@Table(name="t_task")
public class TaskPo extends BaseValue{

	private static final long serialVersionUID = -8936982013233834167L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 任务主题
	 */
	@Column(name="title")
	private String title;
	
	/**
	 * 任务内容
	 */
	@Column(name="content")
	private String content;
	
	/**
	 * 任务状态。1：待处理；2：处理中；3：已处理；4：已审核；5：已归档
	 */
	@Column(name="status")
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要
	 */
	@Column(name="priority")
	private String priority;
	
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
	 * 办理结果
	 */
	@Column(name="result")
	private String result;
	
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
	
	/**
	 * 创建人ID
	 */
	@Column(name="creator_id")
	private String creatorId;
	
	/**
	 * 办理人ID
	 */
	@Column(name="handler_id")
	private String handlerId;
	
	/**
	 * 归档人ID
	 */
	@Column(name="archiver_id")
	private String archiverId;
	
	/**
	 * 审核人ID
	 */
	@Column(name="reviewer_id")
	private String reviewerId;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Date createTime;
	
	/**
	 * 办理时间
	 */
	@Column(name="handle_time")
	private Date handleTime;
	
	/**
	 * 归档时间
	 */
	@Column(name="archive_time")
	private Date archiveTime;
	
	/**
	 * 审核时间
	 */
	@Column(name="review_time")
	private Date reviewTime;
	
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	public String getArchiverId() {
		return archiverId;
	}

	public void setArchiverId(String archiverId) {
		this.archiverId = archiverId;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


	
	
}
