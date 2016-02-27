package com.liefeng.intf.property;

import java.util.List;

import com.liefeng.core.entity.DataPageValue;
import com.liefeng.core.exception.LiefengException;
import com.liefeng.property.vo.workbench.NoticePrivilegeVo;
import com.liefeng.property.vo.workbench.NoticeVo;
import com.liefeng.property.vo.workbench.TaskAttachmentVo;
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
	
	/**
	 * 根据状态，员工Id获取任务的数量
	 * @param status 状态
	 * @param staffId 员工Id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月24日 上午10:45:00
	 */
	public Long findCountByStatusAndStaffId(String status, String staffId);
	
	/**
	 * 分页查询任务信息
	 * @param status 任务状态
	 * @param staffId 员工Id
	 * @param page 当前页
	 * @param size 每页数量
	 * @return                      
	 * @author xhw
	 * @date 2016年2月24日 上午10:47:17
	 */
	public DataPageValue<TaskVo> findTaskByPage(String status, String staffId,Integer page, Integer size);
	
	/**
	 * 获取与员工有关的最近几条待处理、待审核的任务
	 * @param staffId  员工id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 上午11:23:46
	 */
	public List<TaskVo> findTaskByStaffIdAndSize(String staffId, Integer size);
	
	/**
	 * 创建任务附件
	 * @param attachmentVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:21:43
	 */
	public TaskAttachmentVo createTaskAttachment(TaskAttachmentVo attachmentVo);
	
	/**
	 * 根据任务id，获取任务的附件
	 * @param taskId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:23:10
	 */
	public List<TaskAttachmentVo> findAttachmentByTaskId(String taskId);
	
	/**
	 * 根据任务id，删除任务的附件
	 * @param taskId                      
	 * @author xhw
	 * @date 2016年2月25日 下午7:24:08
	 */
	public void deleteAttachmentByTaskId(String taskId);
	
	/**
	 * 创建通知
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:47:01
	 */
	public NoticeVo createNotice(NoticeVo noticeVo);
	
	/**
	 * 更新通知
	 * @param noticeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:47:57
	 */
	public NoticeVo updateNotice(NoticeVo noticeVo);
	
	/**
	 * 根据通知id，获取id
	 * @param id
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午4:48:58
	 */
	public NoticeVo getNoticeById(String id);
	
	/**
	 * 创建通知权限对象
	 * @param noticePrivilegeVo
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:16:26
	 */
	public NoticePrivilegeVo createNoticePrivilege(NoticePrivilegeVo noticePrivilegeVo);
	
	/**
	 * 根据通知id，获取相关的通知权限
	 * @param noticeId
	 * @return                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:22:49
	 */
	public List<NoticePrivilegeVo> getNoticePrivilegeByNoticeId(String noticeId);
	
	/**
	 * 根据通知id，删除相关的通知权限
	 * @param noticeId                      
	 * @author xhw
	 * @date 2016年2月26日 下午7:25:14
	 */
	public void deleteNoticePrivilegeByNoticeId(String noticeId);
}
