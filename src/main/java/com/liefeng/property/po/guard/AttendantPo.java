package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 服务人员持久化对象
 * @author xhw
 * @date 2016年4月18日 下午5:39:15
 */
@Entity
@Table(name = "t_attendant")
public class AttendantPo extends BaseValue {

	private static final long serialVersionUID = -2316103982471641155L;

	@Id
	private String id;
	
	/**
	 * 所属项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * 服务人员姓名
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 身份证号
	 */
	@Column(name = "id_num")
	private String idNum;
	
	/**
	 * 性别
	 */
	@Column(name = "sex")
	private String sex;
	
	/**
	 * 手机号码
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * 服务类型
	 */
	@Column(name = "service_type")
	private String serviceType;
	
	/**
	 * 服务区域
	 */
	@Column(name = "service_area")
	private String serviceArea;
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	
	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;
	
	/**
	 * 车牌号
	 */
	@Column(name = "plate_num")
	private String plateNum;
	
	/**
	 * 车辆类型。取SUITABLE_CAR字典
	 */
	@Column(name = "vehicle_type")
	private String vehicleType;

	/**
	 * 状态。1：正常；2：已注销
	 */
	@Column(name = "status")
	private String status;
	
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

}
