package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.base.vo.CustomerVo;
import com.liefeng.core.entity.BaseValue;

/**
 * 住户信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
public class ResidentVo extends BaseValue {

	private static final long serialVersionUID = 4010241590641153026L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 客户全局唯一标识
	 */
	private String custGlobalId;

	/**
	 * 房产ID
	 */
	private String houseId;

	/**
	 * 业主ID
	 */
	private String proprietorId;

	/**
	 * 是否为业主 0：否；1：是。
	 */
	private String isProprietor;

	/**
	 * 住户类型 1：常住；2：暂住
	 */
	private String residentType;

	/**
	 * 与业主关系
	 */
	private String residentRelation;

	/**
	 * 入住时间
	 */
	private Date checkinDate;

	/**
	 * 入住截止时间
	 */
	private Date checkupDate;

	/**
	 * 住户姓名
	 */
	private String name;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 工作单位
	 */
	private String workUnit;

	/**
	 * 兴趣爱好
	 */
	private String hobbies;

	/**
	 * 特长
	 */
	private String specialty;

	/**
	 * 照片
	 */
	private String pic;
	
	/**
	 * 状态。0：未审提交；1：正常；2：已注销
	 */
	private String status;
	
	/**
	 * 录入员工ID
	 */
	private String staffId;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * OEM编码
	 */
	private String oemCode;

	/**
	 * 客户信息
	 */
	private CustomerVo customer;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 房间号
	 */
	private String houseNum;
	
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

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getIsProprietor() {
		return isProprietor;
	}

	public void setIsProprietor(String isProprietor) {
		this.isProprietor = isProprietor;
	}

	public String getResidentType() {
		return residentType;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckupDate() {
		return checkupDate;
	}

	public void setCheckupDate(Date checkupDate) {
		this.checkupDate = checkupDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public CustomerVo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVo customer) {
		this.customer = customer;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

}
