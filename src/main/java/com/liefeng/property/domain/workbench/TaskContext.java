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
import com.liefeng.property.constant.WorkbenchConstants;
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.workbench.TaskPo;
import com.liefeng.property.repository.mybatis.TaskQueryRepository;
import com.liefeng.property.repository.workbench.TaskRepository;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 任务领域上下文对象
 * @author XHW
 * @date 2016年2月19日下午7:05:06
 */
@Service
@Scope("prototype")
public class TaskContext {
	
	private static Logger logger = LoggerFactory.getLogger(TaskContext.class);
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskQueryRepository taskQueryRepository;
	
	/**
	 * 客户值对象
	 */
	private TaskVo task;
	
	/**
	 * 任务Id
	 */
	private String taskId;
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static TaskContext getInstance() {
		return SpringBeanUtil.getBean(TaskContext.class);
	}
	
	/**
	 * 根据任务值对象构建上下文
	 * @param task 项目值对象
	 * @return 任务上下文
	 */
	public static TaskContext build(TaskVo task) {
		TaskContext taskContext = getInstance();
		taskContext.setTask(task);
		
		return taskContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return 任务上下文
	 */
	public static TaskContext build() {
		TaskContext taskContext = getInstance();
		
		return taskContext;
	}
	
	/**
	 * 根据任务Id加载任务上下文
	 * @param taskId 任务Id
	 * @return 任务上下文                     
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public static TaskContext loadById(String taskId){
		TaskContext taskContext = getInstance();
		taskContext.setTaskId(taskId);
		
		return taskContext;
	}
	
	/**
	 * 通过任务Id获取任务Vo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public TaskVo getTask(){
		if(task == null){
			TaskPo taskPo = null;
			if(ValidateHelper.isNotEmptyString(taskId)){
				taskPo = taskRepository.findOne(taskId);
			}
			
			if (taskPo != null) {
				task = MyBeanUtil.createBean(taskPo, TaskVo.class);
			}
		}
		return task;
	}
	
	/**
	 * 创建任务
	 * @throws PropertyException                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public TaskVo create() {
		if(task != null) {
			task.setId(UUIDGenerator.generate());
            task.setOemCode(ContextManager.getInstance().getOemCode()); 
            task.setCreateTime(new Date());
            task.setStatus(WorkbenchConstants.TaskStatus.PENDING);
            
            TaskPo taskPo = MyBeanUtil.createBean(task, TaskPo.class);
            taskRepository.save(taskPo);
            
            logger.info("Create task : {} success.", taskPo);
		}
		
		return task;
	}
	
	/**
	 * 更新任务
	 * @return                      
	 * @author xhw
	 * @date 2016年2月20日
	 */
	public TaskVo update() {
		if(task != null && ValidateHelper.isNotEmptyString(task.getId())) {
			TaskPo taskPo = taskRepository.findOne(task.getId());
			
			MyBeanUtil.copyBeanNotNull2Bean(task, taskPo);
			taskRepository.save(taskPo);
			
			logger.info("Update task of id: {} success.", task.getId());
		}
		
		return task;
	}
	
	public List<TaskVo> listMyAllTasks(String creatorId, String staffId) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("creatorId", creatorId);
		paramMap.put("staffId", staffId);
		
		return taskQueryRepository.listMyTasks(paramMap);
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTask(TaskVo task) {
		this.task = task;
	}
}
