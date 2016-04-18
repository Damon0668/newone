package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 出入管理设备
 * 摄像头
 * @author 蔡少东
 * @date 2016年4月13日
 */
@Entity
@Table(name = "t_camera")
public class CameraPo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5306193631416973393L;

	/**
	 * 主键
	 */
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
	 * 摄像头序列号
	 */
	@Column(name = "camera_sn")
	private String cameraSn;
	
	/**
	 * 位置ID
	 */
	@Column(name = "position_id")
	private String positionId;
	
	/**
	 * 摄像头类型
	 * 0 独立
	 * 1 附属
	 */
	@Column(name = "type")
	private String type;
	
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

	public String getCameraSn() {
		return cameraSn;
	}

	public void setCameraSn(String cameraSn) {
		this.cameraSn = cameraSn;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
