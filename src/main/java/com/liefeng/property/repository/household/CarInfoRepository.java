package com.liefeng.property.repository.household;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.household.CarInfoPo;

/**
 * 车辆信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2016年4月25日
 */
@Transactional
public interface CarInfoRepository extends JpaRepository<CarInfoPo, String> {

}
