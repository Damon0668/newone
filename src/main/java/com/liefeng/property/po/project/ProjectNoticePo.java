package com.liefeng.property.po.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 小区通告持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Entity
@Table(name = "t_project_notice")
public class ProjectNoticePo extends BaseValue {

	private static final long serialVersionUID = -8092094387860743158L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 小区ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 通告标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 通告内容
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 图片路径
	 */
	@Column(name = "image_url")
	private String imageUrl;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 录入员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

}
