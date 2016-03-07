package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 项目相关错误码
 * @author Huangama
 * @date 2015-12-22
 */
public enum ParkingErrorCode implements IErrorCode {
	
	/**
	 * 车位号已经存在
	 */
	PARKING_ALREADY_EXIST("车位号{0}已经存在"), 
	
	/**
	 * 车位不存在
	 */
	PARKING_ALREADY_NOT_EXIST("车位不存在"),
	;
	private String desc;
	
	private ParkingErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}
	
}
