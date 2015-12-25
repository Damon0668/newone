package com.liefeng.core.entity;

import java.util.List;

import com.liefeng.core.error.IErrorCode;

/**
 * 数据列表值对象（返回对象列表，没有分页）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataListValue<T> extends BaseValue {

	private static final long serialVersionUID = -533092849666145420L;

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回描述
	 */
	private String desc;
	
	/**
	 * 返回的数据对象列表
	 */
	private List<T> dataList;

	public DataListValue() {
		super();
	}

	public DataListValue(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public DataListValue(String code, String desc, List<T> dataList) {
		super();
		this.code = code;
		this.desc = desc;
		this.dataList = dataList;
	}
	
	public DataListValue(Enum<?> en, List<T> dataList) {
		super();
		this.code = en.name();
		this.desc = en.toString();
		this.dataList = dataList;
	}

	/**
	 * 成功返回的静态方法
	 * @param dataList 数据对象列表
	 * @return 数据列表值对象
	 */
	public static DataListValue<Object> success(List<Object> dataList) {
		DataListValue<Object> returnData = new DataListValue<Object>();
		returnData.setCode(IErrorCode.SUCCESS);
		returnData.setDesc(IErrorCode.SUCCESS_DESC);
		returnData.setDataList(dataList);
		
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

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
