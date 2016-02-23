package com.liefeng.property.repository;

import java.util.List;

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

	/**
	 * 获取某个OEM下所有部门列表
	 * @param oemCode OEM编码
	 * @return 部门列表
	 */
	public List<PropertyDepartmentPo> findDepartmentsByOemCode(String oemCode);
}
