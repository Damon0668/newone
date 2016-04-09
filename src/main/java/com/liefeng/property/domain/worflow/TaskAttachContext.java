package com.liefeng.property.domain.worflow;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liefeng.common.util.SpringBeanUtil;
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.repository.mybatis.TaskAttachQueryRepository;
import com.liefeng.service.vo.TaskAttachVo;

/**
 * 工作流附件领域模型
 * 
 * @author ZhenTingJun
 * @date 2016年4月9日
 */
@Service
@Scope("prototype")
public class TaskAttachContext {
	
	private static Logger logger = LoggerFactory.getLogger(TaskAttachContext.class);
	
	@Autowired
	private TaskAttachQueryRepository attachQueryRepository;
	
	private String orderId;
	
	private static TaskAttachContext getInstance() {
		return SpringBeanUtil.getBean(TaskAttachContext.class);
	}
	
	public static TaskAttachContext build(String orderId) {
		TaskAttachContext taskAttachContext = getInstance();
		taskAttachContext.setOrderId(orderId);
		return taskAttachContext;
	}
	
	/**
	 * 根据流程实例ID查询附件
	 * @return 附件列表
	 */
	public List<TaskAttachVo> getTaskAttachs() {
		List<TaskAttachVo> taskAttachVos = new ArrayList<TaskAttachVo>();
		if(ValidateHelper.isNotEmptyString(orderId)) {
			logger.info("get TaskAttac by orderId, orderId = {}", orderId);
			taskAttachVos = attachQueryRepository.queryTaskAttachList(orderId);
		}
		
		return taskAttachVos;
	}

	protected void setOrderId(String orderId) {
		this.orderId = orderId;
	} 
}
