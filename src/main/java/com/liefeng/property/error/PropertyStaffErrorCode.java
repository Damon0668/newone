package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 
 * @author 蔡少东
 * @date 2016年2月24日
 */
public enum PropertyStaffErrorCode implements IErrorCode{
	
	/**
	 * 员工已存在
	 */
	STAFF_ALREADY_EXIST("员工已存在"),
	;
	
	private String desc;
	
	private PropertyStaffErrorCode(String desc){
		   this.desc = desc;
		}
	
	@Override
	public String getDesc() {
		return desc;
	}

}
