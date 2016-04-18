package com.liefeng.property.vo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡用户视图对象
 * @author Huangama
 * @date 2016-2-29
 */
public class GuardCardUserVo extends BaseValue {

	private static final long serialVersionUID = -5359068752503155492L;

	private String id;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 用户类型。0：物业员工；1：业主；2：住户；3：访客；4：外来服务人员。
	 */
	private String userType;
	
	/**
	 * 用户ID。如果是业主，则对应t_proprietor表的id；住户，则对应t_resident表的id；访客，则对应t_visitor表的id；物业员工，则对应t_property_staff表的id。
	 */
	private String userId;
	
	/**
	 * 用户ß姓名
	 */
	private String userName;
	
	/**
	 * 操作员工ID
	 */
	private String staffId;
	
	/**
	 * 操作员工名称
	 */
	private String staffName;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * OEM编码
	 */
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
