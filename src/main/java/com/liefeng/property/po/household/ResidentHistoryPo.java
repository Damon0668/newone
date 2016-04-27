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
	 * 住户房屋信息id
	 */
	@Column(name = "resident_house_id")
	private String residentHouseId;
	
	/**
	 * 业务类型 1：删除 2：迁出 3：迁入
	 */
	@Column(name = "busitype")
	private String busitype;
	
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
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 操作时间
	 */
	@Column(name = "create_time")
	private Date createTime;

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
}

