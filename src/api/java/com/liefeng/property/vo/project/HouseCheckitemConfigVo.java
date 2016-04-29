package com.liefeng.property.vo.project;

import com.liefeng.core.entity.BaseValue;

/**
 * 房屋验收项配置持久化对象
 * @author xhw
 * @date 2016年4月26日 下午4:19:44
 */
public class HouseCheckitemConfigVo extends BaseValue {

	private static final long serialVersionUID = 3680938398990450847L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 父ID(从0开始) 
	 */
	private String parentId;
	
	/**
	 * 项目名称
	 */
	private String itemname;
	
	/**
	 * 排序
	 */
	private String sortindex;
	
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	/**
	 * 项目id
	 */
	private String projectId;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getItemname() {
		return itemname;
	}


	public void setItemname(String itemname) {
		this.itemname = itemname;
	}


	public String getSortindex() {
		return sortindex;
	}


	public void setSortindex(String sortindex) {
		this.sortindex = sortindex;
	}


	public String getOemCode() {
		return oemCode;
	}


	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
