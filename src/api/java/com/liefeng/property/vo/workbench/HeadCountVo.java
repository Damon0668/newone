package com.liefeng.property.vo.workbench;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 报事各列表数量统计类
 * @author ZhenTingJun
 * @date 2016-03-25
 */
@ApiModel
@JsonInclude(Include.NON_EMPTY)
public class HeadCountVo extends BaseValue {
	
	private static final long serialVersionUID = -2619882546163875279L;

	/**
	 * 待签收列表数量
	 */
	@ApiModelProperty(value="待签收数量")
	private Long waitSignCount;
	
	/**
	 * 抢单列表数量
	 */
	@ApiModelProperty(value="可抢单数量")
	private Long grabCount;
	
	/**
	 * 待办理列表数量
	 */
	@ApiModelProperty(value="待办理数量")
	private Long waitDealCount;
	
	/**
	 * 流转中列表数量
	 */
	@ApiModelProperty(value="流转中数量")
	private Long flowingCount;
	
	/**
	 * 已完成列表数量
	 */
	@ApiModelProperty(value="已完成数量")
	private Long completeCount;
	
	/**
	 * 本月办结列表数量
	 */
	@ApiModelProperty(value="本月办结数量")
	private Long monthCompleteCount;
	
	/**
	 * 工作流待办理数量
	 */
	@ApiModelProperty(value="工作流待办理数量")
	private Long workFlowWaitCount;
	
	public Long getWaitSignCount() {
		return waitSignCount;
	}

	public void setWaitSignCount(Long waitSignCount) {
		this.waitSignCount = waitSignCount;
	}

	public Long getGrabCount() {
		return grabCount;
	}

	public void setGrabCount(Long grabCount) {
		this.grabCount = grabCount;
	}

	public Long getWaitDealCount() {
		return waitDealCount;
	}

	public void setWaitDealCount(Long waitDealCount) {
		this.waitDealCount = waitDealCount;
	}

	public Long getFlowingCount() {
		return flowingCount;
	}

	public void setFlowingCount(Long flowingCount) {
		this.flowingCount = flowingCount;
	}

	public Long getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Long completeCount) {
		this.completeCount = completeCount;
	}

	public Long getMonthCompleteCount() {
		return monthCompleteCount;
	}

	public void setMonthCompleteCount(Long monthCompleteCount) {
		this.monthCompleteCount = monthCompleteCount;
	}

	public Long getWorkFlowWaitCount() {
		return workFlowWaitCount;
	}

	public void setWorkFlowWaitCount(Long workFlowWaitCount) {
		this.workFlowWaitCount = workFlowWaitCount;
	}
	
}
