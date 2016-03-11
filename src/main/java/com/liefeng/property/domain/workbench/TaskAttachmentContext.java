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
import com.liefeng.common.util.ValidateHelper;
import com.liefeng.property.po.workbench.TaskAttachmentPo;
import com.liefeng.property.repository.workbench.TaskAttachmentRepository;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;


/**
 * 任务附件领域上下文对象
 * @author xhw
 * @date 2016年2月25日下午5:50:04
 */
@Service
@Scope("prototype")
public class TaskAttachmentContext {
	
	private static Logger logger = LoggerFactory.getLogger(TaskAttachmentContext.class);
	
	@Autowired
	private TaskAttachmentRepository taskAttachmentRepository;
	
	
	/**
	 * 客户值对象
	 */
	private TaskAttachmentVo taskAttachmentVo;
	
	/**
	 * 任务Id
	 */
	private String taskId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static TaskAttachmentContext getInstance() {
		return SpringBeanUtil.getBean(TaskAttachmentContext.class);
	}
	
	/**
	 * 根据任务附件值对象构建上下文
	 * @param task 项目值对象
	 * @return 任务上下文
	 */
	public static TaskAttachmentContext build(TaskAttachmentVo taskAttachmentVo) {
		TaskAttachmentContext taskAttachmentContext = getInstance();
		taskAttachmentContext.setTaskAttachmentVo(taskAttachmentVo);
		
		return taskAttachmentContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static TaskAttachmentContext build() {
		TaskAttachmentContext taskAttachmentContext = getInstance();
		
		return taskAttachmentContext;
	}
	
	/**
	 * 根据任务Id加载任务附件上下文
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午5:56:48
	 */
	public static TaskAttachmentContext loadById(String taskId){
		TaskAttachmentContext taskAttachmentContext = getInstance();
		taskAttachmentContext.setTaskId(taskId);
		
		return taskAttachmentContext;
	}
	
	/**
	 * 通过任务id获取任务附件
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午5:58:27
	 */
	public List<TaskAttachmentVo> findByTaskId(){
		List<TaskAttachmentVo> attachmentVoList = null;
		if (ValidateHelper.isNotEmptyString(taskId)) {
			List<TaskAttachmentPo> attachmentPoList = taskAttachmentRepository.findByTaskId(taskId);

			attachmentVoList = MyBeanUtil.createList(attachmentPoList, TaskAttachmentVo.class);
		}
		return attachmentVoList;
	}
	
	/**
	 * 创建任务附件
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:15:57
	 */
	public TaskAttachmentVo create() {
		if (taskAttachmentVo != null) {
			taskAttachmentVo.setId(UUIDGenerator.generate());
			taskAttachmentVo.setUploadTime(new Date());

			TaskAttachmentPo attachmentPo = MyBeanUtil.createBean(taskAttachmentVo, TaskAttachmentPo.class);
			taskAttachmentRepository.save(attachmentPo);

			logger.info("Create taskAttachment : {} success.", attachmentPo);
		}

		return taskAttachmentVo;
	}
	
	/**
	 * 根据任务id，删除任务的附件                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:16:44
	 */
   public void deleteByTaskId(){
	   if(ValidateHelper.isNotEmptyString(taskId)){
		   taskAttachmentRepository.deleteByTaskId(taskId);
		   
		   logger.info("Delete taskAttachment of taskId : {} success.", taskId);
	   }
   }

	protected void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	protected void setTaskAttachmentVo(TaskAttachmentVo taskAttachmentVo) {
		this.taskAttachmentVo = taskAttachmentVo;
	}
}
