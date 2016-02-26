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

	/**
	 * 该费用设置存在
	 */
	FEESETTING_EXISTS("该费用设置已存在"),
	
	/**
	 * 该费用设置不存在
	 */
	FEESETTING_NOT_EXISTS("该费用设置不存在"),
	
	/**
	 * 该阶梯收费设置存在
	 */
	LADDERFEESETTING_EXISTS("该阶梯收费设置已存在"),
	
	/**
	 * 该阶梯收费设置不存在
	 */
	LADDERFEESETTING_NOT_EXISTS("该阶梯收费设置不存在"),
	
	/**
	 * 本月已抄表
	 */
	METERRECORD_CURRENT_MONTH_EXIST("本月已抄表"), 
	
	/**
	 * 当前日期不能抄表
	 */
	METERRECORD_CURRENT_DATE_CANNOT_CREATE("当前日期不能抄表"), 
	
	/**
	 * 仪表设置为空
	 */
	METERSETTING_ISNULL("不存在该仪表设置"), 
	
	/**
	 * 当前读数错误
	 */
	METERRECORD_CURRENT_NUMBER_ERRER("当前读数错误"),

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
