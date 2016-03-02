package com.liefeng.property.po.workbench;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 消息权限持久化对象
 * @author xhw
 * @2016年3月2日 下午4:50:26
 */
@Entity
@Table(name="t_message_privilege")
public class MessagePrivilegePo extends BaseValue{

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
	 * 权限类型。1：对员工；2：对业主或住户；3：外部人员
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 项目ID。
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 群ID。如果被通知者是员工，则该字段为部门ID，-1表示该项目下所有部门都可见。如果被通知者是业主或住户，则该字段为楼栋ID，-1表示该项目下所有楼栋都可见。
	 */
	@Column(name = "group_id")
	private String groupId;

	/**
	 * 接收人ID
	 */
	@Column(name = "recipient_id")
	private String recipientId;


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
