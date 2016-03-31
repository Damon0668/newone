package com.liefeng.property.vo.staff;

import java.util.Date;

import com.liefeng.common.util.EncryptionUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.entity.BaseValue;

/**
 * 物业员工列表展现
 * @author 蔡少东
 * @date 2016年2月23日
 */
public class PropertyStaffListVo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = -203552099872011992L;

	private String id;
	
	private String number;
	
	private String name;
	
	private String encryptIdNum;
	
	private String idNum;
	
	private Date entryTime;
	
	private String phone;
	
	private String department;
	
	private String position;
	
	private String workStatus;
	
	private String account;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEncryptIdNum() {
		return encryptIdNum;
	}

	public void setEncryptIdNum(String encryptIdNum) {
		this.encryptIdNum = encryptIdNum;
	}

	public String getIdNum() {
		if(ValidateHelper.isNotEmptyString(encryptIdNum)){
			return EncryptionUtil.decryptCustIdNum(encryptIdNum);
		}
		return this.idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
		this.encryptIdNum = EncryptionUtil.encryptCustIdNum(idNum);
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
