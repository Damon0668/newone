package com.liefeng.property.domain.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.property.repository.StaffContactPrivilegeRepository;

/**
 * 员工通讯录权限
 * 领域模型
 * @author 蔡少东
 * @date 2016年2月27日
 */
@Service
@Scope("prototype")
public class StaffContactPrivilegeContext{
	
	@Autowired
	private StaffContactPrivilegeRepository staffContactPrivilegeRepository;
	/**
	 * 员工ID
	 */
	private String staffId;
	
	protected void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	private static StaffContactPrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(StaffContactPrivilegeContext.class);
	}
	
	/**
	 * 构建上下文
	 * @return 上下文对象
	 */
	public static StaffContactPrivilegeContext build() {
		StaffContactPrivilegeContext staffContactPrivilegeContext = getInstance();
		return staffContactPrivilegeContext;
	}
	
	public static StaffContactPrivilegeContext build(String staffId) {
		StaffContactPrivilegeContext staffContactPrivilegeContext = getInstance();
		staffContactPrivilegeContext.setStaffId(staffId);
		return staffContactPrivilegeContext;
	}
}
