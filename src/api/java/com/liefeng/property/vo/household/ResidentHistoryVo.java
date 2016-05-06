package com.liefeng.property.vo.household;

import java.util.Date;

import javax.persistence.Id;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户历史记录持久化对象
 * @author wuzhijing
 *
 */
public class ResidentHistoryVo extends BaseValue {
	
	private static final long serialVersionUID = -2364577676945513787L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目ID
	 */
	private String projectId;
	
	/**
	 * 房号：楼栋编码+楼层编码+房号后缀
	 */
	private String houseNum;
	
	/**
	 * 住户信息id
	 */
	private String residentId;
	
	/**
	 * 业务类型 1：删除 2：迁出 3：迁入
	 */
	private String busiType;
	
	/**
	 * 客户全局唯一标识
	 */
	private String custGlobalId;
	
	/**
	 * 住户姓名
	 */
	private String name;
	
	/**
	 * 住户电话
	 */
	private String mobile;
	
	/**
	 * 住户类型。1：常住；2：暂住
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
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	private String projectName;
	
	private String relation;
	
	private String status;
	
	public String getId() {
		return id;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public String getResidentId() {
		return residentId;
	}

	public String getBusiType() {
		return busiType;
	}

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getStaffId() {
		return staffId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getResidentType() {
		return residentType;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getRelation() {
		return relation;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
