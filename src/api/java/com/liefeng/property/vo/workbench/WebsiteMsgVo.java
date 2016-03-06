package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 站内消息值对象
 * @author xhw
 * @2016年3月2日 下午3:47:49
 */
public class WebsiteMsgVo extends BaseValue{

	private static final long serialVersionUID = -5012246075501954611L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 消息类别。1：系统消息；2、个人消息。
	 */
	private String type;

	/**
	 * 回复的父消息ID。为空则表示没有父消息。
	 */
	private String parentId;

	/**
	 * 创建人ID
	 */
	private String creatorId;

	/**
	 * 创建时间
	 */
	private Date createTime;


	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 创建人姓名
	 */
	private String creatorName;

	/**
	 * 回复数量
	 */
	private Integer replyCount;
	
	/**
	 * 接收人姓名
	 */
	private String receiverName;
	
	/**
	 * 消息的接收人范围
	 */
	private String privilegeStr;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getOemCode() {
		return oemCode;
	}


	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCreatorName() {
		return creatorName;
	}


	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}


	public Integer getReplyCount() {
		return replyCount;
	}


	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}


	public String getReceiverName() {
		return receiverName;
	}


	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}


	public String getPrivilegeStr() {
		return privilegeStr;
	}


	public void setPrivilegeStr(String privilegeStr) {
		this.privilegeStr = privilegeStr;
	}

}
