package com.liefeng.property.po.fee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 抄表记录持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Entity
@Table(name = "t_meter_record")
public class MeterRecordPo extends BaseValue {

	private static final long serialVersionUID = -1115828218110082487L;

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
	 * 楼层ID
	 */
	@Column(name = "floor_id")
	private String floorId;

	/**
	 * 房号
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 表类型
	 * 1：水表；2：电表；3：燃气表
	 */
	@Column(name = "meter_type")
	private String meterType;

	/**
	 * 表属主
	 * 1：业主表；2：公摊表
	 */
	@Column(name = "meter_owner")
	private String meterOwner;

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
	 * 上期表数
	 */
	@Column(name = "pre_num")
	private Double preNum;

	/**
	 * 本期表数
	 */
	@Column(name = "curr_num")
	private Double currNum;

	/**
	 * 本期用量
	 */
	@Column(name ="use_amount")
	private Double useAmount;

	/**
	 * 抄表日期
	 */
	@Column(name = "read_date")
	private Date readDate;

	/**
	 * 抄表员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 数据来源
	 * 1：WEB；2：Android；3：IOS
	 */
	@Column(name = "`from`")
	private String from;

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

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getMeterOwner() {
		return meterOwner;
	}

	public void setMeterOwner(String meterOwner) {
		this.meterOwner = meterOwner;
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

	public Double getPreNum() {
		return preNum;
	}

	public void setPreNum(Double preNum) {
		this.preNum = preNum;
	}

	public Double getCurrNum() {
		return currNum;
	}

	public void setCurrNum(Double currNum) {
		this.currNum = currNum;
	}

	public Double getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(Double useAmount) {
		this.useAmount = useAmount;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
