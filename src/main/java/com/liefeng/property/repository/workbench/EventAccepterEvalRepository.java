package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.EventAccepterEvalPo;


/**
 * 事件办理人评价仓储层
 * @author xhw
 * @date 2016年3月25日 下午1:44:25
 */
@Transactional
public interface EventAccepterEvalRepository extends JpaRepository<EventAccepterEvalPo, String> {
	
}
