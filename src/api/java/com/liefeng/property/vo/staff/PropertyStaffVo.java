package com.liefeng.property.vo.staff;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;
import com.liefeng.property.annotation.Dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物业员工值对象
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@ApiModel
@JsonInclude(Include.NON_EMPTY)
public class PropertyStaffVo extends BaseValue {

	private static final long serialVersionUID = 4284129283467742818L;
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="员工ID=(openId)")
	private String id;
	
	@ApiModelProperty(value="openId")
	private String openId;
	/**
	 * 登陆账号
	 */
	@ApiModelProperty(value="登陆账号")
	private String account;
	
	/**
	 * 登陆密码
	 */
	@ApiModelProperty(value="登陆密码")
	private String password;
	
	/**
	 * 员工姓名
	 */
	@ApiModelProperty(value="名字")
	private String name;
	
	/**
	 * 员工工号
	 */
	@ApiModelProperty(value="员工工号")
	private String number;
	
	/**
	 * 入职时间
	 */
	@ApiModelProperty(value="入职时间")
	private Date entryTime;
	
	/**
	 * 在职状态
	 * 1：在职；2：离职
	 */
	@Dict(group = "WORK_STATUS")
	@ApiModelProperty(value = "在职状态[1：在职；2：离职]")
	private String workStatus;
	
	/**
	 * 所属部门ID
	 */
	@ApiModelProperty(value="所属部门ID")
	private String departmentId;
	
	/**
	 * 上级领导
	 */
	@ApiModelProperty(value="上级领导")
	private String director;
	
	/**
	 * 职位
	 */
	@Dict(group="POSITION")
	@ApiModelProperty(value="职位")
	private String position;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	
	/**
	 * 创建者ID
	 */
	@ApiModelProperty(value="创建者ID")
	private String creatorId;
	
	/**
	 * OEM编码
	 */
	@ApiModelProperty(value="OEM编码")
	private String oemCode;
	
	/**
	 * 状态
	 */
	@ApiModelProperty(value="状态[1:激活;2:注销]")
	private String status;
	
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value="部门名称")
	private String departmentName;
	
	/**
	 * 职位名称
	 */
	@ApiModelProperty(value="职位名称")
	private String positionName;

	/**
	 * clientId
	 */
	@ApiModelProperty(value="clientId")
	private String clientId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
