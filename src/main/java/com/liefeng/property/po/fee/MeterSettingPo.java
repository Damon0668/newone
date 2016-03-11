package com.liefeng.property.po.fee;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





import com.liefeng.core.entity.BaseValue;

/**
 * 
 * <pre>      
 * Title:仪表设置持久化对象
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing  
 * @version 1.0      
 * @created 2016年2月19日上午10:45:36
 * </pre>
 */
@Entity
@Table(name = "t_meter_setting")
public class MeterSettingPo extends BaseValue {

	private static final long serialVersionUID = 433913228400394385L;

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
	 * 表类型
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 回程数（模数）
	 */
	@Column(name = "mod_num")
	private Integer modNum;

	/**
	 * 是否收费。0：不收取；1：收取
	 */
	@Column(name = "chargeable")
	private String chargeable;

	/**
	 * 抄表开始时间（每月几号，默认为每月1号）
	 */
	@Column(name = "start_day")
	private Integer startDay;

	/**
	 * 抄表持续天数
	 */
	@Column(name = "lasting_day")
	private Integer lastingDay;

	/**
	 * 操作员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * oem_code
	 */
	@Column(name = "oem_code")
	private String oemCode;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getModNum() {
		return this.modNum;
	}

	public void setModNum(Integer modNum) {
		this.modNum = modNum;
	}

	public String getChargeable() {
		return this.chargeable;
	}

	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}

	public Integer getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public Integer getLastingDay() {
		return this.lastingDay;
	}

	public void setLastingDay(Integer lastingDay) {
		this.lastingDay = lastingDay;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return this.oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
