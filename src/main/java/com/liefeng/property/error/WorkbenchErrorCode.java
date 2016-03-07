package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 工作台相关错误码
 * @author wuzhijing
 * @date 2015-12-22
 */
public enum WorkbenchErrorCode implements IErrorCode {
	
	/**
	 * 该报事信息已派单,不能修改
	 */
	CANNOT_UPDATE_EVENTREPORT_STATUS_ALREADYWORKERS("该报事信息已派单,不能修改"), 
	
	/**
	 * 该报事信息已完成归档,不能修改
	 */
	CANNOT_UPDATE_EVENTREPORT_STATUS_UNTREATED("该报事信息已完成归档,不能修改");

	private String desc;
	
	private WorkbenchErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
