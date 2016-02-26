package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 通知权限持久化对象
 * @author xhw
 * @date 2016年2月26日下午5:51:58
 */
public class NoticePrivilegeVo extends BaseValue{
	
	
	private static final long serialVersionUID = 7705044693218607524L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 通知ID
	 */
	private String noticeId;
	
	/**
	 * 权限类型。1：对员工；2：对业主
	 */
	private String type;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 群ID。如果被通知者是员工，则该字段为部门ID，-1表示该项目下所有部门都可见。如果被通知者是业主，则该字段为楼栋ID，-1表示该项目下所有楼栋都可见
	 */
	private String groupId;
	
	/**
	 * 被通知者ID。如果被通知者是员工，则该字段为员工ID，-1表示部门下所有员工都可见。如果被通知者是业主，则该字段为业主ID，-1表示楼栋下所有业主都可见
	 */
	private String notifiederId ;
	
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

	public String getNotifiederId() {
		return notifiederId;
	}

	public void setNotifiederId(String notifiederId) {
		this.notifiederId = notifiederId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
