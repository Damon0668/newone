package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

public class EventProcAttachVo extends BaseValue {

	private static final long serialVersionUID = -506951763106489116L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 事件处理过程ID
	 */
	private String eventProcessId;
	
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
	private String fileSize;
	
	/**
	 * 源文件名
	 */
	private String fileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventProcessId() {
		return eventProcessId;
	}

	public void setEventProcessId(String eventProcessId) {
		this.eventProcessId = eventProcessId;
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
