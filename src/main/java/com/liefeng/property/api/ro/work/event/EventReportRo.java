package com.liefeng.property.api.ro.work.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 报事
 * @author xhw
 */
@ApiModel
public class EventReportRo {

	/**
	 * 项目id
	 */
	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;
	
	/**
	 * 位置
	 */
	@ApiModelProperty(value="位置", required=true)
	@NotNull
	private String location;
	
	
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容", required=true)
	@NotNull
	private String content;
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value="手机号码", required=true)
	@NotNull
	private String phone;
	
	/**
	 * 图片路径
	 */
	@ApiModelProperty(value="图片路径", required=true)
	@NotNull
	private String picUrl;
	
	/**
	 * 报事人姓名
	 */
	@ApiModelProperty(value="报事人姓名", required=true)
	@NotNull
	private String reporterName;
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	
}
