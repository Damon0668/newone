package com.liefeng.property.repository.staff;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.staff.PropertyDepartmentPo;

/**
 * 部门信息仓储层
 * @author ZhenTingJun
 * @author Huangama
 * @date 2015-12-24
 */
@Transactional
public interface PropertyDepartmentRepository extends JpaRepository<PropertyDepartmentPo, String> {

	/**
	 * 根据部门名字和OEM编码查询部门信息
	 * @param name 部门名字
	 * @param oemCode OEM编码
	 * @return 部门信息
	 */
	public PropertyDepartmentPo findDepartmentByNameAndOemCode(String name, String oemCode);
	
	/**
	 * 获取某个OEM下所有部门列表
	 * @param oemCode OEM编码
	 * @return 部门列表
	 */
	public List<PropertyDepartmentPo> findDepartmentsByOemCode(String oemCode);
	
	/**
	 * 查询子部门
	 * @param parentId 父部门
	 * @return
	 */
	public List<PropertyDepartmentPo> findDepartmentsByParentId(String parentId);
	

	@Query("select dp from PropertyDepartmentPo dp where dp.deptType=?1 and  dp.oemCode=?2 and dp.parentId='0' and dp.projectId='0'")
	public PropertyDepartmentPo findParentDept(String deptType, String oemCode);
	
	/**
	 * 根据项目id获取部门
	 * @param projectId
	 * @return
	 */
	public List<PropertyDepartmentPo> findDepartmentsByProjectId(String projectId);
	
}
