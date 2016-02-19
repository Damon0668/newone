package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 项目相关错误码
 * @author Huangama
 * @date 2015-12-22
 */
public enum FeeErrorCode implements IErrorCode {
	
	/**
	 * 该仪表设置已存在
	 */
	METERSETTING_EXISTS("该仪表设置已存在"), 

	/**
	 * 该仪表设置不存在
	 */
	METERSETTING_NOT_EXISTS("该仪表设置不存在"),
	
	;
	private String desc;
	
	private FeeErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
