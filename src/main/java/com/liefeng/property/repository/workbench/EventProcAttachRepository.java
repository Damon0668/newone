package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.EventProcAttachPo;

/**
 * 工单处理附件
 * @author wuzhijing
 * @date 2016-3-10
 */
@Transactional
public interface EventProcAttachRepository extends JpaRepository<EventProcAttachPo, String> {

	public List<EventProcAttachPo> findByEventProcessIdAndType(String eventProcessId,
			String type);
}
