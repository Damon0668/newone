package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 工作台相关错误码
 * @author wuzhijing
 * @date 2015-12-22
 */
public enum WorkbenchErrorCode implements IErrorCode {
	
	CANNOT_UPDATE_EVENTREPORT_STATUS_ALREADYWORKERS("该报事信息");
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
