package com.liefeng.property.vo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

/**
 * 服务人员持久化对象
 * @author xhw
 * @date 2016年4月18日 下午5:39:15
 */
public class AttendantVo extends BaseValue {

	private static final long serialVersionUID = -3393931794436289979L;

	private String id;
	
	/**
	 * 所属项目ID
	 */
	private String projectId;
	
	/**
	 * 服务人员姓名
	 */
	private String name;
	
	/**
	 * 身份证号
	 */
	private String idNum;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 服务类型
	 */
	@Dict(group = "SERVICE_TYPE")
	private String serviceType;
	
	/**
	 * 服务区域
	 */
	private String serviceArea;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 录入员工ID
	 */
	private String staffId;
	
	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 车牌号
	 */
	private String plateNum;
	
	/**
	 * 车辆类型。取SUITABLE_CAR字典
	 */
	private String vehicleType;

	/**
	 * 状态。1：正常；2：已注销
	 */
	private String status;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 项目名称
	 */
	private String fullName;
	
	/**
	 * 服务类型名称
	 */
	private String servictTypeName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getServictTypeName() {
		return servictTypeName;
	}

	public void setServictTypeName(String servictTypeName) {
		this.servictTypeName = servictTypeName;
	}
}
