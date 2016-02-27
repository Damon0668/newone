package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 家庭相关错误码
 * 
 * @author ZhenTingJun
 * @date 2016-02-24
 */
public enum HouseholdErrorCode implements IErrorCode {

	/**
	 * 保存业主信息失败
	 */
	CREATE_PROPRIETOR_FAILED("保存业主信息失败"),

	/**
	 * 业主手机号码为空
	 */
	PROPRIETOR_PHONE_NULL("业主手机号码为空"),

	/**
	 * 业主名字为空
	 */
	PROPRIETOR_NAME_NULL("业主名字为空"),

	/**
	 * 业主信息已存在
	 */
	PROPRIETOR_ALREADY_EXIST("业主信息已存在"),
	
	/**
	 * 手机号已被其他客户绑定
	 */
	PHONE_ALREADY_BINDING("手机号已被其他客户绑定"),
	
	/**
	 * 住户手机号码为空
	 */
	RESIDENT_PHONE_NULL("住户手机号码为空"),
	
	/**
	 * 住户名字为空
	 */
	RESIDENT_NAME_NULL("业主名字为空"),

	;

	private String desc;

	private HouseholdErrorCode(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return this.desc;
	}

}
