package com.liefeng.property.vo.workbench;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主通讯录值对象
 * @author xhw
 * @2016年3月4日 下午3:38:27
 */
public class ProprietorContactsVo extends BaseValue{

	private static final long serialVersionUID = -3498578623476698467L;

	/**
	 * 客户全局唯一标识
	 */
	private String custGlobalId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 楼栋ID
	 */
	private String buildingId;
	
	/**
	 * 房号：楼栋编码+楼层编码+房号后缀
	 */
	private String houseNum;
	
	/**
	 * 业主ID
	 */
	private String proprietorId;
	
	/**
	 * 业主姓名
	 */
	private String name;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 状态。0：未审提交；1：正常；2：已注销
	 */
	private String status;
	
	/**
	 * 电子邮件
	 */
	private String email;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

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

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
