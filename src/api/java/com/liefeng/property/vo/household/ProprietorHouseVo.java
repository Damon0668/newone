package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主房产信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
public class ProprietorHouseVo extends BaseValue {

	private static final long serialVersionUID = 1902475636156866068L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 所属项目ID
	 */
	private String projectId;

	/**
	 * 业主ID
	 */
	private String proprietorId;

	/**
	 * 房产编码
	 */
	private String houseNum;

	/**
	 * 合同编号
	 */
	private String contractCode;

	/**
	 * 房屋使用性质
	 */
	private String useType;

	/**
	 * 付款方式
	 */
	private String payType;

	/**
	 * 办理方式：1、业主办理，2、委托办理
	 */
	private String checkinMode;

	/**
	 * 办理入住时间
	 */
	private Date checkinDate;

	/**
	 * 委托人与业主关系
	 */
	private String mandataryRelation;

	/**
	 * 委托人姓名
	 */
	private String mandataryName;

	/**
	 * 委托人性别
	 */
	private String mandatarySex;

	/**
	 * 委托人电话
	 */
	private String mandataryPhone;

	/**
	 * 委托人身份证号
	 */
	private String mandataryIdNum;

	/**
	 * 委托人工作单位
	 */
	private String mandataryWorkUnit;

	/**
	 * 委托人户口所在地
	 */
	private String mandataryAddress;

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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCheckinMode() {
		return checkinMode;
	}

	public void setCheckinMode(String checkinMode) {
		this.checkinMode = checkinMode;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getMandataryRelation() {
		return mandataryRelation;
	}

	public void setMandataryRelation(String mandataryRelation) {
		this.mandataryRelation = mandataryRelation;
	}

	public String getMandataryName() {
		return mandataryName;
	}

	public void setMandataryName(String mandataryName) {
		this.mandataryName = mandataryName;
	}

	public String getMandatarySex() {
		return mandatarySex;
	}

	public void setMandatarySex(String mandatarySex) {
		this.mandatarySex = mandatarySex;
	}

	public String getMandataryPhone() {
		return mandataryPhone;
	}

	public void setMandataryPhone(String mandataryPhone) {
		this.mandataryPhone = mandataryPhone;
	}

	public String getMandataryIdNum() {
		return mandataryIdNum;
	}

	public void setMandataryIdNum(String mandataryIdNum) {
		this.mandataryIdNum = mandataryIdNum;
	}

	public String getMandataryWorkUnit() {
		return mandataryWorkUnit;
	}

	public void setMandataryWorkUnit(String mandataryWorkUnit) {
		this.mandataryWorkUnit = mandataryWorkUnit;
	}

	public String getMandataryAddress() {
		return mandataryAddress;
	}

	public void setMandataryAddress(String mandataryAddress) {
		this.mandataryAddress = mandataryAddress;
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
