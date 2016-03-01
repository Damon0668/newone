package com.liefeng.property.vo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁设备视图对象
 * @author Huangama
 * @date 2016-2-25
 */
public class GuardDeviceVo extends BaseValue {

	private static final long serialVersionUID = -3314952455007904877L;

	private String id;
	
	/**
	 * 设备全局ID。关联t_device表
	 */
	private String deviceGlobalId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 安装位置区域
	 */
	private String positionArea;
	
	/**
	 * 摄像头序列号
	 */
	private String cameraSn;
	
	/**
	 * 厂家电话
	 */
	private String producerTel;
	
	/**
	 * 保修期限
	 */
	private Date warrantyDate;
	
	/**
	 * 购买日期
	 */
	private Date buyDate;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceGlobalId() {
		return deviceGlobalId;
	}

	public void setDeviceGlobalId(String deviceGlobalId) {
		this.deviceGlobalId = deviceGlobalId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getPositionArea() {
		return positionArea;
	}

	public void setPositionArea(String positionArea) {
		this.positionArea = positionArea;
	}

	public String getCameraSn() {
		return cameraSn;
	}

	public void setCameraSn(String cameraSn) {
		this.cameraSn = cameraSn;
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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
