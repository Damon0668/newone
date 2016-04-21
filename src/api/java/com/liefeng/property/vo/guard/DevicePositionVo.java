package com.liefeng.property.vo.guard;

import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

/**
 * 设备位置表
 * @author 蔡少东
 * @date 2016年4月11日
 */
public class DevicePositionVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4233127596772185968L;
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名字
	 */
	private String projectName;
	
	/**
	 * 位置名称
	 */
	private String name;
	
	/**
	 * 该位置放的设备类型
	 */
	@Dict(group="DEVICE_TYPE")
	private String deviceType;
	
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
