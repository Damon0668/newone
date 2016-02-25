package com.liefeng.property.vo.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.liefeng.core.entity.BaseValue;

/**
 * 任务附件值对象
 * @author xhw
 * @date 2016年2月25日下午5:43:20
 */
public class TaskAttachmentVo extends BaseValue{

	private static final long serialVersionUID = -2133253474354990346L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 任务ID
	 */
	private String taskId;
	
	/**
	 * 创建人ID
	 */
	private String creatorId;
	
	
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	
	/**
	 * 文件URL
	 */
	private String fileUrl;
	
	/**
	 * 文件大小
	 */
	private Double fileSize;
	
	/**
	 * 源文件名
	 */
	private String fileName;
	
	/**
	 * 上传人姓名
	 */
	private String creatorName;

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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
