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
	 * 旧密码错误
	 */
	OLD_PASSWORD_ERROR("旧密码错误"),
	
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
