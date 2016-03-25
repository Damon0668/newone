package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 事件办理人评价持久化对象
 * @author xhw
 * @date 2016年3月25日 下午1:37:36
 */
@Entity
@Table(name="t_event_accepter_eval")
public class EventAccepterEvalPo extends BaseValue{

	private static final long serialVersionUID = 7530720002186721363L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 事件ID
	 */
	@Column(name = "event_id")
	private String eventId;

	/**
	 * 办理人ID
	 */
	@Column(name = "accepter_id")
	private String accepterId;

	/**
	 * 0：不赞；1：赞
	 */
	@Column(name = "likes")
	private String likes;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
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
