package com.liefeng.property.api.ro.finger.fee;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 是否抄表请求对象
 * @author 蔡少东
 * @date 2016年4月6日
 */
@ApiModel
public class FreeIsCreateRo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1799684259818271453L;
	
	@ApiModelProperty(value="项目ID", required=true)
	@NotNull
	private String projectId;
	
	@ApiModelProperty(value="房号", required=true)
	@NotNull
	private String houseNum;
	
	@ApiModelProperty(value="仪表类型[1：水表；2：电表；3：燃气表]", required=true)
	@NotNull
	private String meterType;

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
	
	
}
