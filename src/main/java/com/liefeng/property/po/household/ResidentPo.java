package com.liefeng.property.po.household;

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
	 * 项目ID（小区ID）
	 */
	@Column(name = "project_id")
	private String projectId;
	
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
	 * 状态。0：未审提交；1：正常；2：已注销
	 */
	@Column(name = "status")
	private String status;

	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

}
