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
	 * 状态。0：未签收；1：待签收
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 消耗材料
	 */
	@Column(name = "consumptions")
	private String consumptions;
	
	/**
	 * 服务及时性。0：不及时；1：及时
	 */
	@Column(name = "timeliness")
	private String timeliness;
	
	/**
	 * 服务水准。0：差；1：一般；2：好
	 */
	@Column(name = "level")
	private String level;
	
	/**
	 * 服务态度。0：差；1：一般；2：好
	 */
	@Column(name = "attitude")
	private String attitude;
	
	/**
	 * 回访方式。1：电话；2：上门
	 */
	@Column(name = "revisit_mode")
	private String revisitMode;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(String consumptions) {
		this.consumptions = consumptions;
	}

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	public String getRevisitMode() {
		return revisitMode;
	}

	public void setRevisitMode(String revisitMode) {
		this.revisitMode = revisitMode;
	}
}
