package com.liefeng.property.vo.project;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 验收模块及子项
 * @author xhw
 * @date 2016年5月5日 下午1:51:38
 */
public class CheckitemVo extends BaseValue {

	private static final long serialVersionUID = 5265295560697716381L;

	private String id;
	
	private String name;
	
	private List<HouseCheckitemConfigVo> childList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HouseCheckitemConfigVo> getChildList() {
		return childList;
	}

	public void setChildList(List<HouseCheckitemConfigVo> childList) {
		this.childList = childList;
	}
	
}
