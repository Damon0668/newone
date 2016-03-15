package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 工作台相关错误码
 * @author wuzhijing
 * @date 2015-12-22
 */
public enum WorkbenchErrorCode implements IErrorCode {
	
	CANNOT_UPDATE_EVENTREPORT_STATUS_ALREADYWORKERS("该报事信息"), 
	
	PARAM_IS_NULL("参数为空"), 
	
	ALREADY_SIGNFOR("操作失败,该任务已经被签收"), 
	
	TASK_NOT_EXIST("当前任务不存在"), 
	
	ALREADY_SENDBACK("操作失败,该任务已经被退回");
	
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
