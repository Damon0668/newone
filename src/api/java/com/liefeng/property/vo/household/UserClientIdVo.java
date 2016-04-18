package com.liefeng.property.vo.household;

import com.liefeng.core.entity.BaseValue;

/**
 * userId、clientId对象
 * @author xhw
 * @date 2016年4月18日 下午12:00:10
 */
public class UserClientIdVo extends BaseValue {

	private static final long serialVersionUID = -3458753682507573557L;

	private String userId;
	
	private String clientId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
