package com.liefeng.property.repository.staff;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.staff.PropertyStaffPo;

/**
 * 物业员工仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface PropertyStaffRepository extends JpaRepository<PropertyStaffPo, String> {
	/**
	 * 根据登陆账号查找物业员工
	 * @param account
	 * @return
	 */
	public PropertyStaffPo findByAccount(String account);
}
