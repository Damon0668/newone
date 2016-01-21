package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主综合信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public class ProprietorSingleHouseVo extends BaseValue {

	private static final long serialVersionUID = -8954670665706939873L;

	/********************************* 业主相关字段 *********************************/
	
	/**
	 * 业主信息ID
	 */
	private String proprietorId;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 业主姓名，产权人
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
	
	/********************************* 业主房产相关字段 *********************************/
	
	/**
	 * 业主房产信息ID
	 */
	private String proprietorHouseId;
	
	
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
	 * 身份证复印件URL
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
	 * 房屋交付验收表URL
	 */
	private String deliveryCheckPic;

	/**
	 * 业主公约URL
	 */
	private String conventionPic;

	/**
	 * 物业管理服务协议URL
	 */
	private String serviceAgreementPic;

	/**
	 * 装修许可证URL
	 */
	private String decorationLicensePic;

	/**
	 * 卫生间厨房防水试验记录表URL
	 */
	private String waterproofRecordPic;

	/**
	 * 水电燃气底单URL
	 */
	private String hydropowerGasPic;

	/**
	 * 业主办理入住书面委托书URL
	 */
	private String checkinProxyPic;

	/**
	 * 委托人与业主关系
	 */
	private String mandataryRelation;

	/**
	 * 被委托人身份证复印件URL
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

	
	/********************************* 客户相关字段 *********************************/
	
	/**
	 * 客户ID
	 */
	private String customerId;
	
	/**
	 * 身份证号码
	 */
	private String idNum;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 性别。0：未知；1：男；2：女
	 */
	private String sex;
	
	/**
	 * 出生日期
	 */
	private Date birthday;
	
	/**
	 * 婚姻状况。0：未知；1：未婚；2：已婚。
	 */
	private String maritalStatus;
	
	/**
	 * 客户状态
	 */
	private String status;
	
	/********************************* 房产相关字段 *********************************/
	
	/**
	 * 房产ID
	 */
	private String houseId;
	
	/**
	 * 房产编码
	 */
	private String houseNum;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

	/**
	 * 楼层ID
	 */
	private String floorId;

	/**
	 * 户型
	 */
	private String houseType;

	/**
	 * 实际面积
	 */
	private Double actualArea;

	/**
	 * 建筑面积
	 */
	private Double grossArea;

	/**
	 * 使用面积
	 */
	private Double usableArea;

	/**
	 * 分摊面积
	 */
	private Double apportionArea;

	/**
	 * 物业管理费单价
	 */
	private Double propertyFee;

	/**
	 * 销售状态
	 * 0：未出售 1：待售 2：已售
	 */
	private String saleStatus;

	/**
	 * 产权归属
	 * 1：商品房；2：小产权；3：大产权；4：集资房；5：安居房。
	 */
	private String ownership;


	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getProprietorHouseId() {
		return proprietorHouseId;
	}

	public void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Double getActualArea() {
		return actualArea;
	}

	public void setActualArea(Double actualArea) {
		this.actualArea = actualArea;
	}

	public Double getGrossArea() {
		return grossArea;
	}

	public void setGrossArea(Double grossArea) {
		this.grossArea = grossArea;
	}

	public Double getUsableArea() {
		return usableArea;
	}

	public void setUsableArea(Double usableArea) {
		this.usableArea = usableArea;
	}

	public Double getApportionArea() {
		return apportionArea;
	}

	public void setApportionArea(Double apportionArea) {
		this.apportionArea = apportionArea;
	}

	public Double getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(Double propertyFee) {
		this.propertyFee = propertyFee;
	}

	public String getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
}
