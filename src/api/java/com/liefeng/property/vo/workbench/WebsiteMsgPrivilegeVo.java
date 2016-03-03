package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 站内消息权限值对象
 * @author xhw
 * @2016年3月2日 下午4:56:25
 */
public class WebsiteMsgPrivilegeVo extends BaseValue{

	private static final long serialVersionUID = -347036358570721382L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 消息ID
	 */
	private String messageId;


	/**
	 * 项目ID。
	 */
	private String projectId;

	/**
	 * 部门ID
	 */
	private String departmentId;

	/**
	 * 员工ID
	 */
	private String staffId;


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


	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOemCode() {
		return oemCode;
	}


	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

}
