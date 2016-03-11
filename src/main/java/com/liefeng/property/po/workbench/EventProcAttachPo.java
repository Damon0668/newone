package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.liefeng.core.entity.BaseValue;

/**
 * 事件处理过程附件持久化对象
 * @author wuzhijing
 */
public class EventProcAttachPo  extends BaseValue{

	private static final long serialVersionUID = 4456081425631290849L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 事件处理过程ID
	 */
	@Column(name="event_process_id")
	private String eventProcessId;
	
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
	private String fileSize;
	
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
