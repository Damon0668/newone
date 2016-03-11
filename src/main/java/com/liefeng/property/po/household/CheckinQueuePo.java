package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 入住排队持久化对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Entity
@Table(name = "t_checkin_queue")
public class CheckinQueuePo extends BaseValue {

	private static final long serialVersionUID = 4998760990791810877L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 业主手机端用户ID
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * 项目ID
	 */
	@Column(name = "project_id")
	private String projectId;

	/**
	 * 房子ID
	 */
	@Column(name = "house_id")
	private String houseId;

	/**
	 * 排队序号
	 */
	@Column(name = "seq")
	private Integer seq;

	/**
	 * 办理状态。0：未办理；1：办理中；2：办理完成
	 */
	@Column(name = "status")
	private String status;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 办理时间
	 */
	@Column(name = "tran_time")
	private Date tranTime;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getTranTime() {
		return tranTime;
	}

	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
