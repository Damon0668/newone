package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主房产信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Entity
@Table(name = "t_proprietor_house")
public class ProprietorHousePo extends BaseValue {

	private static final long serialVersionUID = 4457165094598419074L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 所属项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 业主ID
	 */
	@Column(name = "proprietor_id")
	private String proprietorId;
	
	/**
	 * 房产编码
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 合同编号
	 */
	@Column(name = "contract_code")
	private String contractCode;


	/**
	 * 房屋使用性质
	 */
	@Column(name = "use_type")
	private String useType;

	/**
	 * 付款方式
	 */
	@Column(name = "pay_type")
	private String payType;

	/**
	 * 办理方式：1、业主办理，2、委托办理
	 */
	@Column(name = "checkin_mode")
	private String checkinMode;

	/**
	 * 办理入住时间
	 */
	@Column(name = "checkin_date")
	private Date checkinDate;

	/**
	 * 委托人与业主关系
	 */
	@Column(name = "mandatary_relation")
	private String mandataryRelation;

	/**
	 * 委托人姓名
	 */
	@Column(name = "mandatary_name")
	private String mandataryName;

	/**
	 * 委托人性别
	 */
	@Column(name = "mandatary_sex")
	private String mandatarySex;

	/**
	 * 委托人电话
	 */
	@Column(name = "mandatary_phone")
	private String mandataryPhone;

	/**
	 * 委托人身份证号
	 */
	@Column(name = "mandatary_id_num")
	private String mandataryIdNum;

	/**
	 * 委托人工作单位
	 */
	@Column(name = "mandatary_work_unit")
	private String mandataryWorkUnit;

	/**
	 * 委托人户口所在地
	 */
	@Column(name = "mandatary_address")
	private String mandataryAddress;

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
