package com.liefeng.property.po.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.liefeng.core.entity.BaseValue;

/**
 * 角色--用户
 * @author 蔡少东
 * @date 2016年2月1日
 */
@Entity
@Table(name = "t_sys_role_user")
public class SysRoleUserPo extends BaseValue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4788763942135814904L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "oem_code")
	private String oemCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
}
