package com.liefeng.property.domain.workbench;

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
import com.liefeng.property.exception.PropertyException;
import com.liefeng.property.po.workbench.TaskPrivilegePo;
import com.liefeng.property.repository.workbench.TaskPrivilegeRepository;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;

/**
 * 任务权限领域上下文对象
 * @author xhw
 * @date 2016年2月22日下午3:44:06
 */
@Service
@Scope("prototype")
public class TaskPrivilegeContext {
	
	private static Logger logger = LoggerFactory.getLogger(TaskPrivilegeContext.class);
	
	@Autowired
	private TaskPrivilegeRepository taskPrivilegeRepository;
	
	/**
	 * 客户值对象
	 */
	private TaskPrivilegeVo taskPrivilege;
	
	/**
	 * 任务Id
	 */
	private String taskId;
	
	
	/**
	 * 获取本类实例，每次返回一个新对象
	 * @return 本类实例
	 */
	private static TaskPrivilegeContext getInstance() {
		return SpringBeanUtil.getBean(TaskPrivilegeContext.class);
	}
	
	/**
	 * 根据任务权限值对象构建上下文
	 * @param taskPrivilege
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日
	 */
	public static TaskPrivilegeContext build(TaskPrivilegeVo taskPrivilege) {
		TaskPrivilegeContext taskPrivilegeContext = getInstance();
		taskPrivilegeContext.setTaskPrivilege(taskPrivilege);
		
		return taskPrivilegeContext;
	}
	
	/**
	 * 构建上下文（无参）
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日
	 */
	public static TaskPrivilegeContext build() {
		TaskPrivilegeContext taskPrivilegeContext = getInstance();
		
		return taskPrivilegeContext;
	}
	
	/**
	 * 根据任务Id加载任务权限上下文
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午3:55:42
	 */
	public static TaskPrivilegeContext loadById(String taskId){
		TaskPrivilegeContext taskPrivilegeContext = getInstance();
		taskPrivilegeContext.setTaskId(taskId);
		
		return taskPrivilegeContext;
	}
	
	/**
	 * 通过任务Id获取相关的任务权限
	 * @return                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public List<TaskPrivilegeVo> getByTaskid(){
		List<TaskPrivilegeVo> privilegeVoList = null;
		if (ValidateHelper.isNotEmptyString(taskId)) {
			List<TaskPrivilegePo> privilegePoList = taskPrivilegeRepository.findByTaskId(taskId);

			privilegeVoList = MyBeanUtil.createList(privilegePoList, TaskPrivilegeVo.class);
		}

		return privilegeVoList;
	}
	
	/**
	 * 创建任务权限
	 * @throws PropertyException                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public TaskPrivilegeVo create() {
		if(taskPrivilege != null) {
			taskPrivilege.setId(UUIDGenerator.generate());
			taskPrivilege.setOemCode(ContextManager.getInstance().getOemCode()); 
            
            TaskPrivilegePo privilegePo = MyBeanUtil.createBean(taskPrivilege, TaskPrivilegePo.class);
            taskPrivilegeRepository.save(privilegePo);
            
            logger.info("Create taskPrivilege : {} success.", privilegePo);
		}
		
		return taskPrivilege;
	}
	
	/**
	 * 根据taskid，删除相关的任务权限
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:22:07
	 */
	public void deleteByTaskId() {
		if(ValidateHelper.isNotEmptyString(taskId)){
			taskPrivilegeRepository.deleteByTaskId(taskId);
			
			logger.info("Delete taskPrivilege of taskId : {} success.", taskId);
		}
		
	}

	protected void setTaskPrivilege(TaskPrivilegeVo taskPrivilege) {
		this.taskPrivilege = taskPrivilege;
	}

	protected void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
