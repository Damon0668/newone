package com.liefeng.property.repository.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.staff.PropertyStaffListVo;
import com.liefeng.property.vo.staff.PropertyStaffVo;

/**
 * 物业员工信息查询接口
 * @author 蔡少东
 * @date 2016年2月23日
 */
public interface PropertyStaffQueryRepository extends BaseRepository<PropertyStaffListVo>{
	
	/**
	 * 查询员工
	 * 根据部门ID和项目ID
	 * @param param
	 * @return
	 */
	public List<PropertyStaffVo> queryByDeptIdAndProjectId(PagingParamVo param);
	
	/**
	 * 查找员工
	 * 根据角色ID
	 * @param param
	 * @return
	 */
	public List<PropertyStaffVo> queryByRoleId(PagingParamVo param);
	
	/**
	 * 员工信息，包含 部门名称，岗位名称
	 * @return
	 */
	public PropertyStaffVo findPropertyStaffById4DP(String propertyStaffId);
	
	/**
	 * 获取部门负责人信息列表
	 * @param projectId
	 */
	public List<PropertyStaffVo> getDepartmentDirectorList(Map<String, String> param);

	/**
	 * 员工信息列表，包含 部门名称，岗位名称
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffById4DPList(String[] staffIds);

	/**
	 * 根据岗位id获取员工信息 包含 部门名称，岗位名称
	 * @return
	 */
	public List<PropertyStaffVo> findPropertyStaffByPosition(String positionId);
	
	/**
	 * 查询物业员工
	 * 根据menu的code查询具有此权限的员工
	 * @param menuCode
	 * @return
	 */
	public List<PropertyStaffVo> findStaffByMenuCode(@Param("menuCode") String menuCode, @Param("oemCode") String oemCode);
	
	/**
	 * 查询员工clientId
	 * 根据部门ID和项目ID
	 * @param param
	 * @return
	 */
	public List<PropertyStaffVo> queryByProjectIdAndDeptId(PagingParamVo param);
}
