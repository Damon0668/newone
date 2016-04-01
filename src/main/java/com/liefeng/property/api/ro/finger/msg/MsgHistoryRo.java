package com.liefeng.property.api.ro.finger.msg;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MsgHistoryRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7403733157097840302L;
	
	@ApiModelProperty(value="手机用户ID",required=true)
	@NotNull
	private String userId;
	
	@ApiModelProperty(value="消息类型[user:个人;sys:系统]",required=true)
	@NotNull
	private String msgType;

	@ApiModelProperty(value="当前页【最小值为1】", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页面大小【最小值为0】", required=true)
	@NotNull
	private Integer size;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
