package com.liefeng.property.vo.household;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主入住材料信息值对象
 * 
 * @author ZhenTingJun
 * @date 2016年2月25日
 */
public class CheckinMaterialVo extends BaseValue {

	private static final long serialVersionUID = 534700333120260542L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 业主房产ID
	 */
	private String proprietorHouseId;

	/**
	 * 业主ID
	 */
	private String proprietorId;

	/**
	 * 房号
	 */
	private String houseNum;

	/**
	 * 资料类型。参考字典：CHECKIN_MATERIAL
	 */
	private String type;

	/**
	 * 资料URL
	 */
	private String url;

	/**
	 * 资料名称
	 */
	private String name;

	/**
	 * 资料大小
	 */
	private Double size;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 录入员工ID
	 */
	private String staffId;
	
	/**
	 * 录取员工名字
	 */
	private String staffName;

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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
