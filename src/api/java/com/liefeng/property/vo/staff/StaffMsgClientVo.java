package com.liefeng.property.vo.staff;

import java.util.Date;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工消息推送客户端信息表
 * @author 蔡少东
 * @date 2016年3月25日
 */
public class StaffMsgClientVo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5703252970905317933L;

	private String id;
	
	private String staffId;
	
	private String clientId;
	
	private Date updateTime;
	
	private String oemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
