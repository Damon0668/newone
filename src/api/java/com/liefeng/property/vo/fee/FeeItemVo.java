package com.liefeng.property.vo.fee;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 费用项值对象 
 * 如果是水、电、燃气，则在抄表的时候，插入t_meter_record表的同时往该表插入一条费用项。
 * 如果是物业管理费、维修基金、车位管理费、排污费、垃圾处理费这些不需要抄表的费用，则由系统根据物业配置的收费周期，自动在期末往该表里面插数据。
 * 页面展示的所有收费项都统一从该表中读取。
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
public class FeeItemVo extends BaseValue {

	private static final long serialVersionUID = 8399866792836988636L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 所属物业公司ID
	 */
	private String propertyId;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 楼栋ID
	 */
	private String buildingId;

	/**
	 * 房号
	 */
	private String houseNum;

	/**
	 * 抄表记录ID 
	 * 如果没有抄表记录（物业费、车位费等）则为空
	 */
	private String meterRecordId;

	/**
	 * 费用类型
	 * 1：水费；2：电费；3：燃气费；4：物业管理费；5：维修基金；6：车位管理费；7：排污费；8：垃圾费；21：公摊水费；22：公摊电费；23：公摊燃气费。
	 */
	private String feeType;

	/**
	 * 本期的使用量
	 */
	private Double usageAmount;

	/**
	 * 业主名称
	 */
	private String proprietorName;

	/**
	 * 费用所属开始日期
	 */
	private Date startDate;

	/**
	 * 费用所属结束日期
	 */
	private Date endDate;

	/**
	 * 折扣 
	 * 初始值从字典表里读取，可以修改。
	 */
	private Double discount;

	/**
	 * 滞纳金费率 
	 * 初始值从字典表里读取，可以修改。
	 */
	private Double lateFeeRate;

	/**
	 * 总价
	 */
	private Double totalFee; 
	
	/**
	 * 计算过后
	 */
	private Double receivableAmount;
	/**
	 * 单价 
	 * 从字典表里读取。
	 */
	private Double unitPrice;

	/**
	 * 缴费最后期限
	 */
	private Date deadline;

	/**
	 * 缴费状态 
	 * 0：未交费；1：已正常缴费；2：过期缴费。
	 */
	private String status;

	/**
	 * 操作员工ID
	 */
	private String staffId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 应收金额
	 */
	private Double recordReceivableAmount;
	
	/**
	 * 实收金额
	 */
	private Double paidAmount;
	
	/**
	 * 创建时间
	 */
	private Date recordCreateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getMeterRecordId() {
		return meterRecordId;
	}

	public void setMeterRecordId(String meterRecordId) {
		this.meterRecordId = meterRecordId;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Double getUsageAmount() {
		return usageAmount;
	}

	public void setUsageAmount(Double usageAmount) {
		this.usageAmount = usageAmount;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getLateFeeRate() {
		return lateFeeRate;
	}

	public void setLateFeeRate(Double lateFeeRate) {
		this.lateFeeRate = lateFeeRate;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(Double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public Double getRecordReceivableAmount() {
		return recordReceivableAmount;
	}

	public void setRecordReceivableAmount(Double recordReceivableAmount) {
		this.recordReceivableAmount = recordReceivableAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setRecordCreateTime(Date recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}
