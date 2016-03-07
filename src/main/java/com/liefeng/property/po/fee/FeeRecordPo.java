package com.liefeng.property.po.fee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 缴费记录持久化对象 
 * 当业主缴纳费用时，根据费用项表的记录，往该表里面插入数据。
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_fee_record")
public class FeeRecordPo extends BaseValue {

	private static final long serialVersionUID = -5082329446132326553L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 费用项ID
	 */
	@Column(name = "fee_item_id")
	private String feeItemId;

	/**
	 * 应收金额
	 */
	@Column(name = "receivable_amount")
	private Double receivableAmount;
	
	/**
	 * 实收金额
	 */
	@Column(name = "paid_amount")
	private Double paidAmount;
	
	/**
	 * 折扣金额
	 */
	@Column(name = "discount_amount")
	private Double discountAmount;
	
	/**
	 * 滞纳金
	 */
	@Column(name = "late_fee")
	private Double lateFee;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeeItemId() {
		return feeItemId;
	}

	public void setFeeItemId(String feeItemId) {
		this.feeItemId = feeItemId;
	}

	public Double getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(Double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getLateFee() {
		return lateFee;
	}

	public void setLateFee(Double lateFee) {
		this.lateFee = lateFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
