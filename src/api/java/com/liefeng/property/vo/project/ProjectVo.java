package com.liefeng.property.vo.project;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目业务值对象
 * @author Huangama
 * @date 2015-12-22
 */
@ApiModel(parent=BaseValue.class)
public class ProjectVo extends BaseValue {

	private static final long serialVersionUID = 1841096284447176151L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;
	
	/**
	 * 项目编码
	 */
	@ApiModelProperty(value="项目编码")
	private String code;
	
	/**
	 * 项目简称
	 */
	@ApiModelProperty(value="项目简称")
	private String shortName;
	
	/**
	 * 项目全称
	 */
	@ApiModelProperty(value="项目全称")
	private String fullName;
	
	/**
	 * 省份
	 */
	@ApiModelProperty(value="省份")
	private String province;
	
	/**
	 * 市
	 */
	@ApiModelProperty(value="市")
	private String city;
	
	/**
	 * 区县
	 */
	@ApiModelProperty(value="区")
	private String zone;
	
	/**
	 * 项目地址
	 */
	@ApiModelProperty(value="项目地址")
	private String address;
	
	/**
	 * 项目电话
	 */
	@ApiModelProperty(value="项目电话")
	private String tel;
	
	/**
	 * 项目简介
	 */
	@ApiModelProperty(value="项目简介")
	private String introduce;
	
	/**
	 * 总户数
	 */
	@ApiModelProperty(value="总户数")
	private Integer totalHouseholds;
	
	/**
	 * 开盘日期
	 */
	@ApiModelProperty(value="开盘日期")
	private Date openingDate;
	
	/**
	 * 项目坐标（longitude,latitude）
	 */
	@ApiModelProperty(value="项目坐标")
	private String coordinate;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remark;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
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
