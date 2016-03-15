package com.liefeng.property.vo.workbench;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

public class EventProcessVo extends BaseValue{

	private static final long serialVersionUID = 6561854156758243751L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 事件ID
	 */
	private String eventId;
	
	
	/**
	 * 工作流任务ID
	 */
	private String wfTaskId;
	
	/**
	 * 当前办理人ID
	 */
	private String currAccepterId;
	
	/**
	 * 下一步办理人ID
	 */
	private String nextAccepterId;
	
	/**
	 * 协助办理人IDs。多个以分号隔开
	 */
	private String assistAccepterIds;
	
	/**
	 * 审核状态。0：不通过；1：通过
	 */
	private String auditStatus;
	
	/**
	 * 办理结果
	 */
	private String result;
	
	/**
	 * 是否抢单。0：否；1：是
	 */
	private String grab;
	
	/**
	 * 受理时间
	 */
	private Date acceptTime;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 任务显示名称
	 */
	private String taskDisplayName;
	
	/**
	 *	当前任务
	 */
	private String taskName;
	
	/**
	 * 当前办理人名称
	 */
	private String currAccepterName;
	
	/**
	 * 下一步办理人名称
	 */
	private String nextNccepterName;

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

	public String getTaskDisplayName() {
		return taskDisplayName;
	}

	public void setTaskDisplayName(String taskDisplayName) {
		this.taskDisplayName = taskDisplayName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getNextNccepterName() {
		return nextNccepterName;
	}

	public void setNextNccepterName(String nextNccepterName) {
		this.nextNccepterName = nextNccepterName;
	}

	public String getCurrAccepterName() {
		return currAccepterName;
	}

	public void setCurrAccepterName(String currAccepterName) {
		this.currAccepterName = currAccepterName;
	}
}