package com.liefeng.property.bo.guard;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 设备位置查询
 * @author 蔡少东
 * @date 2016年4月12日
 */
public class DevicePositionBo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2827022538822977145L;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目ID组
	 */
	private List<String> projectIds;
	
	/**
	 * 
	 */
	private String oemCode;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
