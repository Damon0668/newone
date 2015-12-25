package com.liefeng.core.entity;

import java.util.List;

import com.liefeng.core.error.IErrorCode;

/**
 * 分页数据列表值对象（返回对象列表，有分页）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataPageValue<T> extends BaseValue {

	private static final long serialVersionUID = -8237109320765271196L;

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
	
	/**
	 * 数据总条数
	 */
	private Integer maxCount;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;
	
	/**
	 * 每页大小
	 */
	private Integer pageSize;
	
	/**
	 * 总页数
	 */
	private Integer maxPage;

	public DataPageValue() {
		super();
	}
	
	public DataPageValue(Enum<?> en) {
		this(null, 0, 0, 0);
		this.code = en.name();
		this.desc = en.toString();
	}

	public DataPageValue(Enum<?> en, List<T> dataList, Integer maxCount, 
			Integer pageSize, Integer currentPage) {
		this(dataList, maxCount, pageSize, currentPage);
		this.code = en.name();
		this.desc = en.toString();
	}
	
	public DataPageValue(String code, String desc, List<T> dataList, Integer maxCount, 
			Integer pageSize, Integer currentPage) {
		this(dataList, maxCount, pageSize, currentPage);
		this.code = code;
		this.desc = desc;
	}
	
	public DataPageValue(List<T> dataList, Integer maxCount, Integer pageSize, 
			Integer currentPage) {
		super();
		this.setCode(IErrorCode.SUCCESS);
		this.setDesc(IErrorCode.SUCCESS_DESC);
		this.dataList = dataList;
		this.maxCount = maxCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		if (maxCount % pageSize == 0) {
			this.maxPage = maxCount / pageSize;
		}
		else {
			this.maxPage = maxCount / pageSize + 1;
		}
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

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

}
