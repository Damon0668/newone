package com.liefeng.property.api.ro.id;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通知id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class NoticeIdRo extends BaseValue {
	
	private static final long serialVersionUID = -5318937605003312920L;
	
	@ApiModelProperty(value="通知主键【id】", required=true)
	@NotNull
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
