package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 任务附件持久化对象
 * @author xhw
 * @date 2016年2月25日下午5:37:32
 */
@Entity
@Table(name="t_task_attachment")
public class TaskAttachmentPo extends BaseValue{

	private static final long serialVersionUID = -3522287925143710070L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 任务ID
	 */
	@Column(name="task_id")
	private String taskId;
	
	/**
	 * 创建人ID
	 */
	@Column(name="creator_id")
	private String creatorId;
	
	
	/**
	 * 上传时间
	 */
	@Column(name="upload_time")
	private Date uploadTime;
	
	/**
	 * 文件URL
	 */
	@Column(name="file_url")
	private String fileUrl;
	
	/**
	 * 文件大小
	 */
	@Column(name="file_size")
	private Double fileSize;
	
	/**
	 * 源文件名
	 */
	@Column(name="file_name")
	private String fileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
