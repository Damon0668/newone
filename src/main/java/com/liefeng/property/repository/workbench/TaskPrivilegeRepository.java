package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.TaskPrivilegePo;


/**
 * 任务权限仓储层
 * @author xhw
 * @date 2016年2月22日下午3:45:15
 */
@Transactional
public interface TaskPrivilegeRepository extends JpaRepository<TaskPrivilegePo, String> {
	
	/**
	 * 查询任务的任务权限
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:10:20
	 */
	public List<TaskPrivilegePo> findByTaskId(String taskId);
	
	/**
	 * 根据taskid，删除相应的任务权限
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月22日 下午4:25:56
	 */
	public void deleteByTaskId(String taskId);
}
