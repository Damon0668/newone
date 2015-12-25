package com.liefeng.property.repository;

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

}
