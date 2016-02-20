package com.liefeng.property.service;

import org.springframework.stereotype.Service;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.intf.property.IProjectService;
import com.liefeng.intf.property.IWorkbenchService;
import com.liefeng.property.bo.project.HouseBo;
import com.liefeng.property.bo.project.HouseSpecBo;
import com.liefeng.property.domain.project.HouseContext;
import com.liefeng.property.domain.project.HouseSpecContext;
import com.liefeng.property.domain.project.ProjectBuildingContext;
import com.liefeng.property.domain.project.ProjectContext;
import com.liefeng.property.domain.workbench.TaskContext;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.project.HouseSpecVo;
import com.liefeng.property.vo.project.HouseVo;
import com.liefeng.property.vo.project.ProjectBuildingVo;
import com.liefeng.property.vo.project.ProjectVo;
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
		taskContext.create();
	}

	@Override
	public void updateTask(TaskVo taskVo) {
		TaskContext taskContext = TaskContext.build(taskVo);
		taskContext.update();
		
	}

}
