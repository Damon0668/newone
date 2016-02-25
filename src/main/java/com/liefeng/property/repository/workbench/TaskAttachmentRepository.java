package com.liefeng.property.repository.workbench;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liefeng.property.po.workbench.TaskAttachmentPo;


/**
 * 任务仓储层
 * @author xhw
 * @date 2016年2月25日下午5:51:06
 */
@Transactional
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachmentPo, String> {
	
	/**
	 * 通过任务id获取任务的附件
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午6:02:38
	 */
	public List<TaskAttachmentPo> findByTaskId(String taskId);
	
	/**
	 * 根据任务id，删除任务的附件
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:18:48
	 */
	public void deleteByTaskId(String taskId);
}
