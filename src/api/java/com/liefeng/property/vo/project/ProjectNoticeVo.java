package com.liefeng.property.vo.project;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 小区通告值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
public class ProjectNoticeVo extends BaseValue {

	private static final long serialVersionUID = 2225614261635693557L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 小区ID
	 */
	private String projectId;
	
	/**
	 * 小区名称
	 */
	private String projectName;

	/**
	 * 通告标题
	 */
	private String title;

	/**
	 * 通告内容
	 */
	private String content;

	/**
	 * 图片路径
	 */
	private String imageUrl;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 录入员工ID
	 */
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
