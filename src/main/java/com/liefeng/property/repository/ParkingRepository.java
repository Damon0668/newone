package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.parking.ParkingPo;

/**
 * 车位信息仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-25
 */
@Transactional
public interface ParkingRepository extends JpaRepository<ParkingPo, String> {

	public ParkingPo findByProjectIdAndNum(String projectId, String num);

}
