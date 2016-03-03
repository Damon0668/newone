package com.liefeng.property.vo.parking;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 车位附件值对象
 * 
 * @author wuzhijing
 */
public class ParkingAttachmentVo extends BaseValue {

	private static final long serialVersionUID = -4904420370558927566L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 车位租售ID
	 */
	private String parkingRentalId;
	
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

	public String getParkingRentalId() {
		return parkingRentalId;
	}

	public void setParkingRentalId(String parkingRentalId) {
		this.parkingRentalId = parkingRentalId;
	}
	
}
