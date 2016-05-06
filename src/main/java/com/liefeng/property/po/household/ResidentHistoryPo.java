package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户历史记录值对象
 * 
 * @author wuzhijing
 * @date 2016-04-25
 */
@Entity
@Table(name = "t_resident_history")
public class ResidentHistoryPo extends BaseValue {
	
	private static final long serialVersionUID = -5318126437715509798L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 房号：楼栋编码+楼层编码+房号后缀
	 */
	@Column(name = "house_num")
	private String houseNum;
	
	/**
	 * 住户信息id
	 */
	@Column(name = "resident_id")
	private String residentId;
	
	/**
	 * 业务类型 1：删除 2：迁出 3：迁入
	 */
	@Column(name = "busi_type")
	private String busiType;
	
	/**
	 * 客户全局唯一标识
	 */
	@Column(name = "cust_global_id")
	private String custGlobalId;
	
	/**
	 * 住户姓名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 住户电话
	 */
	@Column(name = "mobile")
	private String mobile;
	
	/**
	 * 住户类型。1：常住；2：暂住
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
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 操作时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;
	
	public String getId() {
		return id;
	}

	public String getResidentId() {
		return residentId;
	}

	public String getBusiType() {
		return busiType;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getCustGlobalId() {
		return custGlobalId;
	}

	public void setCustGlobalId(String custGlobalId) {
		this.custGlobalId = custGlobalId;
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
}

