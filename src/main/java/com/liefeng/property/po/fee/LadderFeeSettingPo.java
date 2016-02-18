package com.liefeng.property.po.fee;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

@Entity
@Table(name = "t_ladder_fee_setting")
public class LadderFeeSettingPo extends BaseValue {

	private String id;
	private String projectId;
	private String feeType;
	private String useType;
	private Integer ladder1;
	private Double ladder1Price;
	private Integer ladder2;
	private Double ladder2Price;
	private Integer ladder3;
	private Double ladder3Price;
	private String staffId;
	private Timestamp createTime;
	private String oemCode;

	@Column(name = "id", nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "project_id", nullable = false, length = 50)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "fee_type", length = 2)
	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	@Column(name = "use_type", length = 2)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "ladder1")
	public Integer getLadder1() {
		return this.ladder1;
	}

	public void setLadder1(Integer ladder1) {
		this.ladder1 = ladder1;
	}

	@Column(name = "ladder1_price", precision = 22, scale = 0)
	public Double getLadder1Price() {
		return this.ladder1Price;
	}

	public void setLadder1Price(Double ladder1Price) {
		this.ladder1Price = ladder1Price;
	}

	@Column(name = "ladder2")
	public Integer getLadder2() {
		return this.ladder2;
	}

	public void setLadder2(Integer ladder2) {
		this.ladder2 = ladder2;
	}

	@Column(name = "ladder2_price", precision = 22, scale = 0)
	public Double getLadder2Price() {
		return this.ladder2Price;
	}

	public void setLadder2Price(Double ladder2Price) {
		this.ladder2Price = ladder2Price;
	}

	@Column(name = "ladder3")
	public Integer getLadder3() {
		return this.ladder3;
	}

	public void setLadder3(Integer ladder3) {
		this.ladder3 = ladder3;
	}

	@Column(name = "ladder3_price", precision = 22, scale = 0)
	public Double getLadder3Price() {
		return this.ladder3Price;
	}

	public void setLadder3Price(Double ladder3Price) {
		this.ladder3Price = ladder3Price;
	}

	@Column(name = "staff_id", length = 50)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "oem_code", nullable = false, length = 20)
	public String getOemCode() {
		return this.oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
