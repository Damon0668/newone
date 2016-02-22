package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 工作台接口
 * @author XHW
 * @date 2016年2月19日下午7:16:34
 */
public interface IWorkbenchService {

	/**
	 * 通过id获取任务
	 * @param taskId 任务id
	 * @return 具体任务对象              
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public TaskVo findTaskById(String taskId);


	/**
	 * 创建任务
	 * @param taskVo  任务对象
	 * @throws LiefengException                      
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public void createTask(TaskVo taskVo);

	/**
	 * 更新任务
	 * @param taskVo  任务对象                 
	 * @author xhw
	 * @date 2016年2月19日
	 */
	public void updateTask(TaskVo taskVo);
	
	/**
	 * 创建任务权限
	 * @param taskPrivilegeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:32:50
	 */
	public TaskPrivilegeVo createTaskPrivilege(TaskPrivilegeVo taskPrivilegeVo);

	/**
	 * 根据taskid，获取任务的相关任务权限
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:34:20
	 */
	public List<TaskPrivilegeVo> findTaskPrivilegeByTaskId(String taskId);
	
	/**
	 * 根据taskid，删除任务相关的任务权限
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:35:43
	 */
	public void deleteTaskPrivilegeByTaskId(String taskId);
	
}
