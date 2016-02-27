package com.liefeng.property.repository;

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

}
