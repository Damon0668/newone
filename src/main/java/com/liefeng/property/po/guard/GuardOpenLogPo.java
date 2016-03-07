package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 开门日志持久化对象
 * @author Huangama
 * @date 2016-2-29
 */
@Entity
@Table(name = "t_guard_open_log")
public class GuardOpenLogPo extends BaseValue {

	private static final long serialVersionUID = -2802412419005977451L;

	@Id
	private String id;
	
	/**
	 * 磁卡ID
	 */
	@Column(name = "card_id")
	private String cardId;
	
	/**
	 * 门禁设备ID
	 */
	@Column(name = "guard_device_id")
	private String guardDeviceId;
	
	/**
	 * 动作。1：开门；2：关门
	 */
	@Column(name = "action")
	private String action;
	
	/**
	 * 开门方式。1：密码；2：刷卡；3：二维码；4：无线对讲(按房号，手机开门,我的门铃开门)；5：管理员开门 ；6：异常开门
	 */
	@Column(name = "open_type")
	private String openType;
	
	/**
	 * 操作用户类型。0：物业员工；1：业主；2：住户；3：访客；4：外来服务人员。
	 */
	@Column(name = "oper_user_type")
	private String operUserType;
	
	/**
	 * 操作用户ID。如果是业主，则对应t_proprietor表的id；住户，则对应t_resident表的id；访客，则对应t_visitor表的id；物业员工，则对应t_property_staff表的id。
	 */
	@Column(name = "oper_user_id")
	private String operUserId;
	
	/**
	 * 操作终端。1：手机；2：电视；3：刷卡；4：密码。
	 */
	@Column(name = "oper_client")
	private String operClient;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getGuardDeviceId() {
		return guardDeviceId;
	}

	public void setGuardDeviceId(String guardDeviceId) {
		this.guardDeviceId = guardDeviceId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOperUserType() {
		return operUserType;
	}

	public void setOperUserType(String operUserType) {
		this.operUserType = operUserType;
	}

	public String getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	public String getOperClient() {
		return operClient;
	}

	public void setOperClient(String operClient) {
		this.operClient = operClient;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
