package com.liefeng.property.api.ro.finger.household;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 入住办理列表请求参数
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
@ApiModel
public class CheckinQueueListRo extends BaseValue {

	private static final long serialVersionUID = -3918356228534656329L;

	@ApiModelProperty(value="项目id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="当前页【最小值为1】", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页面大小【最小值为0】", required=true)
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
