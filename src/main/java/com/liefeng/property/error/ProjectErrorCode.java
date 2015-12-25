package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 项目相关错误码
 * @author Huangama
 * @date 2015-12-22
 */
public enum ProjectErrorCode implements IErrorCode {
	
	/**
	 * 项目已存在
	 */
	PROJECT_ALREADY_EXIST("项目已存在"),
	
	
	;
	private String desc;
	
	private ProjectErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
