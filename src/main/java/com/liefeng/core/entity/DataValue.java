package com.liefeng.core.entity;

import com.liefeng.core.error.IErrorCode;

/**
 * 数据值对象（返回单个对象）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataValue<T> extends BaseValue {

	private static final long serialVersionUID = -4183409003088415431L;

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回描述
	 */
	private String desc;
	
	/**
	 * 返回的数据对象
	 */
	private T data;

	public DataValue() {
		super();
	}

	public DataValue(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public DataValue(String code, String desc, T data) {
		super();
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public DataValue(Enum<?> en, T data) {
		super();
		this.code = en.name();
		this.desc = en.toString();
		this.data = data;
	}

	/**
	 * 成功返回的静态方法
	 * @param data 数据
	 * @return 数据值对象
	 */
	public static DataValue<Object> success(Object data) {
		DataValue<Object> returnData = new DataValue<Object>();
		returnData.setCode(IErrorCode.SUCCESS);
		returnData.setDesc(IErrorCode.SUCCESS_DESC);
		returnData.setData(data);
		
		return returnData;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
