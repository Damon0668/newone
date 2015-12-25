package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Entity
@Table(name = "t_proprietor")
public class ProprietorPo extends BaseValue {

	private static final long serialVersionUID = -4887363148226058550L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 客户全局唯一标识
	 */
	@Column(name = "cust_global_id")
	private String custGlobalId;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 业主姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 手机号码
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 电子邮件
	 */
	@Column(name = "email")
	private String email;

	/**
	 * 工作单位
	 */
	@Column(name = "work_unit")
	private String workUnit;

	/**
	 * 通讯地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 邮政编码
	 */
	@Column(name = "zip_code")
	private String zipCode;

	/**
	 * 紧急联系人
	 */
	@Column(name = "emergency_contact")
	private String emergencyContact;

	/**
	 * 紧急联系电话
	 */
	@Column(name = "emergency_phone")
	private String emergencyPhone;

	/**
	 * 业主照片URL
	 */
	@Column(name = "pic_url")
	private String picUrl;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * 登记时间
	 */
	@Column(name = "register_time")
	private Date registerTime;

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
