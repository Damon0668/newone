package com.liefeng.core.error;

/**
 * 错误码接口
 * @author Huangama
 * @date 2015-11-15
 */
public interface IErrorCode {
	
	/**
	 * 成功返回码
	 */
	public static String SUCCESS = "0";
	
	/**
	 * 成功描述
	 */
	public static String SUCCESS_DESC = "success";
	
	/**
	 * 获取错误码描述
	 */
    public String getDesc();
    
    /**
	 * 获取错误码描述（等同于getDesc()）
	 */
    public String toString();
    
    /**
	 * 获取错误码编码
	 */
    public String name();
}
