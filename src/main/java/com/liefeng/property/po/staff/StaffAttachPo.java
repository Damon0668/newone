package com.liefeng.property.po.staff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工附件
 * @author 蔡少东
 * @date 2016年3月21日
 */
@Entity
@Table(name = "t_staff_attach")
public class StaffAttachPo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6956989827045281199L;

	@Id
	private String id;
	
	/**
	 * 员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 文件类型
	 */
	@Column(name = "type")
	private String type;
	
	/**
	 * 路径
	 */
	@Column(name = "url")
	private String url;
	
	/**
	 * 文件名字
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 大小
	 */
	@Column(name = "size")
	private Double size;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 创建者
	 */
	@Column(name = "creator_id")
	private String creatorId;

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
}
