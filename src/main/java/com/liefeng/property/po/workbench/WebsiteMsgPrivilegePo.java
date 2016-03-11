package com.liefeng.property.po.workbench;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 站内消息权限持久化对象
 * @author xhw
 * @2016年3月2日 下午4:50:26
 */
@Entity
@Table(name="t_website_msg_privilege")
public class WebsiteMsgPrivilegePo extends BaseValue{

	private static final long serialVersionUID = 4084913703229948489L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 消息ID
	 */
	@Column(name = "message_id")
	private String messageId;


	/**
	 * 项目ID。
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 部门ID
	 */
	@Column(name = "department_id")
	private String departmentId;

	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;


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
