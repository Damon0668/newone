package com.liefeng.property.api.ro.finger.fee;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;

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
	
	private String projectId;
	
	private String houseNum;
	
	private String meterType;
}
