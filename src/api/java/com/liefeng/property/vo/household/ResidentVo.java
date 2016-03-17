package com.liefeng.property.vo.household;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.base.vo.CustomerVo;
import com.liefeng.core.entity.BaseValue;

/**
 * 住户信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-23
 */
@JsonInclude(Include.NON_NULL)
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
	 * 项目ID（小区ID）
	 */
	private String projectId;

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
	 * 住户房屋信息值对象
	 */
	private ResidentHouseVo residentHouse;

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

	public ResidentHouseVo getResidentHouse() {
		return residentHouse;
	}

	public void setResidentHouse(ResidentHouseVo residentHouse) {
		this.residentHouse = residentHouse;
	}

}
