package com.liefeng.property.po.guard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 设备位置表
 * @author 蔡少东
 * @date 2016年4月11日
 */
@Entity
@Table(name = "t_device_position")
public class DevicePositionPo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4233127596772185968L;
	/**
	 * 主键
	 */
	@Id
	private String id;
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 位置名称
	 */
	@Column(name = "name")
	private String name;
	
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
