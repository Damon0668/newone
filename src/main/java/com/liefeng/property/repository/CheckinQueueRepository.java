package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.CheckinQueuePo;

/**
 * 入住排队仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月6日
 */
@Transactional
public interface CheckinQueueRepository extends JpaRepository<CheckinQueuePo, String> {
	
}
