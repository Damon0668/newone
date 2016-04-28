package com.liefeng.property.vo.guard;

import java.util.List;

import com.liefeng.core.entity.BaseValue;

/**
 * 出入管理
 * 设备类型
 * @author 蔡少东
 * @date 2016年4月27日
 */
public class GuardDeviceTypeVo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7711137419825710249L;

	public String id;
	
	public String groupCode;
	
	public String name;
	
	private String value;
	
	public List<DevicePositionVo> children;

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

	public List<DevicePositionVo> getChildren() {
		return children;
	}

	public void setChildren(List<DevicePositionVo> children) {
		this.children = children;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getText(){
		return this.name;
	}
	
	public void setText(String text){
		this.name = text;
	}
}
