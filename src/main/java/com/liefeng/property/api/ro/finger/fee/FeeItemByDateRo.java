package com.liefeng.property.api.ro.finger.fee;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class FeeItemByDateRo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8345276473678719351L;

	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;
	
	@ApiModelProperty(value="房号", required=true)
	@NotNull
	private String houseNum;
	
	@ApiModelProperty(value="费用类型,如果等于null，查询所有的费用")
	private String feeType;
	
	@ApiModelProperty(value="费用所属开始日期：日期格式2016-01-01", required=true)
	@NotNull
	private String startDate;
	
	@ApiModelProperty(value="费用所属结束日期：日期格式2016-01-01", required=true)
	@NotNull
	private String endDate;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
