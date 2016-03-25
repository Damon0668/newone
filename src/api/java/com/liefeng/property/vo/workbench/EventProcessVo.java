package com.liefeng.property.vo.workbench;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 事件处理过程值对象
 * @author ZhenTingJun
 * @date 2016-03-25
 */
@ApiModel
@JsonInclude(Include.NON_EMPTY)
public class EventProcessVo extends BaseValue{

	private static final long serialVersionUID = 6561854156758243751L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;
	
	/**
	 * 事件ID
	 */
	@ApiModelProperty(value="事件ID")
	private String eventId;
	
	
	/**
	 * 工作流任务ID
	 */
	@ApiModelProperty(value="工作流任务ID")
	private String wfTaskId;
	
	/**
	 * 当前办理人ID
	 */
	@ApiModelProperty(value="当前办理人ID")
	private String currAccepterId;
	
	/**
	 * 下一步办理人ID
	 */
	@ApiModelProperty(value="下一步办理人ID")
	private String nextAccepterId;
	
	/**
	 * 协助办理人IDs。多个以分号隔开
	 */
	@ApiModelProperty(value=" 协助办理人IDs。多个以分号隔开")
	private String assistAccepterIds;
	
	/**
	 * 审核状态。0：不通过；1：通过
	 */
	@ApiModelProperty(value="审核状态【0：不通过；1：通过】")
	private String auditStatus;
	
	/**
	 * 办理结果
	 */
	@ApiModelProperty(value="办理结果")
	private String result;
	
	/**
	 * 是否抢单。0：否；1：是
	 */
	@ApiModelProperty(value="是否抢单【0：否；1：是】")
	private String grab;
	
	/**
	 * 受理时间
	 */
	@ApiModelProperty(value="受理时间")
	private Date acceptTime;
	
	/**
	 * 状态。0：未签收；1：待签收；2:退回；3：办结
	 */
	@ApiModelProperty(value="状态【0：未签收；1：待签收；2:退回；3：办结】")
	private String status;
	
	/**
	 * 消耗材料
	 */
	@ApiModelProperty(value="消耗材料")
	private String consumptions;
	
	/**
	 * 服务及时性。0：不及时；1：及时
	 */
	@ApiModelProperty(value="服务及时性【0：不及时；1：及时】")
	private String timeliness;
	
	/**
	 * 服务水准。0：差；1：一般；2：好
	 */
	@ApiModelProperty(value="服务水准【0：差；1：一般；2：好】")
	private String level;
	
	/**
	 * 服务态度。0：差；1：一般；2：好
	 */
	@ApiModelProperty(value="服务态度【0：差；1：一般；2：好】")
	private String attitude;
	
	/**
	 * 回访方式。1：电话；2：上门
	 */
	@ApiModelProperty(value="回访方式【1：电话；2：上门】")
	private String revisitMode;
	
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
	private String oemCode;
	
	/**
	 * 任务显示名称
	 */
	@ApiModelProperty(value="任务显示名称")
	private String taskDisplayName;
	
	/**
	 *	当前任务
	 */
	@ApiModelProperty(value="当前任务【distributeLeaflets:派单,dispatching:派工,handle:办理,audit:领导审核,returnVisit:客服回访】,可根据任务名称来显示下步操作按钮")
	private String taskName;
	
	/**
	 * 当前办理人名称
	 */
	@ApiModelProperty(value="当前办理人名称")
	private String currAccepterName;
	
	/**
	 * 下一步办理人名称
	 */
	@ApiModelProperty(value="下一步办理人名称")
	private String nextAccepterName;
	
	/**
	 * 协助办理人名称
	 */
	@ApiModelProperty(value="协助办理人名称")
	private String assistAccepterName;
	
	/**
	 * 附件
	 */
	@ApiModelProperty(value="附件列表")
	private List<EventProcAttachVo> attachs;
	
	/**
	 * 图片地址
	 */
	@ApiModelProperty(value="图片地址")
	private String picUrls;
	
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

	public String getNextAccepterName() {
		return nextAccepterName;
	}

	public void setNextAccepterName(String nextAccepterName) {
		this.nextAccepterName = nextAccepterName;
	}

	public String getCurrAccepterName() {
		return currAccepterName;
	}

	public void setCurrAccepterName(String currAccepterName) {
		this.currAccepterName = currAccepterName;
	}

	public String getAssistAccepterName() {
		return assistAccepterName;
	}

	public void setAssistAccepterName(String assistAccepterName) {
		this.assistAccepterName = assistAccepterName;
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

	public List<EventProcAttachVo> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<EventProcAttachVo> attachs) {
		this.attachs = attachs;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
}