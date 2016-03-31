package com.liefeng.property.repository.staff;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.StaffArchivePo;

/**
 * 员工档案仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface StaffArchiveRepository extends JpaRepository<StaffArchivePo, String> {
	/**
	 * 根据员工ID查找档案
	 * @param staffId
	 * @return
	 */
	public StaffArchivePo findByStaffId(String staffId);
	
	/**
	 * 根据手机号查询员工档案
	 * @param phone
	 * @return
	 */
	public StaffArchivePo findByPhoneAndOemCode(String phone,String oemCode);
}
