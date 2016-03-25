package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;


/**
 * 事件办理人评价值对象
 * @author xhw
 * @date 2016年3月25日 下午1:37:36
 */
public class EventAccepterEvalVo extends BaseValue{

	private static final long serialVersionUID = 365406936199920054L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 事件ID
	 */
	private String eventId;

	/**
	 * 办理人ID
	 */
	private String accepterId;

	/**
	 * 0：不赞；1：赞
	 */
	private String likes;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
