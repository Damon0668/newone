package com.liefeng.property.vo.approvalFlow;

import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.Map;

import org.snaker.engine.helper.JsonHelper;

import com.liefeng.core.entity.BaseValue;

public class HistoryTaskVo extends BaseValue {
	
	
	private static final long serialVersionUID = -328808192641072591L;
	
	@ApiModelProperty(value="任务id")
	private String id;
    
	@ApiModelProperty(value="流程实例id")
    private String orderId;
   
	@ApiModelProperty(value="流程步骤名称")
	private String taskName;
	
	@ApiModelProperty(value="流程步骤显示名称")
	private String displayName;
	
	private Integer performType;
	
    private Integer taskType;
    
    private Integer taskState;
   
    @ApiModelProperty(value="当前步骤操作人id")
    private String operator;
    
    @ApiModelProperty(value="当前步骤操作人名称")
    private String operatorName;
   
    @ApiModelProperty(value="任务创建时间")
    private String createTime;
    
    @ApiModelProperty(value="任务完成时间")
    private String finishTime;
   
    private String expireTime;
    
    private String actionUrl;
    
    private String[] actorIds;
   
    private String parentTaskId;
	
    @ApiModelProperty(value="流程变量")
    private String variable;
    
    public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
    
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getTaskState() {
		return taskState;
	}

	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPerformType() {
		return performType;
	}

	public void setPerformType(Integer performType) {
		this.performType = performType;
	}

	public String[] getActorIds() {
		return actorIds;
	}

	public void setActorIds(String[] actorIds) {
		this.actorIds = actorIds;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getVariableMap() {
        Map<String, Object> map = JsonHelper.fromJson(this.variable, Map.class);
        if(map == null) return Collections.emptyMap();
        return map;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
