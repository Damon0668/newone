package com.liefeng.property.api.ro.work.zentao;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.liefeng.core.entity.BaseValue;

/**
 * 禅道
 * @author xhw
 * @date 2016年4月28日 下午1:45:29
 */
public class ZenTaoRo extends BaseValue{
	
	private static final long serialVersionUID = 6848588949656287820L;

	@ApiModelProperty(value="模板类型", required=true)
	@NotNull
	private String action;

	@ApiModelProperty(value="登陆账号", required=true)
	@NotNull
	private String account;

	@ApiModelProperty(value="事件id", required=true)
	@NotNull
	private String id;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
