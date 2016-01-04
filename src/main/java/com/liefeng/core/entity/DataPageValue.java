package com.liefeng.core.entity;

import java.util.List;

import com.liefeng.core.error.IErrorCode;

/**
 * 分页数据列表值对象（返回对象列表，有分页）
 * @author Huangama
 * @date 2015-11-15
 */
public class DataPageValue<T> extends ReturnValue {

	private static final long serialVersionUID = -8237109320765271196L;

	/**
	 * 返回的数据对象列表
	 */
	private List<T> dataList;
	
	/**
	 * 数据总条数
	 */
	private Long maxCount;
	
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

	public DataPageValue(Enum<?> en) {
		this(null, 0L, 0, 0);
		super.setCode(en.name());
		super.setDesc(en.toString());
	}

	public DataPageValue(Enum<?> en, List<T> dataList, Long maxCount, 
			Integer pageSize, Integer currentPage) {
		this(dataList, maxCount, pageSize, currentPage);
		super.setCode(en.name());
		super.setDesc(en.toString());
	}
	
	public DataPageValue(String code, String desc, List<T> dataList, Long maxCount, 
			Integer pageSize, Integer currentPage) {
		this(dataList, maxCount, pageSize, currentPage);
		super.setCode(code);
		super.setDesc(desc);
	}
	
	public DataPageValue(List<T> dataList, Long maxCount, Integer pageSize, 
			Integer currentPage) {
		super(IErrorCode.SUCCESS, IErrorCode.SUCCESS_DESC);
		this.dataList = dataList;
		this.maxCount = maxCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		if (maxCount % pageSize == 0) {
			this.maxPage = (int) (maxCount / pageSize);
		}
		else {
			this.maxPage = (int) (maxCount / pageSize + 1);
		}
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
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
