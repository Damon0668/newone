package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentHistoryPo;
import com.liefeng.property.po.household.ResidentPo;

/**
 * 住户历史信息仓储层
 * 
 * @author wuzhijing
 * @date 2016-04-25
 */
@Transactional
public interface ResidentHistoryRepository extends JpaRepository<ResidentHistoryPo, String> {

}
