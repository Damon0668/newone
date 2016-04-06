package com.liefeng.property.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 小区通告值对象
 * 
 * @author ZhenTingJun
 * @author xhw
 * @date 2016年3月11日
 */
@ApiModel
@JsonInclude(Include.NON_EMPTY)
public class ProjectNoticeVo extends BaseValue {

	private static final long serialVersionUID = 2225614261635693557L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;

	/**
	 * 小区ID
	 */
	@ApiModelProperty(value="小区ID")
	private String projectId;
	
	/**
	 * 小区名称
	 */
	@ApiModelProperty(value="小区名称")
	private String projectName;

	/**
	 * 通告标题
	 */
	@ApiModelProperty(value="通告标题")
	private String title;

	/**
	 * 通告内容
	 */
	@ApiModelProperty(value="通告内容")
	private String content;

	/**
	 * 图片路径
	 */
	@ApiModelProperty(value="图片路径")
	private String imageUrl;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * 录入员工ID
	 */
	@ApiModelProperty(value="创建时间")
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
