package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.EventReportPo;

/**
 * 报事仓储层
 * @author Huangama
 * @date 2016-3-3
 */
@Transactional
public interface EventReportRepository extends JpaRepository<EventReportPo, String> {

	public EventReportPo findByWfOrderId(String wfOrderId);

}
