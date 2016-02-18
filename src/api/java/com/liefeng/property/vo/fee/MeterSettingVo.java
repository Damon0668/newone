package com.liefeng.property.vo.fee;

import java.sql.Timestamp;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

import com.liefeng.core.entity.BaseValue;

public class MeterSettingVo extends BaseValue {

	private String id;
	private String projectId;
	private String type;
	private Integer modNum;
	private String chargeable;
	private Integer startDay;
	private Integer lastingDay;
	private String staffId;
	private Timestamp createTime;
	private String oemCode;

	public String getId() {
		return id;
	}
	public String getProjectId() {
		return projectId;
	}
	public String getType() {
		return type;
	}
	public Integer getModNum() {
		return modNum;
	}
	public String getChargeable() {
		return chargeable;
	}
	public Integer getStartDay() {
		return startDay;
	}
	public Integer getLastingDay() {
		return lastingDay;
	}
	public String getStaffId() {
		return staffId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public String getOemCode() {
		return oemCode;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setModNum(Integer modNum) {
		this.modNum = modNum;
	}
	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}
	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}
	public void setLastingDay(Integer lastingDay) {
		this.lastingDay = lastingDay;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	
	
}
