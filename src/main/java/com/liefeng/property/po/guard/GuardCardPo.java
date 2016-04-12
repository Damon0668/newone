package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 门禁磁卡持久化对象
 * @author Huangama
 * @date 2016-2-25
 */
@Entity
@Table(name = "t_guard_card")
public class GuardCardPo extends BaseValue {

	private static final long serialVersionUID = -3118380178822984579L;

	@Id
	private String id;
	
	/**
	 * 磁卡编号
	 */
	@Column(name = "sn")
	private String sn;
	
	/**
	 * 磁卡类型。0：长期有效；1：临时卡
	 */
	@Column(name = "type")
	private String type;
	
	/**
	 * 状态。0：已注销；1：正常；2：已挂失
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 使用开始日期
	 */
	@Column(name = "start_date")
	private Date startDate;
	
	/**
	 * 使用结束日期
	 */
	@Column(name = "end_date")
	private Date endDate;
	
	/**
	 * 发卡时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;
	
	/**
	 * OEM编码
	 */
	@Column(name = "oem_code")
	private String oemCode;

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

}
