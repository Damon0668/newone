package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡操作日志持久化对象
 * @author Huangama
 * @date 2016-2-25
 */
@Entity
@Table(name = "t_guard_card_log")
public class GuardCardLogPo extends BaseValue {

	private static final long serialVersionUID = 8775658842793216555L;

	@Id
	private String id;
	
	/**
	 * 磁卡ID
	 */
	@Column(name = "card_id")
	private String cardId;
	
	/**
	 * 操作类型。1：发卡；2：注销；3：挂失；4：解挂。
	 */
	@Column(name = "oper_type")
	private String operType;
	
	/**
	 * 操作员工ID
	 */
	@Column(name = "staff_id")
	private String staffId;
	
	/**
	 * 操作时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
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
