package com.liefeng.property.api.ro.finger.fee;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建抄表接口参数类
 *
 */
@ApiModel
public class CreateMeterRecordRo extends BaseValue {

	private static final long serialVersionUID = 2543445263894573019L;
	

	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;
	
	
	@ApiModelProperty(value="房号", required=true)
	@NotNull
	private String houseNum;
	
	@ApiModelProperty(value="仪表类型【1：水；2：电；3:气】", required=true)
	@NotNull
	private String meterType;
	
	@ApiModelProperty(value="本期表数", required=true)
	@NotNull
	private Double currNum;
	
	@ApiModelProperty(value="抄表日期",required=true)
	@NotNull
	private Date readDate;
	
	@ApiModelProperty(value="数据来源[1：WEB；2：Android；3：IOS]", required=true)
	@NotNull
	private String from;
	
	public String getProjectId() {
		return projectId;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public String getMeterType() {
		return meterType;
	}

	public Double getCurrNum() {
		return currNum;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public void setCurrNum(Double currNum) {
		this.currNum = currNum;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
}