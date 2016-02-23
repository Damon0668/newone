package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.entity.ReturnValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年2月22日
 */
public interface IPropertyStaffService {
	
	
	/**
	 * 分页查询物业员工
	 * @param page 第几页
	 * @param size 页面数据大小
	 * @return
	 */
	public DataPageValue<PropertyStaffVo> findPropertyStaff(int page, int size);
	
	/**
	 * 创建物业员工
	 * @param propertyStaff 物业员工
	 * @return
	 * @throws LiefengException
	 * @throws Exception 
	 */
	public ReturnValue createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws LiefengException, Exception;
	
	/**
	 * 创建物业员工
	 * @param propertyStaff 物业员工
	 * @return
	 * @throws LiefengException
	 */
	public ReturnValue createStaff(PropertyStaffVo propertyStaff) throws LiefengException;
	
	/**
	 * 更新物业员工
	 * @param propertyStaff
	 * @return
	 * @throws LiefengException
	 */
	public ReturnValue updateStaff(PropertyStaffVo propertyStaff) throws LiefengException;
	
	/*********************** 部门相关接口 **********************/
	
	/**
	 * 创建部门
	 * @param department 部门VO
	 */
	public void createDepartment(PropertyDepartmentVo department);
	
	/**
	 * 更新部门
	 * @param department 部门VO
	 */
	public void updateDepartment(PropertyDepartmentVo department);
	
	/**
	 * 删除部门
	 * @param departmentId 部门ID
	 */
	public void deleteDepartment(String departmentId);
	
	/**
	 * 查询某个OEM下所有部门列表
	 * @return 部门列表
	 */
	public List<PropertyDepartmentVo> getDepartments();
	
	/**
	 * 根据部门ID获取部门负责人信息
	 * @param departmentId 部门ID
	 * @return 部门负责人信息
	 */
	public PropertyStaffVo getDepartmentDirector(String departmentId);
}
