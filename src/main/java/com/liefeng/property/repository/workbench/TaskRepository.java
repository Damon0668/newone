package com.liefeng.property.repository.workbench;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.TaskPo;


/**
 * 任务仓储层
 * @author XHW
 * @date 2016年2月19日下午7:02:16
 */
@Transactional
public interface TaskRepository extends JpaRepository<TaskPo, String> {
	
}
