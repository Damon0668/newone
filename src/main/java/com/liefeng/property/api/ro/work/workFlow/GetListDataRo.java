package com.liefeng.property.api.ro.work.workFlow;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取
 * @author wuzhijing
 *
 */
@ApiModel
public class GetListDataRo extends BaseValue {

	private static final long serialVersionUID = 3522439492468187233L;
	
	@ApiModelProperty(value="获取什么列表【1：待审批;2:我已审批;3:我发起的】", required=true)
	@NotNull
	private String type;
	
	@ApiModelProperty(value="当前登陆人id", required=true)
	@NotNull
	private String staffId;
	
	@ApiModelProperty(value="当前页【最小值为1】", required=true)
	@NotNull
	private Integer page;
	
	@ApiModelProperty(value="页面大小【最小值为0】", required=true)
	@NotNull
	private Integer size;

	public String getType() {
		return type;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getSize() {
		return size;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
}
