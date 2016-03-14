package com.liefeng.property.bo.fee;

import com.liefeng.core.entity.BaseValue;

public class MeterSettingBo extends BaseValue {

	private static final long serialVersionUID = 4797947895718960813L;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 表类型
	 */
	private String type;

	public String getProjectId() {
		return projectId;
	}

	public String getType() {
		return type;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
