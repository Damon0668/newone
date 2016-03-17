package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 手机端好友持久化对象
 *  
 * @author xhw
 * @date 2016年3月16日 下午1:53:14
 */
@Entity
@Table(name="t_app_friend")
public class AppFriendPo extends BaseValue{

	private static final long serialVersionUID = 5726292317216232577L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 用户ID。对应t_user表的ID
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * 好友ID。对应t_user表的ID
	 */
	@Column(name = "friend_id")
	private String friendId;

	/**
	 * 状态。0：等待验证；1：已为好友；2：拒绝
	 */
	@Column(name = "status")
	private String status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 更新时间
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

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
