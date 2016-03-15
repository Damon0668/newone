package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 用户手机端消息设置持久化 
 * @author xhw
 * @date 2016年3月14日 上午11:33:38
 */
@Entity
@Table(name="t_app_msg_setting")
public class AppMsgSettingPo extends BaseValue{

	private static final long serialVersionUID = -349847517905891778L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * 声音提醒。0：关；1：开
	 */
	@Column(name = "sound")
	private String sound;

	/**
	 * 弹屏通知。0：关；1：开
	 */
	@Column(name = "pop_flag")
	private String popFlag;

	/**
	 * 悬浮框通知。0：关；1：开
	 */
	@Column(name = "float_flag")
	private String floatFlag;

	/**
	 * 悬浮框通知。0：关；1：开
	 */
	@Column(name = "lock_flag")
	private String lockFlag;
	
	/**
	 * 创建时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * oem编码
	 */
	@Column(name = "oem_code", updatable = false)
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
