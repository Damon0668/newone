package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.parking.ParkingRentalPo;

/**
 * 车位租售信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Transactional
public interface ParkingRentalRepository extends JpaRepository<ParkingRentalPo, String> {

}
