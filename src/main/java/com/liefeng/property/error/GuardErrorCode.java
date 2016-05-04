package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;


/**
 * 门禁错误码
 * @author 蔡少东
 * @date 2016年4月12日
 */
public enum GuardErrorCode implements IErrorCode {
	
	DEVICE_POSITION_HAS_EXIST("设备位置\"{0}\"已存在"),
	
	POSITION_HAS_DEVICE("设备位置存在设备"),
	;
	private String desc;
	
	private GuardErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
