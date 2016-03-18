package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 工作台相关错误码
 * @author wuzhijing
 * @date 2015-12-22
 */
public enum WorkbenchErrorCode implements IErrorCode {
	
	/**
	 * 报事的内容为空
	 */
	EVENTREPORT_CONTENT_NULL("报事的内容为空"),
	
	/**
	 * 无法更新,该报事信息已经被签收
	 */
	CANNOT_UPDATE_EVENTREPORT_STATUS_ALREADYWORKERS("无法更新,该报事信息已经被签收"), 
	
	/**
	 * 参数为空
	 */
	PARAM_IS_NULL("参数为空"), 
	
	/**
	 * 操作失败,该任务已经被签收
	 */
	ALREADY_SIGNFOR("操作失败,该任务已经被签收"), 
	
	/**
	 * 当前任务不存在
	 */
	TASK_NOT_EXIST("当前任务不存在"), 
	
	/**
	 * 操作失败,该任务已经被退回
	 */
	ALREADY_SENDBACK("操作失败,该任务已经被退回"), 
	
	/**
	 * 该报事不存在
	 */
	EVENTREPORT_NON_EXISTENT("该报事不存在"),
	
	/**
	 * 该报事已进入流程无法删除
	 */
	EVENTREPORT_CANNOT_DELETE("该报事已进入流程无法删除");
	
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
