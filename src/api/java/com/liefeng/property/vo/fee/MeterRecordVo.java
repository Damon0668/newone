package com.liefeng.property.vo.fee;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 抄表记录值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
public class MeterRecordVo extends BaseValue {

	private static final long serialVersionUID = 1542017567267693196L;

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
	 * 楼层ID
	 */
	private String floorId;

	/**
	 * 房号
	 */
	private String houseNum;

	/**
	 * 表类型
	 * 1：水表；2：电表；3：燃气表
	 */
	private String meterType;

	/**
	 * 表属主
	 * 1：业主表；2：公摊表
	 */
	private String meterOwner;

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
	 * 上期表数
	 */
	private Double preNum;

	/**
	 * 本期表数
	 */
	private Double currNum;
	
	/**
	 * 本期用量
	 */
	private Double useAmount;

	/**
	 * 抄表日期
	 */
	private Date readDate;

	/**
	 * 抄表员工ID
	 */
	private String staffId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 是否已读
	 */
	private Integer isRead;
	
	/**
	 * 数据来源
	 * 1：WEB；2：Android；3：IOS
	 */
	private String from;

	/**
	 * OEM编码
	 */
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

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

}
