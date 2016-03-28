package com.liefeng.property.api.ro.work.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 报事工单处理执行请求类
 * @author xhw
 */
@ApiModel
public class ExecuteEventReporRo extends BaseValue {
	
	private static final long serialVersionUID = 3552346260809942769L;

	/**
	 * 工单处理id
	 */
	@ApiModelProperty(value="工单处理id", required=true)
	@NotNull
	private String id;
	
	/**
	 * 报事ID
	 */
	@ApiModelProperty(value="报事ID", required=true)
	@NotNull
	private String eventId;
	
	
	/**
	 * 工作流任务ID
	 */
	@ApiModelProperty(value="工作流任务ID", required=true)
	@NotNull
	private String wfTaskId;
	
	/**
	 * 当前办理人ID
	 */
	@ApiModelProperty(value="当前办理人ID", required=true)
	@NotNull
	private String currAccepterId;
	
	/**
	 * 下一步办理人ID
	 */
	@ApiModelProperty(value="下一步办理人ID,多个以分号隔开",notes="除了领导审核不通过之外，其他的都必须指定")
	private String nextAccepterId;
	
	/**
	 * 协助办理人IDs。多个以分号隔开
	 */
	@ApiModelProperty(value="协助办理人IDs,多个以分号隔开")
	private String assistAccepterIds;
	
	/**
	 * 审核状态。0：不通过；1：通过
	 */
	@ApiModelProperty(value="审核状态【0：不通过；1：通过】",notes="领导审核时使用")
	private String auditStatus;
	
	/**
	 * 办理结果
	 */
	@ApiModelProperty(value="办理结果")
	private String result;
	
	/**
	 * 是否抢单。0：否；1：是
	 */
	@ApiModelProperty(value="是否抢单【0：否；1：是】",notes="办理时必须填写")
	private String grab;
	
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
	 * 图片
	 */
	@ApiModelProperty(value="图片地址,多个以分号隔开")
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

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
}
