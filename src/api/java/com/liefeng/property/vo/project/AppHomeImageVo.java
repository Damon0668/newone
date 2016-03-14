package com.liefeng.property.vo.project;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主手机端首页轮播图值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
public class AppHomeImageVo extends BaseValue {

	private static final long serialVersionUID = 7050275557476695011L;

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
	 * 图片路径
	 */
	private String imageUrl;

	/**
	 * 状态。0：未审核；1：审核通过；2：审核未通过
	 */
	private String status;

	/**
	 * 顺序
	 */
	private Integer seq;

	/**
	 * 开始生效时间
	 */
	private Date startDate;

	/**
	 * 结束生效时间
	 */
	private Date endDate;

	/**
	 * 创建时间
	 */
	private Date createTime;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
