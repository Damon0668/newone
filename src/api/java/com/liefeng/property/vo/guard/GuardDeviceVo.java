package com.liefeng.property.vo.guard;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.liefeng.base.vo.device.DeviceVo;

/**
 * 门禁设备视图对象
 * @author Huangama
 * @date 2016-2-25
 */
public class GuardDeviceVo extends DeviceVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742515295286315350L;

	/**
	 * 设备全局ID。关联t_device表
	 */
	private String deviceGlobalId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 安装位置
	 */
	private String positionId;
	
	/**
	 * 安装位置名称
	 */
	private String positionName;
	
	/**
	 * 位置类型
	 */
	private String deviceType;
	
	/**
	 * 摄像头ID
	 */
	private String cameraId;
	
	/**
	 * 摄像头类型
	 */
	private String cameraType;
	
	/**
	 * 设备名称
	 */
	private String guardName;
	
	/**
	 * 门口机编号
	 */
	private String guardType;
	
	/**
	 * 门口机编号
	 */
	private String guardNum;
	
	/**
	 * 二维码URL
	 */
	private String qrCodeUrl;
	
	/**
	 * 厂家电话
	 */
	private String producerTel;
	
	/**
	 * 保修期限
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date warrantyDate;
	
	/**
	 * 购买日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date buyDate;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getDeviceGlobalId() {
		return deviceGlobalId;
	}

	public void setDeviceGlobalId(String deviceGlobalId) {
		this.deviceGlobalId = deviceGlobalId;
		super.setGlobalId(deviceGlobalId);
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public String getGuardType() {
		return guardType;
	}

	public void setGuardType(String guardType) {
		this.guardType = guardType;
	}

	public String getProducerTel() {
		return producerTel;
	}

	public void setProducerTel(String producerTel) {
		this.producerTel = producerTel;
	}

	public Date getWarrantyDate() {
		return warrantyDate;
	}

	public void setWarrantyDate(Date warrantyDate) {
		this.warrantyDate = warrantyDate;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGuardNum() {
		return guardNum;
	}

	public void setGuardNum(String guardNum) {
		this.guardNum = guardNum;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getCameraType() {
		return cameraType;
	}

	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}

	public String getGuardName() {
		return guardName;
	}

	public void setGuardName(String guardName) {
		this.guardName = guardName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
