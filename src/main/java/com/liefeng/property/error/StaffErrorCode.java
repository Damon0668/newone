package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 员工相关错误码
 * @author Huangama
 * @date 2016-2-23
 */
public enum StaffErrorCode implements IErrorCode {
	
	/**
	 * 部门不存在
	 */
	DEPARTMENT_NOT_EXIST("部门不存在"),
	
	/**
	 * 部门已存在
	 */
	DEPARTMENT_ALREADY_EXIST("部门\"{0}\"已存在"),
	
	/**
	 * 部门名字为空
	 */
	DEPARTMENT_NAME_NULL("部门名字为空"),
	
	;
	private String desc;
	
	private StaffErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
