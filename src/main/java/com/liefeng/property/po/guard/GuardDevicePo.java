package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁设备持久化对象
 * @author Huangama
 * @date 2016-2-25
 */
@Entity
@Table(name = "t_guard_device")
public class GuardDevicePo extends BaseValue {

	private static final long serialVersionUID = -3997601295625851290L;

	@Id
	private String id;
	
	/**
	 * 设备全局ID。关联t_device表
	 */
	@Column(name = "device_global_id")
	private String deviceGlobalId;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	@Column(name = "building_id")
	private String buildingId;
	
	/**
	 * 安装位置区域
	 */
	@Column(name = "position_area")
	private String positionArea;
	
	/**
	 * 厂家电话
	 */
	@Column(name = "producerTel")
	private String producer_tel;
	
	/**
	 * 保修期限
	 */
	@Column(name = "warrantyDate")
	private Date warrantyDate;
	
	/**
	 * 购买日期
	 */
	@Column(name = "buy_date")
	private Date buyDate;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
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

	public String getProducer_tel() {
		return producer_tel;
	}

	public void setProducer_tel(String producer_tel) {
		this.producer_tel = producer_tel;
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
