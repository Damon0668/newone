package com.liefeng.property.po.household;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 房屋验收项配置持久化对象
 * @author xhw
 * @date 2016年4月26日 下午4:19:44
 */
@Entity
@Table(name = "t_house_checkitem_config")
public class HouseCheckitemConfigPo extends BaseValue {

	private static final long serialVersionUID = -8394151496740761828L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 父ID(从0开始) 
	 */
	@Column(name = "parent_id")
	private String parentId;
	
	/**
	 * 项目名称
	 */
	@Column(name = "itemname")
	private String itemname;
	
	/**
	 * 排序
	 */
	@Column(name = "sortindex")
	private String sortindex;
	
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;


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

}
