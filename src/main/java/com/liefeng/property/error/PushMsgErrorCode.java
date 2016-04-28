package com.liefeng.property.error;

import com.liefeng.core.error.IErrorCode;

/**
 * 个推的错误码
 * @author xhw
 * @date 2016年4月28日 下午2:26:12
 */
public enum PushMsgErrorCode implements IErrorCode{
	
	
	/**
	 * 个推模板为空
	 */
	TEMPLATE_NOT_FIND("个推模板为空"),
	
	/**
	 * 找不到个推需要的clientId
	 */
	CLIENTID_NOT_FIND("找不到个推需要的clientId"),
	;
	
	private String desc;
	
	private PushMsgErrorCode(String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return this.desc;
	}
}
