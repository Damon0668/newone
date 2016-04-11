package com.liefeng.property.api.ro.finger.fee;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MeterRecordRo extends BaseValue{

	private static final long serialVersionUID = -7312785426460549179L;

	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;

	@ApiModelProperty(value="房号", required=true)
	@NotNull
	private String houseNum;

	@ApiModelProperty(value="仪表类型【1：水表；2：电表；3：燃气表】", required=true)
	@NotNull
	private String meterType;
	
	@ApiModelProperty(value="当前页", required=true)
	@NotNull
	private Integer page;

	@ApiModelProperty(value="每页数据条数", required=true)
	@NotNull
	private Integer size;

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

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
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
