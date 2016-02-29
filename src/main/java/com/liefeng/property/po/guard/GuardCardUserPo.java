package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡用户持久化对象
 * @author Huangama
 * @date 2016-2-29
 */
@Entity
@Table(name = "t_guard_card_user")
public class GuardCardUserPo extends BaseValue {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	/**
	 * 磁卡ID
	 */
	@Column(name = "card_id")
	private String cardId;
	
	/**
	 * 用户类型。0：物业员工；1：业主；2：住户；3：访客；4：外来服务人员。
	 */
	@Column(name = "user_type")
	private String userType;
	
	/**
	 * 用户ID。如果是业主，则对应t_proprietor表的id；住户，则对应t_resident表的id；访客，则对应t_visitor表的id；物业员工，则对应t_property_staff表的id。
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 操作员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 操作时间
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
