package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁模块
 * 访客信息参数封装类
 * @author xhw
 * @date 2016年4月14日 下午3:32:07
 */
public class GuardVisitorBo extends BaseValue{
	
	private static final long serialVersionUID = -3056919127418772367L;

	public String projectId;
	
	public String name;
	
	public String phone;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
