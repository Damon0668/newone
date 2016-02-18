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
	 * 上个月抄表记录
	 * @param projectId
	 * @param houseNum
	 * @return
	 */
	@Query("select mr from MeterRecordPo mr where mr.projectId=?1 and  mr.houseNum=?2  and ?3 between mr.startDate and  mr.endDate")
	public MeterRecordPo getPreMeterRecord(String projectId, String houseNum,Date preDate);

}
