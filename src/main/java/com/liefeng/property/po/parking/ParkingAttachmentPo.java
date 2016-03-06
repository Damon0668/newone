package com.liefeng.property.po.parking;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 车位附件表
 * 
 * @author wuzhijing
 */
@Entity
@Table(name = "t_parking_attachment")
public class ParkingAttachmentPo extends BaseValue {

	private static final long serialVersionUID = -4904420370558927566L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 车位租售ID
	 */
	@Column(name="parking_rental_id")
	private String parkingRentalId;
	
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
