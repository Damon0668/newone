package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
public class ProprietorVo extends BaseValue {

	private static final long serialVersionUID = 2716022009717931809L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 客户全局唯一标识
	 */
	private String custGlobalId;

	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 业主姓名
	 */
	private String name;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 工作单位
	 */
	private String workUnit;

	/**
	 * 通讯地址
	 */
	private String address;

	/**
	 * 邮政编码
	 */
	private String zipCode;

	/**
	 * 紧急联系人
	 */
	private String emergencyContact;

	/**
	 * 紧急联系电话
	 */
	private String emergencyPhone;

	/**
	 * 业主照片URL
	 */
	private String picUrl;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 *  状态。1：激活；2：已注销
	 */
	private String status;

	/**
	 * 录入员工ID
	 */
	private String staffId;

	/**
	 * 登记时间
	 */
	private Date registerTime;

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

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
