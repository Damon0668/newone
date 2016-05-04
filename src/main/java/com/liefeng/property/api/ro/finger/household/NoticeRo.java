package com.liefeng.property.api.ro.finger.household;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 工作台
 * 查看通知列表请求参数
 * @author xhw
 * @date 2016年3月21日 下午2:01:02
 */
@ApiModel
public class NoticeRo extends BaseValue {

	private static final long serialVersionUID = -6939350928959032246L;

	@ApiModelProperty(value="通知类型[1：社区通告；2：温馨提醒；3：通知；4：社区动态]", required=true)
	@NotNull
	private String noticeType;
	
	@ApiModelProperty(value="项目Id", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="楼栋Id", required=true)
	@NotNull
	private String groupId;
	
	@ApiModelProperty(value="当前页[第几页，最小值为1]", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页大小[每页条数，最小值为0]", required=true)
	@NotNull
	private Integer size;

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
