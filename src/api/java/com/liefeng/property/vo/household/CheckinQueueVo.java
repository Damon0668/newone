package com.liefeng.property.vo.household;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.liefeng.core.entity.BaseValue;

/**
 * 入住排队值对象
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@JsonInclude(Include.NON_EMPTY)
public class CheckinQueueVo extends BaseValue {

	private static final long serialVersionUID = 4953334449200565710L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 业主手机端用户ID
	 */
	private String userId;

	/**
	 * 项目ID
	 */
	private String projectId;

	/**
	 * 房子ID
	 */
	private String houseId;

	/**
	 * 排队序号
	 */
	private Integer seq;

	/**
	 * 办理状态。0：未办理；1：办理中；2：办理完成
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 办理时间
	 */
	private Date tranTime;

	/**
	 * OEM编码
	 */
	private String oemCode;
	
	/*============== 附加字段 ==================*/
	
	/**
	 * 业主ID
	 */
	private String proprietorId;
	
	/**
	 * 业主房产ID
	 */
	private String proprietorHouseId;
	
	/**
	 * 业主名称
	 */
	private String proprietorName;
	
	/**
	 * 房号
	 */
	private String houseNum;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	
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

	public String getProprietorId() {
		return proprietorId;
	}

	public void setProprietorId(String proprietorId) {
		this.proprietorId = proprietorId;
	}

	public String getProprietorHouseId() {
		return proprietorHouseId;
	}

	public void setProprietorHouseId(String proprietorHouseId) {
		this.proprietorHouseId = proprietorHouseId;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
