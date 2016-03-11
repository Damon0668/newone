package com.liefeng.property.po.workbench;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 事件处理过程持久化对象
 * @author wuzhijing
 *
 */
@Entity
@Table(name="t_event_process")
public class EventProcessPo extends BaseValue{

	private static final long serialVersionUID = 6561854156758243751L;

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
	 * 工作流任务ID
	 */
	@Column(name = "wf_task_id")
	private String wfTaskId;
	
	/**
	 * 当前办理人ID
	 */
	@Column(name = "curr_accepter_id")
	private String currAccepterId;
	
	/**
	 * 下一步办理人ID
	 */
	@Column(name = "next_accepter_id")
	private String nextAccepterId;
	
	/**
	 * 协助办理人IDs。多个以分号隔开
	 */
	@Column(name = "assist_accepter_ids")
	private String assistAccepterIds;
	
	/**
	 * 审核状态。0：不通过；1：通过
	 */
	@Column(name = "audit_status")
	private String auditStatus;
	
	/**
	 * 办理结果
	 */
	@Column(name = "result")
	private String result;
	
	/**
	 * 是否抢单。0：否；1：是
	 */
	@Column(name = "grab")
	private String grab;
	
	/**
	 * 受理时间
	 */
	@Column(name = "accept_time")
	private Date acceptTime;
	
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getWfTaskId() {
		return wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public String getCurrAccepterId() {
		return currAccepterId;
	}

	public void setCurrAccepterId(String currAccepterId) {
		this.currAccepterId = currAccepterId;
	}

	public String getNextAccepterId() {
		return nextAccepterId;
	}

	public void setNextAccepterId(String nextAccepterId) {
		this.nextAccepterId = nextAccepterId;
	}

	public String getAssistAccepterIds() {
		return assistAccepterIds;
	}

	public void setAssistAccepterIds(String assistAccepterIds) {
		this.assistAccepterIds = assistAccepterIds;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getGrab() {
		return grab;
	}

	public void setGrab(String grab) {
		this.grab = grab;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
