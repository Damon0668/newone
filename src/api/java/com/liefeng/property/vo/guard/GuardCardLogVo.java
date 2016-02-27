package com.liefeng.property.vo.guard;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡操作日志视图对象
 * @author Huangama
 * @date 2016-2-25
 */
public class GuardCardLogVo extends BaseValue {

	private static final long serialVersionUID = 4414690783829330752L;

	private String id;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 操作类型。1：发卡；2：注销；3：挂失；4：解挂。
	 */
	private String operType;
	
	/**
	 * 操作员工ID
	 */
	private String staffId;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * OEM编码
	 */
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
