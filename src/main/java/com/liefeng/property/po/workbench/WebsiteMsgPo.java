package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 站内消息持久化对象
 * @author xhw
 * @2016年3月2日 下午3:38:07
 */
@Entity
@Table(name=" t_website_msg")
public class WebsiteMsgPo extends BaseValue{

	private static final long serialVersionUID = 359412495207380466L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 消息内容
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 消息类别。1：系统消息；2、个人消息。
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 回复的父消息ID。为空则表示没有父消息。
	 */
	@Column(name = "parent_id")
	private String parentId;

	/**
	 * 创建人ID
	 */
	@Column(name = "creator_id")
	private String creatorId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;


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


}
