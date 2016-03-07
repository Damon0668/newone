package com.liefeng.property.repository.mybatis;

import java.util.List;

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
	public List<PropertyStaffVo> queryByDeptIdAndProjectId(PagingParamVo param);
}
