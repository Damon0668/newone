package com.liefeng.property.po.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 业主手机端首页轮播图持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月11日
 */
@Entity
@Table(name = "t_app_home_image")
public class AppHomeImagePo extends BaseValue {

	private static final long serialVersionUID = 1987458679403673666L;

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
	 * 图片路径
	 */
	@Column(name = "image_url")
	private String imageUrl;

	/**
	 * 状态。0：未审核；1：审核通过；2：审核未通过
	 */
	@Column(name = "status")
	private String status;

	/**
	 * 顺序
	 */
	@Column(name = "seq")
	private Integer seq;

	/**
	 * 开始生效时间
	 */
	@Column(name = "start_date")
	private Date startDate;

	/**
	 * 结束生效时间
	 */
	@Column(name = "end_date")
	private Date endDate;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
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
