package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 访客信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_visitor")
public class VisitorPo extends BaseValue {

	private static final long serialVersionUID = -7368011987491388243L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 访客姓名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 身份证号
	 */
	@Column(name = "id_num")
	private String idNum;
	
	/**
	 * 手机号码
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 被访问者
	 */
	@Column(name = "interviewee")
	private String interviewee;
	
	/**
	 * 房间号
	 */
	@Column(name = "house_num")
	private String houseNum;
	
	/**
	 * 车牌号
	 */
	@Column(name = "plate_num")
	private String plateNum;
	
	/**
	 * 申请人ID
	 * APP端申请访问时该字段才有值，对应用户表的ID。
	 */
	@Column(name = "applicant_id")
	private String applicantId;

	/**
	 * 进入时间
	 */
	@Column(name = "in_time")
	private Date inTime;
	
	/**
	 * 出去时间
	 */
	@Column(name = "out_time")
	private Date outTime;
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	
	/**
	 * 类型
	 * 0：物业录入；1：业主/住户用APP申请录入。
	 */
	@Column(name = "type")
	private String type;
	
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

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
