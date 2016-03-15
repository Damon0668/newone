package com.liefeng.property.repository.fee;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.fee.FeeRecordPo;

/**
 * 缴费记录仓储层
 * 
 * @author ZhenTingJun
 * @date 2015-12-24
 */
@Transactional
public interface FeeRecordRepository extends JpaRepository<FeeRecordPo, String> {

	public FeeRecordPo findByFeeItemId(String feeItemId);

}
