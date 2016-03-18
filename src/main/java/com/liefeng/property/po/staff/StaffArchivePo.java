package com.liefeng.property.po.staff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工档案持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_staff_archive")
public class StaffArchivePo extends BaseValue {

	private static final long serialVersionUID = 2458540908259203216L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 客户全局唯一标识
	 */
	@Column(name = "cust_global_id", updatable = false)
	private String custGlobalId;
	
	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 员工姓名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 手机号码
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * 毕业学校
	 */
	@Column(name = "school")
	private String school;
	
	/**
	 * 毕业时间
	 */
	@Column(name = "graduate_time")
	private Date  graduateTime;
	
	/**
	 * 专业
	 */
	@Column(name = "major")
	private String major;
	
	/**
	 * 语言能力：多选用“,”分隔
	 */
	@Column(name = "language")
	private String language;
	
	/**
	 * 健康状况
	 */
	@Column(name = "health")
	private String health;
	
	/**
	 * 现住地址
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * 电子邮件
	 */
	@Column(name = "email")
	private String email;
	
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
	 * 身份证扫描件
	 */
	@Column(name = "id_pic")
	private String idPic;
	
	/**
	 * 毕业证扫描件
	 */
	@Column(name = "diploma_pic")
	private String diplomaPic;
	
	/**
	 * 学位证扫描件
	 */
	@Column(name = "degree_pic")
	private String degreePic;
	
	/**
	 * 其他证件扫描件
	 */
	@Column(name = "other_pic")
	private String otherPic;
	
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

	public String getIdPic() {
		return idPic;
	}

	public void setIdPic(String idPic) {
		this.idPic = idPic;
	}

	public String getDiplomaPic() {
		return diplomaPic;
	}

	public void setDiplomaPic(String diplomaPic) {
		this.diplomaPic = diplomaPic;
	}

	public String getDegreePic() {
		return degreePic;
	}

	public void setDegreePic(String degreePic) {
		this.degreePic = degreePic;
	}

	public String getOtherPic() {
		return otherPic;
	}

	public void setOtherPic(String otherPic) {
		this.otherPic = otherPic;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
