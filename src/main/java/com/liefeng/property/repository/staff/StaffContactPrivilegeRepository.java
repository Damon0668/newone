package com.liefeng.property.repository.staff;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.StaffContactPrivilegePo;


/**
 * 员工通讯录权限
 * 仓储层
 * @author 蔡少东
 * @date 2016年2月27日
 */
@Transactional
public interface StaffContactPrivilegeRepository extends JpaRepository<StaffContactPrivilegePo, String>{
	
	/**
	 * 根据员工ID删除所有权限
	 * @param staffId 员工ID
	 */
	public void deleteByStaffId(String staffId);
	
	/**
	 * 根据部门ID删除
	 * @param departmentId 部门ID
	 */
	public void deleteByDepartmentId(String departmentId);
	
	/**
	 * 根据staffId查找通讯权限
	 * @param staffId 员工ID
	 * @return
	 */
	public List<StaffContactPrivilegePo> findByStaffId(String staffId);
}
