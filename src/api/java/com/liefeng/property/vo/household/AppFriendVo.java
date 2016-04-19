package com.liefeng.property.vo.household;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;


/**
 * 手机端好友值对象
 *  
 * @author xhw
 * @date 2016年3月16日 下午1:58:34
 */
@ApiModel(value="好友信息")
@JsonInclude(Include.NON_EMPTY)
public class AppFriendVo extends BaseValue{
	
	private static final long serialVersionUID = 439475851394709911L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String id;

	/**
	 * 用户ID。对应t_user表的ID
	 */
	@ApiModelProperty(value="用户ID【t_user表的ID】")
	private String userId;

	/**
	 * 好友ID。对应t_user表的ID
	 */
	@ApiModelProperty(value="好友ID【t_user表的ID】")
	private String friendId;

	/**
	 * 状态。0：等待验证；1：已为好友；2：拒绝
	 */
	@ApiModelProperty(value="状态【0：等待对方验证{显示：发消息按钮}；1：已为好友{显示：发消息按钮}；2：已拒绝{显示：发消息按钮、添加按钮}；3：等待我验证{显示：同意、拒绝按钮}】")
	private String status;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	private Date updateTime;
	
	/**
	 * oem编码
	 */
	@ApiModelProperty(value="oem编码")
	private String oemCode;
	
	/**
	 * 好友姓名
	 */
	@ApiModelProperty(value="好友姓名")
	private String friendName;
	
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value="手机号码")
	private String phone;
	
	/**
	 * 好友头像
	 */
	@ApiModelProperty(value="好友头像")
	private String avatarUrl;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
