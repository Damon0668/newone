package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@Entity
@Table(name = "t_resident")
public class ResidentPo extends BaseValue {

	private static final long serialVersionUID = -7535448116793740626L;

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
	 * 房产ID
	 */
	@Column(name = "house_id")
	private String houseId;

	/**
	 * 业主ID
	 */
	@Column(name = "proprietor_id")
	private String proprietorId;

	/**
	 * 是否为业主 0：否；1：是。
	 */
	@Column(name = "is_proprietor")
	private String isProprietor;

	/**
	 * 住户类型 1：常住；2：暂住
	 */
	@Column(name = "resident_type")
	private String residentType;

	/**
	 * 与业主关系
	 */
	@Column(name = "resident_relation")
	private String residentRelation;

	/**
	 * 入住时间
	 */
	@Column(name = "checkin_date")
	private Date checkinDate;

	/**
	 * 入住截止时间
	 */
	@Column(name = "checkup_date")
	private Date checkupDate;

	/**
	 * 住户姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 手机号码
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 电话
	 */
	@Column(name = "tel")
	private String tel;

	/**
	 * 工作单位
	 */
	@Column(name = "work_unit")
	private String workUnit;

	/**
	 * 兴趣爱好
	 */
	@Column(name = "hobbies")
	private String hobbies;

	/**
	 * 特长
	 */
	@Column(name = "specialty")
	private String specialty;

	/**
	 * 照片
	 */
	@Column(name = "pic")
	private String pic;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

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

}
