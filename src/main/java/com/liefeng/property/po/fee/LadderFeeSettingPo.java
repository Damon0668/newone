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
 * Title:阶梯费用设置持久化对象
 * Description:
 * Company:广州列丰科技有限公司
 * Copyright: Copyright (c) 2015
 * @author  wuzhijing
 * @version 1.0      
 * @created 2016年2月19日上午10:23:12
 * </pre>
 */
@Entity
@Table(name = "t_ladder_fee_setting")
public class LadderFeeSettingPo extends BaseValue {

	private static final long serialVersionUID = -5630913108073864979L;

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
	 * 费用类型。1：水费；2：电费；3：燃气费；4：物业管理费；5：维修基金；6：车位管理费；7：排污费；8：垃圾费；21：公摊水费；22：公摊电费；23：公摊燃气费。
	 */
	@Column(name = "fee_type")
	private String feeType;

	/**
	 * 使用类型。1：家居；2：家居出租；3：商用；4：商用出租。
	 */
	@Column(name = "use_type")
	private String useType;

	/**
	 * 第一阶梯
	 */
	@Column(name = "ladder1")
	private Integer ladder1;

	/**
	 * 第一阶梯单价
	 */
	@Column(name = "ladder1_price")
	private Double ladder1Price;

	/**
	 * 第二阶梯
	 */
	@Column(name = "ladder2")
	private Integer ladder2;

	/**
	 * 第二阶梯单价
	 */
	@Column(name = "ladder2_price")
	private Double ladder2Price;

	/**
	 * 第三阶梯单价
	 */
	@Column(name = "ladder3_price")
	private Double ladder3Price;

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
	 * OEM编码
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

	public Integer getLadder1() {
		return this.ladder1;
	}

	public void setLadder1(Integer ladder1) {
		this.ladder1 = ladder1;
	}

	public Double getLadder1Price() {
		return this.ladder1Price;
	}

	public void setLadder1Price(Double ladder1Price) {
		this.ladder1Price = ladder1Price;
	}

	public Integer getLadder2() {
		return this.ladder2;
	}

	public void setLadder2(Integer ladder2) {
		this.ladder2 = ladder2;
	}

	public Double getLadder2Price() {
		return this.ladder2Price;
	}

	public void setLadder2Price(Double ladder2Price) {
		this.ladder2Price = ladder2Price;
	}

	public Double getLadder3Price() {
		return this.ladder3Price;
	}

	public void setLadder3Price(Double ladder3Price) {
		this.ladder3Price = ladder3Price;
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
