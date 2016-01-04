package com.liefeng.core.entity;

/**
 * 接口返回值对象
 * @author Huangama
 * @date 2016-1-4
 */
public class ReturnValue extends BaseValue {

	private static final long serialVersionUID = -5655757386004172231L;

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回描述
	 */
	private String desc;
	
	public ReturnValue(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
