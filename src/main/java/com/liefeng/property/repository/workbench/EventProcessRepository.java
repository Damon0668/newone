package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liefeng.property.po.workbench.EventProcessPo;
import com.liefeng.property.vo.workbench.EventProcessVo;

/**
 * 报事仓储层
 * @author wuzhijing
 * @date 2016-3-10
 */
@Transactional
public interface EventProcessRepository extends JpaRepository<EventProcessPo, String> {

	public EventProcessPo findByWfTaskId(String wfTaskId);

}
