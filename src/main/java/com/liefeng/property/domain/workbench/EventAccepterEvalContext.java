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
import com.liefeng.common.util.TimeUtil;
import com.liefeng.common.util.UUIDGenerator;
import com.liefeng.common.util.ValidateHelper;
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
	
	/**
	 * 创建“办理人评价“（list）
	 * @param eventAccepterEvalVoList 
	 * @author xhw
	 * @date 2016年3月31日 下午1:52:00
	 */
	public void createList(List<EventAccepterEvalVo> eventAccepterEvalVoList){
		if(eventAccepterEvalVoList != null && eventAccepterEvalVoList.size() > 0){
			List<EventAccepterEvalPo> eventAccepterEvalPoList = MyBeanUtil.createList(eventAccepterEvalVoList, EventAccepterEvalPo.class);
			
			eventAccepterEvalRepository.save(eventAccepterEvalPoList);
			
			logger.info("Create eventAccepterEvalPoList : {} success.", eventAccepterEvalPoList);
		}
		
	}
	
	/**
	 * 获取今日点赞
	 * @param staffId
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:45:09
	 */
	public long getLikesOfToday(String staffId){
		long likes = 0;
		if(ValidateHelper.isNotEmptyString(staffId)){
			List<EventAccepterEvalPo> eventAccepterEvalPoList = eventAccepterEvalRepository.findByAccepterIdAndCreateTime(staffId, TimeUtil.format(new Date(), TimeUtil.PATTERN_1));
			
			if(eventAccepterEvalPoList != null){
				likes = eventAccepterEvalPoList.size();
			}
		}
		return likes;
	}
	
	/**
	 * 获取历史点赞
	 * @param staffId
	 * @return 
	 * @author xhw
	 * @date 2016年4月13日 下午2:45:09
	 */
	public long getAllLikes(String staffId){
		long likes = 0;
		if(ValidateHelper.isNotEmptyString(staffId)){
			List<EventAccepterEvalPo> eventAccepterEvalPoList = eventAccepterEvalRepository.findByAccepterId(staffId);
			
			if(eventAccepterEvalPoList != null){
				likes = eventAccepterEvalPoList.size();
			}
		}
		return likes;
	}
	
	protected void setEventAccepterEvalVo(EventAccepterEvalVo eventAccepterEvalVo) {
		this.eventAccepterEvalVo = eventAccepterEvalVo;
	}
}
