package com.liefeng.property.vo.fee;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 缴费记录值对象 
 * 当业主缴纳费用时，根据费用项表的记录，往该表里面插入数据。
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
public class FeeRecordVo extends BaseValue {

	private static final long serialVersionUID = 4647104966466196511L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 费用项ID
	 */
	private String feeItemId;

	/**
	 * 应收金额
	 */
	private Double receivableAmount;
	
	/**
	 * 实收金额
	 */
	private Double paidAmount;
	
	/**
	 * 折扣金额
	 */
	private Double discountAmount;
	
	/**
	 * 滞纳金
	 */
	private Double lateFee;
	
	/**
	 * 创建时间
	 */
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
