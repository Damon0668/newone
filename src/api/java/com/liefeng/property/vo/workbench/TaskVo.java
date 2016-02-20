package com.liefeng.property.vo.workbench;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String priority;
	
	/**
	 * 开始时间
	 */
	private Date start_time;
	
	/**
	 * 结束时间
	 */
	private Date end_time;
	
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
	private String creator_id;
	
	/**
	 * 办理人ID
	 */
	private String handler_id;
	
	/**
	 * 归档人ID
	 */
	private String archiver_id;
	
	/**
	 * 审核人ID
	 */
	private String reviewer_id;
	
	/**
	 * 创建时间
	 */
	private Date create_time;
	
	/**
	 * 办理时间
	 */
	private Date handle_time;
	
	/**
	 * 归档时间
	 */
	private Date archive_time;
	
	/**
	 * 审核时间
	 */
	private Date review_time;
	
	private String oem_code;

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

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
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

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getHandler_id() {
		return handler_id;
	}

	public void setHandler_id(String handler_id) {
		this.handler_id = handler_id;
	}

	public String getArchiver_id() {
		return archiver_id;
	}

	public void setArchiver_id(String archiver_id) {
		this.archiver_id = archiver_id;
	}

	public String getReviewer_id() {
		return reviewer_id;
	}

	public void setReviewer_id(String reviewer_id) {
		this.reviewer_id = reviewer_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getHandle_time() {
		return handle_time;
	}

	public void setHandle_time(Date handle_time) {
		this.handle_time = handle_time;
	}

	public Date getArchive_time() {
		return archive_time;
	}

	public void setArchive_time(Date archive_time) {
		this.archive_time = archive_time;
	}

	public Date getReview_time() {
		return review_time;
	}

	public void setReview_time(Date review_time) {
		this.review_time = review_time;
	}

	public String getOem_code() {
		return oem_code;
	}

	public void setOem_code(String oem_code) {
		this.oem_code = oem_code;
	}
}
