package com.liefeng.core.entity;

import java.util.List;

import com.liefeng.core.error.IErrorCode;

/**
 * 数据列表值对象（返回对象列表，没有分页）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataListValue<T> extends ReturnValue {

	private static final long serialVersionUID = -533092849666145420L;

	/**
	 * 返回的数据对象列表
	 */
	private List<T> dataList;

	public DataListValue(String code, String desc) {
		super(code, desc);
	}

	public DataListValue(String code, String desc, List<T> dataList) {
		super(code, desc);
		this.dataList = dataList;
	}
	
	public DataListValue(Enum<?> en, List<T> dataList) {
		super(en.name(), en.toString());
		this.dataList = dataList;
	}

	/**
	 * 成功返回的静态方法
	 * @param dataList 数据对象列表
	 * @return 数据列表值对象
	 */
	public static DataListValue<Object> success(List<Object> dataList) {
		DataListValue<Object> returnData = 
				new DataListValue<Object>(IErrorCode.SUCCESS, IErrorCode.SUCCESS_DESC);
		returnData.setDataList(dataList);
		
		return returnData;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
