package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ProprietorHousePo;

/**
 * 业主房产信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface ProprietorHouseRepository extends JpaRepository<ProprietorHousePo, String> {

}
