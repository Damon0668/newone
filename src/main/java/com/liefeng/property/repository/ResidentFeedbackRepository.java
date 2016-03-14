package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentFeedbackPo;


/**
 * 用户反馈仓储层
 *  
 * @author xhw
 * @date 2016年3月14日 上午10:05:25
 */
@Transactional
public interface ResidentFeedbackRepository extends JpaRepository<ResidentFeedbackPo, String> {
	
}
