package com.liefeng.core.entity;

import com.liefeng.core.error.IErrorCode;

/**
 * 数据值对象（返回单个对象）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataValue<T> extends ReturnValue {

	private static final long serialVersionUID = -4183409003088415431L;

	/**
	 * 返回的数据对象
	 */
	private T data;

	public DataValue(String code, String desc) {
		super(code, desc);
	}

	public DataValue(String code, String desc, T data) {
		super(code, desc);
		this.data = data;
	}

	public DataValue(Enum<?> en, T data) {
		super(en.name(), en.toString());
		this.data = data;
	}

	/**
	 * 成功返回的静态方法
	 * @param data 数据
	 * @return 数据值对象
	 */
	public static DataValue<Object> success(Object data) {
		DataValue<Object> returnData = 
				new DataValue<Object>(IErrorCode.SUCCESS, IErrorCode.SUCCESS_DESC);
		returnData.setData(data);
		
		return returnData;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
