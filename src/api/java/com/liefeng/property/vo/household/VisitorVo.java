package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 访客信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
public class VisitorVo extends BaseValue {

	private static final long serialVersionUID = 5520332347302068105L;
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 访客姓名
	 */
	private String name;
	
	/**
	 * 身份证号
	 */
	private String idNum;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 被访问者
	 */
	private String interviewee;
	
	/**
	 * 房间号
	 */
	private String houseNum;
	
	/**
	 * 车牌号
	 */
	private String plateNum;
	
	/**
	 * 申请人ID
	 * APP端申请访问时该字段才有值，对应用户表的ID。
	 */
	private String applicantId;

	/**
	 * 进入时间
	 */
	private Date inTime;
	
	/**
	 * 出去时间
	 */
	private Date outTime;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 类型
	 * 0：物业录入；1：业主/住户用APP申请录入。
	 */
	private String type;
	
	/**
	 * 录入员工ID
	 */
	private String staffId;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 项目名称
	 */
	private String fullName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
