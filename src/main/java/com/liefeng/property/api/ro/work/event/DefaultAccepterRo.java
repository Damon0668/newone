package com.liefeng.property.api.ro.work.event;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取默认办理人接口参数类
 * @author wuzhijing
 * @date 2016-03-28
 */
@ApiModel
public class DefaultAccepterRo  extends BaseValue {
	
	private static final long serialVersionUID = 3851155083340187176L;
	
	@ApiModelProperty(value="报事ID", required=true)
	@NotNull
	private String eventId;
	
	@ApiModelProperty(value="办理步骤【dispatching：派工；distributeLeaflets：拍单】", required=true)
	@NotNull
	private String taskName;

	public String getEventId() {
		return eventId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
