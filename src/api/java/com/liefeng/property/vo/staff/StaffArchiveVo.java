package com.liefeng.property.vo.staff;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 员工档案值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@ApiModel
public class StaffArchiveVo extends BaseValue {

	private static final long serialVersionUID = -3212942678576706332L;
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="档案ID")
	private String id;
	
	/**
	 * 客户全局唯一标识
	 */
	@ApiModelProperty(value="客户全局唯一标识")
	private String custGlobalId;
	
	/**
	 * 员工ID
	 */
	@ApiModelProperty(value="员工ID")
	private String staffId;
	
	/**
	 * 员工姓名
	 */
	@ApiModelProperty(value="员工姓名")
	private String name;
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value="手机号码")
	private String phone;
	
	/**
	 * 毕业学校
	 */
	@ApiModelProperty(value="毕业学校")
	private String school;
	
	/**
	 * 毕业时间
	 */
	@ApiModelProperty(value="毕业时间")
	private Date  graduateTime;
	
	/**
	 * 专业
	 */
	@ApiModelProperty(value="专业")
	private String major;
	
	/**
	 * 语言能力：多选用“,”分隔
	 */
	@ApiModelProperty(value="多选用“,”分隔")
	private String language;
	
	/**
	 * 健康状况
	 */
	@ApiModelProperty(value="健康状况")
	private String health;
	
	/**
	 * 现住地址
	 */
	@ApiModelProperty(value="现住地址")
	private String address;
	
	/**
	 * 电子邮件
	 */
	@ApiModelProperty(value="电子邮件")
	private String email;
	
	/**
	 * 紧急联系人
	 */
	@ApiModelProperty(value="紧急联系人")
	private String emergencyContact;
	
	/**
	 * 紧急联系电话
	 */
	@ApiModelProperty(value="紧急联系电话")
	private String emergencyPhone;
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Date getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
