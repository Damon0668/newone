package com.liefeng.property.po.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 项目(小区)持久化对象
 * @author Huangama
 * @date 2015-12-22
 */
@Entity
@Table(name = "t_project")
public class ProjectPo extends BaseValue {
	
	private static final long serialVersionUID = 6079303170842555695L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 项目编码
	 */
	@Column(name = "code")
	private String code;
	
	/**
	 * 项目简称
	 */
	@Column(name = "short_name")
	private String shortName;
	
	/**
	 * 项目全称
	 */
	@Column(name = "full_name")
	private String fullName;
	
	/**
	 * 省份
	 */
	@Column(name = "province")
	private String province;
	
	/**
	 * 市
	 */
	@Column(name = "city")
	private String city;
	
	/**
	 * 区县
	 */
	@Column(name = "zone")
	private String zone;
	
	/**
	 * 项目地址
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * 项目电话
	 */
	@Column(name = "tel")
	private String tel;
	
	/**
	 * 项目简介
	 */
	@Column(name = "introduce")
	private String introduce;
	
	/**
	 * 总户数
	 */
	@Column(name = "total_households")
	private Integer totalHouseholds;
	
	/**
	 * 开盘日期
	 */
	@Column(name = "opening_date")
	private Date openingDate;
	
	/**
	 * 项目坐标（longitude,latitude）
	 */
	@Column(name = "coordinate")
	private String coordinate;
	
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getTotalHouseholds() {
		return totalHouseholds;
	}

	public void setTotalHouseholds(Integer totalHouseholds) {
		this.totalHouseholds = totalHouseholds;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	
}
