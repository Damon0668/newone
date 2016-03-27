package com.liefeng.property.api.ro.finger.event;

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
	 * 房间号
	 */
	@ApiModelProperty(value="房间号", required=true)
	@NotNull
	private String houseNum;
	
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
	
	/**
	 * 业主姓名
	 */
	@ApiModelProperty(value="业主姓名", required=true)
	@NotNull
	private String proprietorName;

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

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
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

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}
	
}
