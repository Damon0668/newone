package com.liefeng.property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liefeng.common.util.ValidateHelper;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.domain.workbench.TaskContext;
import com.liefeng.property.domain.workbench.TaskPrivilegeContext;
import com.liefeng.property.vo.workbench.TaskPrivilegeVo;
import com.liefeng.property.vo.workbench.TaskVo;


/**
 * 任务服务实现类
 * @author XHW
 * @date 2016年2月19日下午7:36:30
 */
@Service
public class WorkbenchService implements IWorkbenchService {

	@Override
	public TaskVo findTaskById(String taskId) {
		TaskContext taskContext = TaskContext.loadById(taskId);
		TaskVo taskVo = taskContext.getTask();
		
		return taskVo;
	}

	@Override
	public void createTask(TaskVo taskVo){
		TaskContext taskContext = TaskContext.build(taskVo);
		TaskVo tv = taskContext.create();
		
		if(tv!=null){   //创建任务的权限
			if(ValidateHelper.isNotEmptyString(tv.getAffstr())){
				String[] str1 = tv.getAffstr().split(",");
				for(int i=0; i<str1.length; i++){
					String[] str2 = str1[i].split("\\|");
					TaskPrivilegeVo aff = new TaskPrivilegeVo();
					aff.setTaskId(tv.getId());
					
					if("0".equals(str2[1])){
						aff.setProjectId(str2[0]);
						aff.setDeptId("-1");
						createTaskPrivilege(aff);
					}else{
						if("0".equals(str2[2])){
							aff.setProjectId(str2[0]);
							aff.setDeptId(str2[1]);
							aff.setStaffId("-1");
							createTaskPrivilege(aff);
						}else{
							aff.setProjectId(str2[0]);
							aff.setDeptId(str2[1]);
							aff.setStaffId(str2[2]);
							createTaskPrivilege(aff);
						}
					}
				}
				
			}
		}
	}

	@Override
	public void updateTask(TaskVo taskVo) {
		TaskContext taskContext = TaskContext.build(taskVo);
		taskContext.update();
		
	}

	@Override
	public TaskPrivilegeVo createTaskPrivilege(TaskPrivilegeVo taskPrivilegeVo) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.build(taskPrivilegeVo);
		return taskPrivilegeContext.create();
	}

	@Override
	public List<TaskPrivilegeVo> findTaskPrivilegeByTaskId(String taskId) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.loadById(taskId);
		return taskPrivilegeContext.getTaskPrivilegesByTaskid();
	}

	@Override
	public void deleteTaskPrivilegeByTaskId(String taskId) {
		TaskPrivilegeContext taskPrivilegeContext = TaskPrivilegeContext.loadById(taskId);
		taskPrivilegeContext.deleteByTaskId();
	}


}
