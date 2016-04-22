package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 费用相关错误码
 * @author wuzhiing
 */
public enum ApprovalFlowErrorCode implements IErrorCode {
	
	
	/**
	 * 删除失败
	 */
	DELETE_FAILED("删除失败"),
	
	/**
	 * 工作已被其他人处理，无法删除！
	 */
	CANNOT_DELETE("工作已被其他人处理，无法删除！"), 
	
	/**
	 * 当前任务不存在
	 */
	CURR_TASK_NULL("当前任务不存在"), 
	
	/**
	 * 历史任务不存在，收回失败
	 */
	HISTASK_NULL("历史任务不存在，收回失败"),
	;
	private String desc;
	
	private ApprovalFlowErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
