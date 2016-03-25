package com.liefeng.property.po.staff;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 员工消息推送客户端信息表
 * @author 蔡少东
 * @date 2016年3月25日
 */
@Entity
@Table(name = "t_staff_msg_client")
public class StaffMsgClientPo extends BaseValue{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3970679540456962658L;

	@Id
	private String id;
	
	@Column(name = "staff_id")
	private String staffId;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "update_time")
	private Date updateTime;
	
	@Column(name = "oem_code")
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
