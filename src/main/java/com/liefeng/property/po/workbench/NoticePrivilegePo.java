package com.liefeng.property.po.workbench;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 通知持久化对象
 * @author xhw
 * @date 2016年2月26日下午3:18:34
 */
@Entity
@Table(name="t_notice_privilege")
public class NoticePrivilegePo extends BaseValue{

	private static final long serialVersionUID = 7541954144647201697L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 通知ID
	 */
	@Column(name = "notice_id")
	private String noticeId;

	/**
	 * 权限类型。1：对员工；2：对业主/住户
	 */
	@Column(name = "type ")
	private String type;

	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 群ID。如果被通知者是员工，则该字段为部门ID，-1表示该项目下所有部门都可见。如果被通知者是业主，则该字段为楼栋ID，-1
	 * 表示该项目下所有楼栋都可见
	 */
	@Column(name = "group_id")
	private String groupId;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code", updatable = false)
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
