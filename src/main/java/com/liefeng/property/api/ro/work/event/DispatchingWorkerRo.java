package com.liefeng.property.api.ro.work.event;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 派工人员接口参数类
 * @author wuzhijing
 * @date 2016-03-28
 */
@ApiModel
public class DispatchingWorkerRo  extends BaseValue {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7676187145623772255L;

	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;
	
	@ApiModelProperty(value="当前登陆人ID", required=true)
	@NotNull
	private String staffId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
