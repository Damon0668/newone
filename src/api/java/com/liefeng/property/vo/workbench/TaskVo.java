package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 任务信息对象
 * @author XHW
 * @date 2016年2月19日下午5:16:48
 */
public class TaskVo extends BaseValue{

	private static final long serialVersionUID = 7997078179249977481L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 任务主题
	 */
	private String title;
	
	/**
	 * 任务内容
	 */
	private String content;
	
	/**
	 * 任务状态。1：待处理；2：处理中；3：已处理；4：已审核；5：已归档
	 */
	private String status;
	
	/**
	 * 优先级。高位表示紧急程度，低位表示重要程度，如：10表示紧急不重要；11表示紧急重要
	 */
	private String priority = "00";
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 办理结果
	 */
	private String result;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建人ID
	 */
	private String creatorId;
	
	/**
	 * 办理人ID
	 */
	private String handlerId;
	
	/**
	 * 归档人ID
	 */
	private String archiverId;
	
	/**
	 * 审核人ID
	 */
	private String reviewerId;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 办理时间
	 */
	private Date handleTime;
	
	/**
	 * 归档时间
	 */
	private Date archiveTime;
	
	/**
	 * 审核时间
	 */
	private Date reviewTime;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 任务的接收人范围
	 */
	private String affstr;
	
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

	public String getAffstr() {
		return affstr;
	}

	public void setAffstr(String affstr) {
		this.affstr = affstr;
	}

}
