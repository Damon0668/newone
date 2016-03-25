package com.liefeng.property.repository.staff;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.StaffAttachPo;

/**
 * 员工材料存储
 * @author 蔡少东
 * @date 2016年3月21日
 */
@Transactional
public interface StaffAttachRepository extends JpaRepository<StaffAttachPo, String> {
	/**
	 * 删除
	 * @param staffId 员工ID
	 */
	public void deleteByStaffId(String staffId);
}
