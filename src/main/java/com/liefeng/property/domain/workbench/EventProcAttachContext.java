package com.liefeng.property.domain.workbench;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.property.po.workbench.EventProcAttachPo;
import com.liefeng.property.repository.workbench.EventProcAttachRepository;
import com.liefeng.property.vo.workbench.EventProcAttachVo;

/**
 * 事件处理领域模型
 * @author wuzhijing
 */
@Service
@Scope("prototype")
public class EventProcAttachContext {

	private Logger logger = LoggerFactory.getLogger(EventProcAttachContext.class);
	
	@Autowired
	private EventProcAttachRepository eventProcAttachRepository;
	
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 事件处理过程对象
	 */
	private EventProcAttachVo eventProcAttach;

	private static EventProcAttachContext getInstance() {
		EventProcAttachContext eventProcAttachContext = SpringBeanUtil
				.getBean(EventProcAttachContext.class);
		return eventProcAttachContext;
	}

	public static EventProcAttachContext build() {
		EventProcAttachContext eventProcAttachContext = getInstance();
		return eventProcAttachContext;
	}

	public static EventProcAttachContext build(EventProcAttachVo eventProcAttachVo) {
		EventProcAttachContext eventProcAttachContext = getInstance();
		eventProcAttachContext.setEventProcAttach(eventProcAttachVo);
		return eventProcAttachContext;
	}

	public static EventProcAttachContext loadById(String id) {
		EventProcAttachContext eventProcAttachContext = getInstance();
		eventProcAttachContext.setId(id);
		return eventProcAttachContext;
	}
	
	public void create() {
		eventProcAttach.setId(UUIDGenerator.generate());
		eventProcAttach.setUploadTime(new Date());
		eventProcAttachRepository.save(MyBeanUtil.createBean(eventProcAttach, EventProcAttachPo.class));
	}
	
	public void delete(){
		logger.info("delete eventProcAttach id is {}" ,id);
		eventProcAttachRepository.delete(id);
	}
	
	public List<EventProcAttachVo> findByEventProcessIdAndType(String eventProcessId ,String type){
		List<EventProcAttachPo> eventProcAttachPos = eventProcAttachRepository.findByEventProcessIdAndType(eventProcessId,type);
		return MyBeanUtil.createList(eventProcAttachPos, EventProcAttachVo.class);
	}
	
	protected void setId(String id) {
		this.id = id;
	}

	protected void setEventProcAttach(EventProcAttachVo eventProcAttach) {
		this.eventProcAttach = eventProcAttach;
	}

	
	
}
