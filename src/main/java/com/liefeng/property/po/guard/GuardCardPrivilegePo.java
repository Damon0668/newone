package com.liefeng.property.po.guard;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡权限持久化对象
 * @author Huangama
 * @date 2016-2-25
 */
@Entity
@Table(name = "t_guard_card_privilege")
public class GuardCardPrivilegePo extends BaseValue {

	private static final long serialVersionUID = 6716020259050072430L;

	@Id
	private String id;
	
	/**
	 * 磁卡ID
	 */
	@Column(name = "card_id")
	private String cardId;
	
	/**
	 * 门禁设备ID
	 */
	@Column(name = "guard_device_id")
	private String guardDeviceId;
	
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

	public String getGuardDeviceId() {
		return guardDeviceId;
	}

	public void setGuardDeviceId(String guardDeviceId) {
		this.guardDeviceId = guardDeviceId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

}
