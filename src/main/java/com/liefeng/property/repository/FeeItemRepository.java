package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.FeeItemPo;

/**
 * 费用项仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface FeeItemRepository extends JpaRepository<FeeItemPo, String> {

}
