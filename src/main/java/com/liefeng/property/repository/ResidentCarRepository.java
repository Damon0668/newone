package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentCarPo;

/**
 * 住户车辆信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Transactional
public interface ResidentCarRepository extends JpaRepository<ResidentCarPo, String> {

}
