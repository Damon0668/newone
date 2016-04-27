package com.liefeng.property.vo.household;

import java.util.Date;

import javax.persistence.Column;
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
	private String id;
	
	/**
	 * 住户房屋信息id
	 */
	private String residentHouseId;
	
	/**
	 * 业务类型 1：删除 2：迁出 3：迁入
	 */
	private String busitype;
	
	/**
	 * 住户姓名
	 */
	private String name;
	
	/**
	 * 住户电话
	 */
	private String mobile;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 房号
	 */
	private String houseNum;
	
	/**
	 * 用户关系
	 */
	private String relation;
	
	/**
	 * 操作人名称
	 */
	private String staffName;
	
	/**
	 * 状态。0：未审提交；1：正常；2：已注销
	 */
	private String status;
	
	/**
	 * 住户信息主键
	 */
	private String residentId;
	
	public String getId() {
		return id;
	}

	public String getResidentHouseId() {
		return residentHouseId;
	}

	public String getBusitype() {
		return busitype;
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

	public void setResidentHouseId(String residentHouseId) {
		this.residentHouseId = residentHouseId;
	}

	public void setBusitype(String busitype) {
		this.busitype = busitype;
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

	public String getProjectName() {
		return projectName;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public String getRelation() {
		return relation;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}
}
