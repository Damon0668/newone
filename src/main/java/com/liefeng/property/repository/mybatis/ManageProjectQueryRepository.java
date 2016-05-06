package com.liefeng.property.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.staff.ManageProjectVo;

/**
 * 员工管理项目
 * 仓储接口
 * @author 蔡少东
 * @date 2016年2月23日
 */
public interface ManageProjectQueryRepository  extends BaseRepository<ManageProjectVo>{
	
	List<ManageProjectVo> findByStaffId(@Param(value="staffId") String staffId);
}
