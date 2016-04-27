package com.liefeng.property.repository.staff;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * 根据oem查询员工
	 * 员工必须是在职并且激活的
	 * @param oemCode
	 * @return
	 */
	@Query("select st from PropertyStaffPo st where st.departmentId = ?1 and st.oemCode = ?2 and  st.workStatus = '1' and st.status = '1' and creatorId != '0' ")
	public List<PropertyStaffPo> findByDepartmentIdAndOemCode(String departmentId, String oemCode);
	
	/**
	 * 根据员工工号查询员工
	 * @param number 工号
	 * @param oemCode OEM编码
	 * @return
	 * @author ZhenTingJun
	 * @date 2016年4月26日
	 */
	public List<PropertyStaffPo> findByNumberAndOemCode(String number, String oemCode);
}