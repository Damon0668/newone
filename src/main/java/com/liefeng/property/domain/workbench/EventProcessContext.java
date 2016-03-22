package com.liefeng.property.domain.workbench;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.core.dubbo.filter.ContextManager;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.po.workbench.EventProcessPo;
import com.liefeng.property.repository.mybatis.EventProcessQueryRepository;
import com.liefeng.property.repository.workbench.EventProcessRepository;
import com.liefeng.property.vo.workbench.EventProcessVo;

/**
 * 事件处理过程领域模型
 * @author wuzhijing
 * @author xhw
 */
@Service
@Scope("prototype")
public class EventProcessContext {

	private Logger logger = LoggerFactory.getLogger(EventProcessContext.class);
	
	@Autowired
	private EventProcessRepository eventProcessRepository;
	
	@Autowired
	private EventProcessQueryRepository eventProcessQueryRepository;
	
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
	
	public EventProcessVo create() {
		eventProcess.setId(UUIDGenerator.generate());
		eventProcess.setOemCode(ContextManager.getInstance().getOemCode());
		eventProcess.setAcceptTime(new Date());
		eventProcessRepository.save(MyBeanUtil.createBean(eventProcess, EventProcessPo.class));
		return eventProcess;
	}
	
	public EventProcessVo findByWfTaskId(String wfTaskId) {
		EventProcessPo eventProcessPo = eventProcessRepository.findByWfTaskId(wfTaskId);
		return MyBeanUtil.createBean(eventProcessPo, EventProcessVo.class);
	}
	
	public List<EventProcessVo> getHis(String orderId){
		logger.info("exe method getHis orderId eq {}",orderId);
		return eventProcessQueryRepository.getHisEventProcess(orderId);
	}
	
	public EventProcessVo getActive(String orderId,String staffid) {
		logger.info("exe method getActive orderId eq {}",orderId);
		return eventProcessQueryRepository.getActiveEventProcess(orderId,staffid);
	}
	
	public void update(){
		eventProcess.setAcceptTime(new Date());
		eventProcess.setOemCode(ContextManager.getInstance().getOemCode());
		eventProcessRepository.save(MyBeanUtil.createBean(eventProcess, EventProcessPo.class));
	}
	
	/**
	 * 获取某个已经完成的任务流程
	 * @param eventId 报事id
	 * @param taskName 步骤
	 * @return 
	 * @author xhw
	 * @date 2016年3月18日 下午6:18:29
	 */
	public EventProcessVo getEventProcess(String eventId, String taskName){
		EventProcessVo eventProcessVo = null;
		if(ValidateHelper.isNotEmptyString(eventId) && ValidateHelper.isNotEmptyString(taskName)){
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("eventId", eventId);
			paramMap.put("taskName", taskName);
			
			PagingParamVo param = new PagingParamVo();
			param.setExtra(paramMap);
			
			eventProcessVo = eventProcessQueryRepository.findEventProcess(param);
		}
		return eventProcessVo;
	}
	protected void setId(String id) {
		this.id = id;
	}

	protected void setEventProcess(EventProcessVo eventProcess) {
		this.eventProcess = eventProcess;
	}

	
	
}
