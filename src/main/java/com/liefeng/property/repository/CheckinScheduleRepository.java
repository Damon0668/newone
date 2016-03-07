package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.CheckinSchedulePo;

/**
 * 入住时间安排仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Transactional
public interface CheckinScheduleRepository extends JpaRepository<CheckinSchedulePo, String> {
	
}
