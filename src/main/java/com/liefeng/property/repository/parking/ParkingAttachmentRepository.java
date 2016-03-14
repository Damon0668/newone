package com.liefeng.property.repository.parking;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.parking.ParkingAttachmentPo;

/**
 * 车位租售附件仓储层
 * 
 * @author wuzhijng
 */
@Transactional
public interface ParkingAttachmentRepository extends JpaRepository<ParkingAttachmentPo, String> {

	public List<ParkingAttachmentPo> findByParkingRentalId(String parkingRentalId);
}
