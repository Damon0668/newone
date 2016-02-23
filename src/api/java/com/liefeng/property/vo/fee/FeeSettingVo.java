package com.liefeng.property.vo.fee;


import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 
 * <pre>      
 * Title:
 * Description:费用设置值对象
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing       
 * @version 1.0      
 * @created 2016年2月19日上午10:42:04
 * </pre>
 */
public class FeeSettingVo extends BaseValue{

	private static final long serialVersionUID = -6906607650082289291L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 费用类型。1：水费；2：电费；3：燃气费；4：物业管理费；5：维修基金；6：车位管理费；7：排污费；8：垃圾费；21：公摊水费；22：公摊电费；23：公摊燃气费。
	 */
	private String feeType;

	/**
	 * 使用类型。1：家居；2：家居出租；3：商用；4：商用出租。
	 */
	private String useType;

	/**
	 * 是否收费。0：不收取；1：收取
	 */
	private String chargeable;

	/**
	 * 单价
	 */
	private Double price;

	/**
	 * 收费单位
	 */
	private String unit;

	/**
	 * 收费周期
	 */
	private String period;
	
	/**
	 * 收费开始时间（每月几号，默认为每月1号）
	 */
	private Integer startDay;

	/**
	 * 收费开始月份
	 */
	private Integer startMonth;

	/**
	 * 缴费期限
	 */
	private Integer paymentPeriod;

	/**
	 * 操作员工ID
	 */
	private String staffId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * OEM编码
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

	
	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	
	public String getChargeable() {
		return this.chargeable;
	}

	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}

	
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	
	public Integer getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	
	public Integer getStartMonth() {
		return this.startMonth;
	}

	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}

	
	public Integer getPaymentPeriod() {
		return this.paymentPeriod;
	}

	public void setPaymentPeriod(Integer paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
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
