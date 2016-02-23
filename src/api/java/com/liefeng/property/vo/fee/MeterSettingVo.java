package com.liefeng.property.vo.fee;


import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 
 * <pre>      
 * Title:仪表设置值对象
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing      
 * @version 1.0      
 * @created 2016年2月19日上午10:45:22
 * </pre>
 */
public class MeterSettingVo extends BaseValue {

	private static final long serialVersionUID = 8763764026112375612L;
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 表类型
	 */
	private String type;

	/**
	 * 回程数（模数）
	 */
	private Integer modNum;

	/**
	 * 是否收费。0：不收取；1：收取
	 */
	private String chargeable;

	/**
	 * 抄表开始时间（每月几号，默认为每月1号）
	 */
	private Integer startDay;

	/**
	 * 抄表持续天数
	 */
	private Integer lastingDay;

	/**
	 * 操作员工ID
	 */
	private String staffId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * oem_code
	 */
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
