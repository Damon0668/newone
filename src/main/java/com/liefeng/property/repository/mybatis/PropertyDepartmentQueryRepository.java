package com.liefeng.property.repository.mybatis;

import java.util.List;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.staff.PropertyDepartmentVo;

/**
 * 物业部门查询
 * @author 蔡少东
 * @date 2016年3月14日
 */
public interface PropertyDepartmentQueryRepository extends BaseRepository<PropertyDepartmentVo>{
	
	/**
	 * 查找父部门
	 * @param deptId 部门ID
	 * @return
	 */
	public PropertyDepartmentVo findParentDept(String deptId);
	
	/**
	 * 获取与项目有关的所有子部门，以及公司的领导部门
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月25日 下午3:43:05
	 */
	public List<PropertyDepartmentVo> findAllDepartmentByProjectId(PagingParamVo param);
}
