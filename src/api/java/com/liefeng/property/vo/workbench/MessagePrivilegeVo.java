package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 消息权限值对象
 * @author xhw
 * @2016年3月2日 下午4:56:25
 */
public class MessagePrivilegeVo extends BaseValue{

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
	 * 权限类型。1：对员工；2：对业主或住户；3：外部人员
	 */
	private String type;

	/**
	 * 项目ID。
	 */
	private String projectId;

	/**
	 * 群ID。如果被通知者是员工，则该字段为部门ID，-1表示该项目下所有部门都可见。如果被通知者是业主或住户，则该字段为楼栋ID，-1表示该项目下所有楼栋都可见。
	 */
	private String groupId;

	/**
	 * 接收人ID
	 */
	private String recipientId;

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


	public String getRecipientId() {
		return recipientId;
	}


	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}


	public String getOemCode() {
		return oemCode;
	}


	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}



}
