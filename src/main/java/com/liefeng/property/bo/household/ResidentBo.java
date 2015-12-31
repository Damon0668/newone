package com.liefeng.property.bo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * 住户业务相关查询参数封装类
 * 
 * @author ZhenTingJun
 * @date 2015-12-31
 */
public class ResidentBo extends BaseValue {

	private static final long serialVersionUID = -2821068233260071076L;
	
	/**
	 * 住户ID
	 */
	private String residentId;
	
	/**
	 * 业主ID
	 */
	private String proprietorId;
	
	/**
	 * 房产ID
	 */
	private String houseId;

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

}
