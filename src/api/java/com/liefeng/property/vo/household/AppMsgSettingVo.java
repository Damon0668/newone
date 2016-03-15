package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;


/**
 * 用户手机端消息设置值对象
 * @author xhw
 * @date 2016年3月14日 上午11:33:38
 */
public class AppMsgSettingVo extends BaseValue{

	private static final long serialVersionUID = -6554763660577695490L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 声音提醒。0：关；1：开
	 */
	private String sound;

	/**
	 * 弹屏通知。0：关；1：开
	 */
	private String popFlag;

	/**
	 * 悬浮框通知。0：关；1：开
	 */
	private String floatFlag;

	/**
	 * 悬浮框通知。0：关；1：开
	 */
	private String lockFlag;
	
	/**
	 * 创建时间
	 */
	private Date updateTime;

	/**
	 * oem编码
	 */
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


}
