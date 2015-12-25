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
	 * 业主ID
	 */
	private String proprietorId;

	/**
	 * 房产ID
	 */
	private String houseId;

	/**
	 * 合同编号
	 */
	private String contractCode;

	/**
	 * 购房合同书URL
	 */
	private String contractPic;

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
	 * 身份证复印件
	 */
	private String idCardPic;

	/**
	 * 办理入住时间
	 */
	private Date checkinDate;

	/**
	 * 入住通知书URL
	 */
	private String checkinNoticePic;

	/**
	 * 房屋交付验收表
	 */
	private String deliveryCheckPic;

	/**
	 * 业主公约
	 */
	private String conventionPic;

	/**
	 * 物业管理服务协议
	 */
	private String serviceAgreementPic;

	/**
	 * 装修许可证
	 */
	private String decorationLicensePic;

	/**
	 * 卫生间厨房防水试验记录表
	 */
	private String waterproofRecordPic;

	/**
	 * 水电燃气底单
	 */
	private String hydropowerGasPic;

	/**
	 * 业主办理入住书面委托书
	 */
	private String checkinProxyPic;

	/**
	 * 委托人与业主关系
	 */
	private String mandataryRelation;

	/**
	 * 被委托人身份证复印件
	 */
	private String mandataryIdCardPic;

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

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
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
