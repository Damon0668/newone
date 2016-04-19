package com.liefeng.property.bo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁模块
 * 服务人员信息参数封装类
 * @author xhw
 * @date 2016年4月19日 下午4:39:40
 */
public class GuardAttendantBo extends BaseValue{
	
	private static final long serialVersionUID = 678237757990577588L;

	private String projectId;
	
	private String name;
	
	private String phone;
	
	private String serviceType;

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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
}
