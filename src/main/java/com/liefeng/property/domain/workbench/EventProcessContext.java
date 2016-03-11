package com.liefeng.property.domain.workbench;


import java.util.Date;

import javax.swing.text.AbstractDocument.Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.property.po.workbench.EventProcessPo;
import com.liefeng.property.repository.workbench.EventProcessRepository;
import com.liefeng.property.vo.workbench.EventProcessVo;

/**
 * 事件处理过程领域模型
 * @author wuzhijing
 */
@Service
@Scope("prototype")
public class EventProcessContext {

	private Logger logger = LoggerFactory.getLogger(EventProcessContext.class);
	
	@Autowired
	private EventProcessRepository eventProcessRepository;
	
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 事件处理过程对象
	 */
	private EventProcessVo eventProcess;

	private static EventProcessContext getInstance() {
		EventProcessContext eventProcessContext = SpringBeanUtil
				.getBean(EventProcessContext.class);
		return eventProcessContext;
	}

	public static EventProcessContext build() {
		EventProcessContext eventProcessContext = getInstance();
		return eventProcessContext;
	}

	public static EventProcessContext build(EventProcessVo eventProcessVo) {
		EventProcessContext eventProcessContext = getInstance();
		eventProcessContext.setEventProcess(eventProcessVo);
		return eventProcessContext;
	}

	public static EventProcessContext loadById(String id) {
		EventProcessContext eventProcessContext = getInstance();
		eventProcessContext.setId(id);
		return eventProcessContext;
	}
	
	public void create() {
		eventProcess.setId(UUIDGenerator.generate());
		eventProcess.setOemCode(ContextManager.getInstance().getOemCode());
		eventProcess.setAcceptTime(new Date());
		eventProcessRepository.save(MyBeanUtil.createBean(eventProcess, EventProcessPo.class));
	}
	
	public EventProcessVo findByWfTaskId(String wfTaskId) {
		EventProcessPo eventProcessPo = eventProcessRepository.findByWfTaskId(wfTaskId);
		return MyBeanUtil.createBean(eventProcessPo, EventProcessVo.class);
	}
	
	public void update(){
		eventProcessRepository.save(MyBeanUtil.createBean(eventProcess, EventProcessPo.class));
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setEventProcess(EventProcessVo eventProcess) {
		this.eventProcess = eventProcess;
	}
}
