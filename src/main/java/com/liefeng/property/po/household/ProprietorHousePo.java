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
	 * 购房合同书URL
	 */
	@Column(name = "contract_pic")
	private String contractPic;

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
	 * 身份证复印件
	 */
	@Column(name = "id_card_pic")
	private String idCardPic;

	/**
	 * 办理入住时间
	 */
	@Column(name = "checkin_date")
	private Date checkinDate;

	/**
	 * 入住通知书URL
	 */
	@Column(name = "checkin_notice_pic")
	private String checkinNoticePic;

	/**
	 * 房屋交付验收表
	 */
	@Column(name = "delivery_check_pic")
	private String deliveryCheckPic;

	/**
	 * 业主公约
	 */
	@Column(name = "convention_pic")
	private String conventionPic;

	/**
	 * 物业管理服务协议
	 */
	@Column(name = "service_agreement_pic")
	private String serviceAgreementPic;

	/**
	 * 装修许可证
	 */
	@Column(name = "decoration_license_pic")
	private String decorationLicensePic;

	/**
	 * 卫生间厨房防水试验记录表
	 */
	@Column(name = "waterproof_record_pic")
	private String waterproofRecordPic;

	/**
	 * 水电燃气底单
	 */
	@Column(name = "hydropower_gas_pic")
	private String hydropowerGasPic;

	/**
	 * 业主办理入住书面委托书
	 */
	@Column(name = "checkin_proxy_pic")
	private String checkinProxyPic;

	/**
	 * 委托人与业主关系
	 */
	@Column(name = "mandatary_relation")
	private String mandataryRelation;

	/**
	 * 被委托人身份证复印件
	 */
	@Column(name = "mandatary_id_card_pic")
	private String mandataryIdCardPic;

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

	public String getContractPic() {
		return contractPic;
	}

	public void setContractPic(String contractPic) {
		this.contractPic = contractPic;
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

	public String getIdCardPic() {
		return idCardPic;
	}

	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckinNoticePic() {
		return checkinNoticePic;
	}

	public void setCheckinNoticePic(String checkinNoticePic) {
		this.checkinNoticePic = checkinNoticePic;
	}

	public String getDeliveryCheckPic() {
		return deliveryCheckPic;
	}

	public void setDeliveryCheckPic(String deliveryCheckPic) {
		this.deliveryCheckPic = deliveryCheckPic;
	}

	public String getConventionPic() {
		return conventionPic;
	}

	public void setConventionPic(String conventionPic) {
		this.conventionPic = conventionPic;
	}

	public String getServiceAgreementPic() {
		return serviceAgreementPic;
	}

	public void setServiceAgreementPic(String serviceAgreementPic) {
		this.serviceAgreementPic = serviceAgreementPic;
	}

	public String getDecorationLicensePic() {
		return decorationLicensePic;
	}

	public void setDecorationLicensePic(String decorationLicensePic) {
		this.decorationLicensePic = decorationLicensePic;
	}

	public String getWaterproofRecordPic() {
		return waterproofRecordPic;
	}

	public void setWaterproofRecordPic(String waterproofRecordPic) {
		this.waterproofRecordPic = waterproofRecordPic;
	}

	public String getHydropowerGasPic() {
		return hydropowerGasPic;
	}

	public void setHydropowerGasPic(String hydropowerGasPic) {
		this.hydropowerGasPic = hydropowerGasPic;
	}

	public String getCheckinProxyPic() {
		return checkinProxyPic;
	}

	public void setCheckinProxyPic(String checkinProxyPic) {
		this.checkinProxyPic = checkinProxyPic;
	}

	public String getMandataryRelation() {
		return mandataryRelation;
	}

	public void setMandataryRelation(String mandataryRelation) {
		this.mandataryRelation = mandataryRelation;
	}

	public String getMandataryIdCardPic() {
		return mandataryIdCardPic;
	}

	public void setMandataryIdCardPic(String mandataryIdCardPic) {
		this.mandataryIdCardPic = mandataryIdCardPic;
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
