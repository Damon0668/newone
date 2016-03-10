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
	
	/**
	 * 头像
	 */
	private String pic;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 身份证号码
	 */
	private String idNum;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 与业主的关系
	 */
	private String residentRelation;
	
	/**
	 * 工作单位
	 */
	private String workUnit;
	
	/**
	 * 籍贯
	 */
	private String nativePlace;

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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

}
