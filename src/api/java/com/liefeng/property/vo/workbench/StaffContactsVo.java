package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工通讯录值对象
 * @author xhw
 * @2016年3月4日 下午3:28:10
 */
public class StaffContactsVo extends BaseValue{

	private static final long serialVersionUID = -2306263218216847410L;

	/**
	 * 客户全局唯一标识
	 */
	private String custGlobalId;

	/**
	 * 所属部门ID
	 */
	private String departmentId;

	/**
	 * 员工ID
	 */
	private String staffId;

	/**
	 * 员工姓名
	 */
	private String name;

	/**
	 * 员工工号
	 */
	private String number;

	/**
	 * 状态。1：激活；2：注销
	 */
	private String status;

	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 * 在职状态。1：在职；2：离职
	 */
	private String workStatus;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 接收人信息
	 */
	private String receiverMessage;

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getReceiverMessage() {
		return receiverMessage;
	}

	public void setReceiverMessage(String receiverMessage) {
		this.receiverMessage = receiverMessage;
	}
	

}
