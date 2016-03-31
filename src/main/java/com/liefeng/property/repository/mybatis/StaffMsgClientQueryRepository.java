package com.liefeng.property.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.staff.StaffMsgClientVo;

public interface StaffMsgClientQueryRepository extends BaseRepository<StaffMsgClientVo>{

	/**
	 * 查询个推ID列表
	 * @param staffIds
	 * @return
	 */
	public List<String> findClientIds(@Param("staffIds") String staffIds);
}
