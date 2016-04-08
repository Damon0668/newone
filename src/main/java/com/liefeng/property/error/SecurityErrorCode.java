package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 系统权限错误码
 * @author 蔡少东
 * @date 2016年3月18日
 */
public enum SecurityErrorCode implements IErrorCode{
	
	ROLE_HAS_EXIST("角色已存在"),
	
	OPENID_HAS_EXIST("OPENID已存在"),
	;

	private String desc;
	
	private SecurityErrorCode(String desc){
	   this.desc = desc;
	}
	
	public String getDesc(){
	  return desc;
	}
	
	public String toString() {
		return this.desc;
	}

}
