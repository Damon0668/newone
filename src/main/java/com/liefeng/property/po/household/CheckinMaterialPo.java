package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主入住材料信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年2月25日
 */
@Entity
@Table(name = "t_checkin_material")
public class CheckinMaterialPo extends BaseValue {

	private static final long serialVersionUID = 189654284239091025L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 业主房产ID
	 */
	@Column(name = "proprietor_house_id")
	private String proprietorHouseId;

	/**
	 * 业主ID
	 */
	@Column(name = "proprietor_id")
	private String proprietorId;

	/**
	 * 房号
	 */
	@Column(name = "house_num")
	private String houseNum;

	/**
	 * 资料类型。参考字典：CHECKIN_MATERIAL
	 */
	@Column(name = "type")
	private String type;

	/**
	 * 资料URL
	 */
	@Column(name = "url")
	private String url;

	/**
	 * 资料名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 资料大小
	 */
	@Column(name = "size")
	private Double size;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProprietorHouseId() {
		return proprietorHouseId;
	}

	public void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
