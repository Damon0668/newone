package com.liefeng.property.vo.api;

import com.liefeng.core.entity.BaseValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登陆账户信息
 * @author 蔡少东
 * @date 2016年3月18日
 */
@ApiModel(value="登陆账户信息",parent=BaseValue.class)
public class LoginUserVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1513443395193467863L;

	@ApiModelProperty(value="主键")
	private String id;
	
	@ApiModelProperty(value="全局ID,cust_global_id")
	private String globalId;
	
	@ApiModelProperty(value="手机用户全局ID")
	private String openId;
	
	@ApiModelProperty(value="手机用户ID,t_user表ID")
	private String userId;
	
	@ApiModelProperty(value="项目ID")
	private String projectId;
	
	@ApiModelProperty(value="名字")
	private String name;
	
	@ApiModelProperty(value="用户昵称")
	private String nickName;

	@ApiModelProperty(value="住户类型[1：业主；2：住户]")
	private String householdType;
	
	@ApiModelProperty(value="与业主关系[[字典]RESIDENT_RELATION]")
	private String residentRelation;

	@ApiModelProperty(value="电话")
	private String mobile;
	
	@ApiModelProperty(value="状态")
	private String status;
	
	@ApiModelProperty(value="头像")
	private String pic;
	
	@ApiModelProperty(value="OEM编码")
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getResidentRelation() {
		return residentRelation;
	}

	public void setResidentRelation(String residentRelation) {
		this.residentRelation = residentRelation;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
