package com.liefeng.property.vo.guard;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁磁卡视图对象
 * 
 * @author Huangama
 * @date 2016-2-25
 */
public class GuardCardVo extends BaseValue {

	private static final long serialVersionUID = -5697303393169304896L;

	private String id;

	/**
	 * 磁卡编号
	 */
	private String sn;

	/**
	 * 磁卡类型。0：长期有效；1：临时卡
	 */
	private String type;

	/**
	 * 状态。0：已注销；1：正常；2：已挂失
	 */
	private String status;

	/**
	 * 使用开始日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;

	/**
	 * 使用结束日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;

	/**
	 * 发卡时间
	 */
	private Date createTime;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/**
	 * 磁卡绑定用户信息
	 */
	private GuardCardUserVo guardCardUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public GuardCardUserVo getGuardCardUser() {
		return guardCardUser;
	}

	public void setGuardCardUser(GuardCardUserVo guardCardUser) {
		this.guardCardUser = guardCardUser;
	}

}
