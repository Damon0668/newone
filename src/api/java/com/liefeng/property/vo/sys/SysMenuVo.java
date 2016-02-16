package com.liefeng.property.vo.sys;

import java.util.List;

import com.liefeng.core.entity.BaseVoValue;

/**
 * 系统菜单
 * @author 蔡少东
 * @date 2016年2月1日
 */
public class SysMenuVo extends BaseVoValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4298476470609885410L;
	
	private Long id;
	
	private Long parentId;
	
	private Integer type;
	
	private String name;
	
	private String code;
	
	private String url;
	
	private String icon;
	
	private Long order;
	
	private String desc;
	
	private int isSelect;
	
	/**
	 * 子菜单
	 */
	private List<SysMenuVo> subMenus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(int isSelect) {
		this.isSelect = isSelect;
	}

	public List<SysMenuVo> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SysMenuVo> subMenus) {
		this.subMenus = subMenus;
	}
}
