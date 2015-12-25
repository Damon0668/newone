package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.PropertyDepartmentPo;

/**
 * 部门信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface PropertyDepartmentRepository extends JpaRepository<PropertyDepartmentPo, String> {

}
