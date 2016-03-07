package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡业务申请持久化对象
 * @author Huangama
 * @date 2016-2-25
 */
@Entity
@Table(name = "t_guard_card_apply")
public class GuardCardApplyPo extends BaseValue {

	private static final long serialVersionUID = -518737428267085929L;

	@Id
	private String id;
	
	/**
	 * 磁卡ID
	 */
	@Column(name = "card_id")
	private String cardId;
	
	/**
	 * 申请业务类型。1：发卡；2：注销；3：挂失；4：解挂。
	 */
	@Column(name = "apply_type")
	private String applyType;
	
	/**
	 * 申请用户ID。关联t_user表
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 办理状态。0：未办理；1：已办理。
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 申请时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 办理时间
	 */
	@Column(name = "process_time")
	private Date processTime;
	
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

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
