package com.liefeng.property.po.household;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户车辆信息持久化对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Entity
@Table(name = "t_resident_car")
public class ResidentCarPo extends BaseValue {

	private static final long serialVersionUID = 7234401345024001756L;
	
	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 房产ID
	 */
	@Column(name = "house_id")
	private String houseId;

	/**
	 * 车牌号
	 */
	@Column(name = "plate_num")
	private String plateNum;

	/**
	 * 品牌
	 */
	@Column(name = "brand")
	private String brand;

	/**
	 * 颜色
	 */
	@Column(name = "color")
	private String color;

	/**
	 * 备注
	 */
	@Column(name = "remark")
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
