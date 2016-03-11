package com.liefeng.property.vo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户车辆信息值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
public class ResidentCarVo extends BaseValue {

	private static final long serialVersionUID = 3134467002315036902L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 房产ID
	 */
	private String houseId;

	/**
	 * 车牌号
	 */
	private String plateNum;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
