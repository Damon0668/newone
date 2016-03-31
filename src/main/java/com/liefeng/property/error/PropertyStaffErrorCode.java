package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 员工相关错误码
 * @author 蔡少东
 * @date 2016年2月24日
 */
public enum PropertyStaffErrorCode implements IErrorCode{
	
	/**
	 * 员工ID不存在
	 */
	STAFF_ID_NOT_EXIST("员工ID不存在"),
	
	/**
	 * 员工已存在
	 */
	STAFF_ALREADY_EXIST("员工已存在"),
	
	/**
	 * 员工已存在
	 */
	STAFF_NOT_EXIST("员工不存在"),
	
	/**
	 * 密码错误
	 */
	PASSWORD_ERROR("密码错误"),
	
	/**
	 * 旧密码错误
	 */
	OLD_PASSWORD_ERROR("旧密码错误"),
	
	
	/**
	 * 手机已存在
	 */
	MOBILE_HAS_EXIST("手机已存在"),
	
	/**
	 * 手机号不匹配
	 */
	MOBILE_NOT_MATCHING("手机号不匹配"),
	
	;
	
	private String desc;
	
	private PropertyStaffErrorCode(String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return this.desc;
	}
}
