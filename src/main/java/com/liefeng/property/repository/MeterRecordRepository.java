package com.liefeng.property.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.fee.MeterRecordPo;

/**
 * 抄表记录仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface MeterRecordRepository extends JpaRepository<MeterRecordPo, String> {

	/**
	 * 上个月抄表记录(业主表)
	 * @param projectId
	 * @param houseNum
	 * @param meterType
	 * @param meterOwner
	 * @param preDate
	 * @return
	 */
	@Query("select mr from MeterRecordPo mr where mr.projectId=?1 and  mr.houseNum=?2 and mr.meterType=?3 and mr.meterOwner=?4 and ?5 between mr.startDate and  mr.endDate")
	public MeterRecordPo getPreTypeAndMeterOwner(String projectId, String houseNum,String meterType,String meterOwner,Date preDate);

	/**
	 * 上个月抄表记录(公摊表)
	 * @param projectId
	 * @param houseNum
	 * @return
	 */
	@Query("select mr from MeterRecordPo mr where mr.projectId=?1 and  mr.buildingId=?2 and mr.meterType=?3 and mr.meterOwner=?4 and ?5 between mr.startDate and  mr.endDate")
	public MeterRecordPo getPrePublicTypeAndMeterOwner(String projectId, String buildingId,String meterType,String meterOwner,Date preDate);

}
