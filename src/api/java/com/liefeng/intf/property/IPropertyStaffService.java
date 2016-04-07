package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.bo.property.PropertyStaffBo;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;
import com.liefeng.property.vo.staff.PropertyStaffDetailInfoVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;
import com.liefeng.property.vo.staff.StaffArchiveVo;

/**
 * 物业员工服务
 * 对外暴露接口
 * @author 蔡少东
 * @date 2016年2月22日
 */
public interface IPropertyStaffService {
	
	/**
	 * 分页查询物业员工
	 * @param propertyStaffBo
	 * @param page 第几页
	 * @param size 页面数据大小
	 * @return
	 */
	public DataPageValue<PropertyStaffListVo> listPropertyStaff4Page(PropertyStaffBo propertyStaffBo, int page, int size);
	
	/**
	 * 创建物业员工
	 * @param propertyStaffDetailInfo 物业员工
	 * @return
	 * @throws LiefengException
	 * @throws Exception 
	 */
	public void createStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws LiefengException;
	
	/**
	 * 更新物业员工
	 * @param propertyStaffDetailInfo 物业员工
	 * @return
	 * @throws LiefengException
	 * @throws Exception 
	 */
	public void updateStaff(PropertyStaffDetailInfoVo propertyStaffDetailInfo) throws LiefengException;
	/**
	 * 批量更新员工状态
	 * @param staffIdList 员工ID列表
	 * @param status 状态
	 * @return
	 */
	public void updateStaffStatus(List<String> staffIdList,String status) throws LiefengException;
	
	/**
	 * 修改密码
	 * @param staffId 员工ID
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 */
	public void updateStaffPassword(String staffId, String oldPassword, String newPassword) throws PropertyException;

	/**
	 * 查询物业员工
	 * @param staffId 员工ID
	 * @return
	 */
	public PropertyStaffVo findPropertyStaffById(String staffId);
	
	/**
	 * 查询物业员工
	 * @param account 员工登陆账号(唯一)
	 * @return
	 * @throws LiefengException
	 */
	public PropertyStaffVo findPropertyStaffByAccount(String account);
	
	/**
	 * 查询物业员工
	 * @param departmentId 部门ID
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaff(String departmentId);
	
	/**
	 * 查询物业员工
	 * @param roleId 角色ID
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffByRoleId(Long roleId);
	
	/**
	 * 查询物业员工
	 * @param departmentId 部门ID
	 * @param projectId 项目ID
	 * @return
	 * @throws LiefengException
	 */
	public List<PropertyStaffVo> findPropertyStaff(String departmentId, String projectId);
	
	/**
	 * 查询物业员工
	 * 详细信息
	 * @param staffId 员工ID
	 * @return
	 */
	public PropertyStaffDetailInfoVo findStaffDetailInfo(String staffId);
	
	/**
	 * 获取员工通讯录权限
	 * @param staffId 员工ID
	 * @return
	 */
	public List<PropertyDepartmentVo> findStaffContactPrivilege(String staffId);
	
	/**
	 * 查询物业员工档案
	 * @param staffId 员工ID
	 * @return
	 */
	public StaffArchiveVo findStaffArchByStaffId(String staffId);
	
	/**
	 * 查询物业员工  包含部门名称，职位名称
	 * @param staffId 员工ID
	 * @return
	 */
	public PropertyStaffVo findPropertyStaffById4DP(String staffId);
	
	/**
	 * 查询员工的个推ID
	 * @param staffId 员工ID
	 * @return
	 */
	public String findStaffMsgClientId(String staffId);
	
	/**
	 * 查询员工的个推ID列表
	 * @param staffId 员工ID列表
	 * @return
	 */
	public List<String> findStaffMsgClientIds(List<String> staffIds);
	
	/**
	 * 设置员工个推ID
	 * @param staffId 员工ID
	 * @param clientId 个推ID
	 */
	public void  settIngStaffMsgClientId(String staffId,String clientId);
	/*********************** 部门相关接口 **********************/
	
	/**
	 * 创建部门
	 * @param department 部门VO
	 */
	public void createDepartment(PropertyDepartmentVo department) throws LiefengException;
	
	/**
	 * 更新部门
	 * @param department 部门VO
	 */
	public void updateDepartment(PropertyDepartmentVo department) throws LiefengException;
	
	/**
	 * 删除部门
	 * @param departmentId 部门ID
	 */
	public void deleteDepartment(String departmentId);
	
	/**
	 * 查询某个OEM下所有部门列表
	 * 分页
	 * @param page
	 * @param size
	 * @return
	 */
	public DataPageValue<PropertyDepartmentVo> listDepartment4Page(Integer page, Integer size);
	
	/**
	 * 查询某个OEM下所有部门列表
	 * @return 部门列表
	 */
	public List<PropertyDepartmentVo> getDepartments();
	
	/**
	 * 查询多个部门
	 * @param departmentIds
	 * @return
	 */
	public List<PropertyDepartmentVo> getDepartments(List<String> departmentIds);
	
	/**
	 * 根据部门ID获取部门负责人信息
	 * @param departmentId 部门ID
	 * @return 部门负责人信息
	 */
	public PropertyStaffVo getDepartmentDirector(String departmentId);
	
	/**
	 * 根据部门ID获取部门
	 * @param departmentId 部门ID
	 * @return 部门信息
	 */
	public PropertyDepartmentVo getDepartment(String departmentId);
	
	/**
	 * 获取部门及部门负责人
	 * @param projectId
	 * @return
	 */
	public List<PropertyStaffVo> getDepartmentDirectorList(String projectId,String departmentId);

	/**
	 * 查询物业员工列表  包含部门名称，职位名称
	 * @param staffIds 员工ID，逗号隔开
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffById4DPList(String staffIds);
	
	/**
	 * 通过岗位获取员工信息 包含部门名称，职位名称
	 * @param positionId
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffByPosition(String positionId);
}
