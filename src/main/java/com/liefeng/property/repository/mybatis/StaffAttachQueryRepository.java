package com.liefeng.property.repository.mybatis;

import java.util.List;

import javax.transaction.Transactional;

import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.property.vo.staff.StaffAttachVo;

/**
 * 员工附件
 * @author 蔡少东
 * @date 2016年3月21日
 */
@Transactional
public interface StaffAttachQueryRepository extends BaseRepository<StaffAttachVo>{
	/**
	 * 查找
	 * @param staffId 员工ID
	 */
	public List<StaffAttachVo> findByStaffId(String staffId);
}
