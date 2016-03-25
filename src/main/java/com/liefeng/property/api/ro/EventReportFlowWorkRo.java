package com.liefeng.property.api.ro;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 报事工单处理 请求参数类
 * 用于：签收\抢单、退回、撤回
 * @author wuzhijing
 * @date 2016年3月24日13:53:09
 */
@ApiModel
public class EventReportFlowWorkRo {
	
	@ApiModelProperty(value="任务id", required=true)
	@NotNull
	public String wfTaskId;
	
	@ApiModelProperty(value="当前登陆人的id", required=true)
	@NotNull
	public String staffid;

	public String getWfTaskId() {
		return wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
}
