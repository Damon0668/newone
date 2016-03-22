package com.liefeng.property.api.ro;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户手机端消息设置参数封装类
 * @author xhw
 * @date 2016年3月21日 下午8:44:07
 */
@ApiModel
public class AppMsgSettingRo {
	@ApiModelProperty(value="手机端用户id", required=true)
	@NotNull
	private String userId;
	
	@ApiModelProperty(value="声音提醒【0：关；1：开】", required=true)
	@NotNull
	private String sound;
	
	@ApiModelProperty(value="弹屏通知【0：关；1：开】", required=true)
	@NotNull
	private String popFlag;

	@ApiModelProperty(value="悬浮框通知【0：关；1：开】", required=true)
	@NotNull
	private String floatFlag;

	@ApiModelProperty(value="锁屏弹出通知内容【0：关；1：开】", required=true)
	@NotNull
	private String lockFlag;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getPopFlag() {
		return popFlag;
	}

	public void setPopFlag(String popFlag) {
		this.popFlag = popFlag;
	}

	public String getFloatFlag() {
		return floatFlag;
	}

	public void setFloatFlag(String floatFlag) {
		this.floatFlag = floatFlag;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}
	
}
