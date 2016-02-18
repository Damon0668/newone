package com.liefeng.property.po.fee;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




import com.liefeng.core.entity.BaseValue;

@Entity
@Table(name = "t_meter_setting")
public class MeterSettingPo extends BaseValue {

	private static final long serialVersionUID = 433913228400394385L;

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

	@Id
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

	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "mod_num")
	public Integer getModNum() {
		return this.modNum;
	}

	public void setModNum(Integer modNum) {
		this.modNum = modNum;
	}

	@Column(name = "chargeable", length = 1)
	public String getChargeable() {
		return this.chargeable;
	}

	public void setChargeable(String chargeable) {
		this.chargeable = chargeable;
	}

	@Column(name = "start_day")
	public Integer getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	@Column(name = "lasting_day")
	public Integer getLastingDay() {
		return this.lastingDay;
	}

	public void setLastingDay(Integer lastingDay) {
		this.lastingDay = lastingDay;
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
