package com.liefeng.property.vo.guard;

import com.liefeng.core.entity.BaseValue;

/**
 * 磁卡权限视图对象
 * @author Huangama
 * @date 2016-2-25
 */
public class GuardCardPrivilegeVo extends BaseValue {

	private static final long serialVersionUID = -7195785485379965107L;

	private String id;
	
	/**
	 * 磁卡ID
	 */
	private String cardId;
	
	/**
	 * 门禁设备ID
	 */
	private String guardDeviceId;
	
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
