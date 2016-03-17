package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.ResidentHousePo;

/**
 * 住户房屋信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年3月15日
 */
@Transactional
public interface ResidentHouseRepository extends JpaRepository<ResidentHousePo, String> {

}
