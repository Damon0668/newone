package com.liefeng.property.vo.household;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;


/**
 * 用户反馈值对象 
 * @author xhw
 * @date 2016年3月14日 上午9:55:01
 */
public class ResidentFeedbackVo extends BaseValue{

	private static final long serialVersionUID = -5993150714606267398L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 房产ID
	 */
	private String houseId;

	/**
	 * 是否为业主。0：否；1：是。
	 */
	private String isProprietor;

	/**
	 * 住户/业主ID
	 */
	private String residentId;

	/**
	 * 反馈内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * oem编码
	 */
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
