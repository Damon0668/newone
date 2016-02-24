package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 家庭相关错误码
 * @author Zhentingjun
 * @date 2016-02-24
 */
public enum HouseholdErrorCode implements IErrorCode {
	
	/**
	 * 保存业主信息失败
	 */
	CREATE_PROPRIETOR_FAILED("保存业主信息失败"), 

	;
	
	private String desc;
	
	private HouseholdErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
