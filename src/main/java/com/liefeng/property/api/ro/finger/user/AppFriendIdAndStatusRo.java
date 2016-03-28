package com.liefeng.property.api.ro.finger.user;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 好友记录id、状态
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class AppFriendIdAndStatusRo extends BaseValue {
	
	private static final long serialVersionUID = 7564219411533439538L;

	@ApiModelProperty(value="好友记录的id【t_app_friend表的id】", required=true)
	@NotNull
	private String id;

	@ApiModelProperty(value="结果【 1：同意， 2：拒绝】", required=true)
	@NotNull
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
