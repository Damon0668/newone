package com.liefeng.property.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.MeterRecordPo;

/**
 * 抄表记录仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface MeterRecordRepository extends JpaRepository<MeterRecordPo, String> {

}
