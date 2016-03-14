package com.liefeng.property.po.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 用户反馈持久化对象 
 * @author xhw
 * @date 2016年3月14日 上午9:55:01
 */
@Entity
@Table(name="t_resident_feedback")
public class ResidentFeedbackPo extends BaseValue{

	private static final long serialVersionUID = -8546955241491669766L;

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 房产ID
	 */
	@Column(name = "house_id")
	private String houseId;

	/**
	 * 是否为业主。0：否；1：是。
	 */
	@Column(name = "is_proprietor")
	private String isProprietor;

	/**
	 * 住户/业主ID
	 */
	@Column(name = "resident_id")
	private String residentId;

	/**
	 * 反馈内容
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * oem编码
	 */
	@Column(name = "oem_code", updatable = false)
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getIsProprietor() {
		return isProprietor;
	}

	public void setIsProprietor(String isProprietor) {
		this.isProprietor = isProprietor;
	}

	public String getResidentId() {
		return residentId;
	}

	public void setResidentId(String residentId) {
		this.residentId = residentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
