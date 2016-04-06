package com.liefeng.property.api.ro.finger.project;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物业须知列表请求参数
 * @author xhw
 * @date 2016年4月6日 下午2:21:41
 */
@ApiModel
public class ProjectNoticeRo extends BaseValue {

	private static final long serialVersionUID = 9127694868163963937L;

	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;
	
	@ApiModelProperty(value="当前页【第几页，最小值为1】", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页大小【每页条数，最小值为0】", required=true)
	@NotNull
	private Integer size;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
