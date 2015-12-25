package com.liefeng.property.po.fee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 费用项持久化对象
 * 如果是水、电、燃气，则在抄表的时候，插入t_meter_record表的同时往该表插入一条费用项。
 * 如果是物业管理费、维修基金、车位管理费、排污费、垃圾处理费这些不需要抄表的费用，则由系统根据物业配置的收费周期，自动在期末往该表里面插数据。
 * 页面展示的所有收费项都统一从该表中读取。
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_fee_item")
public class FeeItemPo extends BaseValue {

	private static final long serialVersionUID = -2974222400016034298L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 所属物业公司ID
	 */
	@Column(name = "property_id")
	private String propertyId;

	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 楼栋ID
	 */
	@Column(name = "building_id")
	private String buildingId;

	/**
	 * 房号
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 抄表记录ID 
	 * 如果没有抄表记录（物业费、车位费等）则为空
	 */
	@Column(name = "meter_record_id")
	private String meterRecordId;

	/**
	 * 费用类型
	 * 1：水费；2：电费；3：燃气费；4：物业管理费；5：维修基金；6：车位管理费；7：排污费；8：垃圾费；21：公摊水费；22：公摊电费；23：公摊燃气费。
	 */
	@Column(name = "fee_type")
	private String feeType;

	/**
	 * 本期的使用量
	 */
	@Column(name = "usage_amount")
	private Double usageAmount;

	/**
	 * 业主名称
	 */
	@Column(name = "proprietor_name")
	private String proprietorName;

	/**
	 * 费用所属开始日期
	 */
	@Column(name = "start_date")
	private Date startDate;

	/**
	 * 费用所属结束日期
	 */
	@Column(name = "end_date")
	private Date endDate;

	/**
	 * 折扣 
	 * 初始值从字典表里读取，可以修改。
	 */
	@Column(name = "discount")
	private Double discount;

	/**
	 * 滞纳金费率 
	 * 初始值从字典表里读取，可以修改。
	 */
	@Column(name = "late_fee_rate")
	private Double lateFeeRate;

	/**
	 * 单价 
	 * 从字典表里读取。
	 */
	@Column(name = "unit_price")
	private Double unitPrice;

	/**
	 * 缴费最后期限
	 */
	@Column(name = "deadline")
	private Date deadline;

	/**
	 * 缴费状态 
	 * 0：未交费；1：已正常缴费；2：过期缴费。
	 */
	@Column(name = "status")
	private String status;

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
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;

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
}
