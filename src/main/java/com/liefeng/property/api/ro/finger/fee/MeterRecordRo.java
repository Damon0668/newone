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
	
	@ApiModelProperty(value="本期读数", required=true)
	@NotNull
	private Double currNum;
	
	@ApiModelProperty(value="抄表时间")
	private Date readDate;
	
	@ApiModelProperty(value="仪表类型【1：水表；2：电表；3：燃气表】", required=true)
	@NotNull
	private String meterType;
	
	@ApiModelProperty(value="楼栋id", required=true)
	@NotNull
	private String buildingId;
}
