package com.liefeng.property.api.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 事件办理人评价参数封装类
 * @author xhw
 * @date 2016年3月25日 下午2:08:01
 */
@ApiModel
public class EventAccepterEvalRo {
	

	/**
	 * 事件ID
	 */
	@ApiModelProperty(value="报事id", required=true)
	@NotNull
	private String eventId;

	/**
	 * 办理人评价
	 */
	@ApiModelProperty(value="办理人评价【单人评价以‘accpterId-likes’方式，多人评价以‘accpterId-likes,accpterId2-likes2’方式】", required=true)
	@NotNull
	private String accepterLikes;
	
	/**
	 * 及时处理。0：不及时；1：及时
	 */
	@ApiModelProperty(value="及时处理【0：不及时；1：及时】", required=true)
	@NotNull
	private String timeliness;
	
	/**
	 * 处理结果。0：差；1：一般；2：好
	 */
	@ApiModelProperty(value="处理结果【0：差；1：一般；2：好】", required=true)
	@NotNull
	private String level;
	
	/**
	 * 满意度。0：差；1：一般；2：好
	 */
	@ApiModelProperty(value="满意度【0：差；1：一般；2：好】", required=true)
	@NotNull
	private String attitude;
	
	/**
	 * 评价内容
	 */
	@ApiModelProperty(value="评价内容", required=true)
	@NotNull
	private String result;
	
	/**
	 * 流程实例id
	 */
	@ApiModelProperty(value="流程实例id", required=true)
	@NotNull
	private String wfOrderId;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccepterLikes() {
		return accepterLikes;
	}

	public void setAccepterLikes(String accepterLikes) {
		this.accepterLikes = accepterLikes;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getWfOrderId() {
		return wfOrderId;
	}

	public void setWfOrderId(String wfOrderId) {
		this.wfOrderId = wfOrderId;
	}
}
