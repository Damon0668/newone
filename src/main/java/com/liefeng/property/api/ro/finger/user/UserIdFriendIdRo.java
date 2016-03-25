package com.liefeng.property.api.ro.finger.user;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户id、好友id
 * @author xhw
 * @date 2016年3月21日 下午3:48:19
 */
@ApiModel
public class UserIdFriendIdRo {
	
	@ApiModelProperty(value="用户id", required=true)
	@NotNull
	private String userId;

	@ApiModelProperty(value="好友id", required=true)
	@NotNull
	private String friendId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

}
