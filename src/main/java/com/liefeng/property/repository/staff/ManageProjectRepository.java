package com.liefeng.property.repository.staff;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.ManageProjectPo;

/**
 * 员工与项目关系
 * 仓储接口
 * @author 蔡少东
 * @date 2016年2月23日
 */
@Transactional
public interface ManageProjectRepository  extends JpaRepository<ManageProjectPo, String>{
	/**
	 * 根据staffId删除
	 * @param staffId 员工ID
	 */
	public void deleteByStaffId(String staffId);
}
