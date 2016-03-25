package com.liefeng.property.domain.workbench;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.MyBeanUtil;
import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.property.po.workbench.EventAccepterEvalPo;
import com.liefeng.property.repository.workbench.EventAccepterEvalRepository;
import com.liefeng.property.vo.workbench.EventAccepterEvalVo;


/**
 * 事件办理人评价上下文
 * @author xhw
 * @date 2016年3月25日 下午1:46:03
 */
@Service
@Scope("prototype")
public class EventAccepterEvalContext {
	
	private static Logger logger = LoggerFactory.getLogger(EventAccepterEvalContext.class);
	
	@Autowired
	private EventAccepterEvalRepository eventAccepterEvalRepository;
	
	
	/**
	 * 客户值对象
	 */
	private EventAccepterEvalVo eventAccepterEvalVo;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static EventAccepterEvalContext getInstance() {
		return SpringBeanUtil.getBean(EventAccepterEvalContext.class);
	}
	
	/**
	 * 根据值对象加载上下文对象
	 * @param eventAccepterEvalVo
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午1:49:03
	 */
	public static EventAccepterEvalContext build(EventAccepterEvalVo eventAccepterEvalVo) {
		EventAccepterEvalContext eventAccepterEvalContext = getInstance();
		eventAccepterEvalContext.setEventAccepterEvalVo(eventAccepterEvalVo);
		
		return eventAccepterEvalContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static EventAccepterEvalContext build() {
		EventAccepterEvalContext eventAccepterEvalContext = getInstance();
		
		return eventAccepterEvalContext;
	}
	
	/**
	 * 创建“事件办理人评价”
	 * @return 
	 * @author xhw
	 * @date 2016年3月25日 下午1:50:14
	 */
	public EventAccepterEvalVo create() {
		if (eventAccepterEvalVo != null) {
			eventAccepterEvalVo.setId(UUIDGenerator.generate());
			eventAccepterEvalVo.setCreateTime(new Date());

			EventAccepterEvalPo eventAccepterEvalPo = MyBeanUtil.createBean(eventAccepterEvalVo, EventAccepterEvalPo.class);
			eventAccepterEvalRepository.save(eventAccepterEvalPo);

			logger.info("Create eventAccepterEval : {} success.", eventAccepterEvalVo);
		}

		return eventAccepterEvalVo;
	}
	
	protected void setEventAccepterEvalVo(EventAccepterEvalVo eventAccepterEvalVo) {
		this.eventAccepterEvalVo = eventAccepterEvalVo;
	}
}
