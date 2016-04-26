package com.liefeng.property.vo.staff;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工附件
 * @author 蔡少东
 * @date 2016年3月21日
 */
public class StaffAttachVo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -899281753784753928L;

	private String id;
	
	/**
	 * 员工ID
	 */
	private String staffId;
	
	/**
	 * 文件类型
	 */
	private String type;
	
	/**
	 * 路径
	 */
	private String url;
	
	/**
	 * 文件名字
	 */
	private String name;
	
	/**
	 * 大小
	 */
	private Double size;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建者
	 */
	private String creatorId;
	
	/**
	 * 创建者名字
	 */
	private String creatorName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
